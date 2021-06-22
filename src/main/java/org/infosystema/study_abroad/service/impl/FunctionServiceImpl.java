package org.infosystema.study_abroad.service.impl;

import org.apache.commons.lang.StringUtils;
import org.infosystema.study_abroad.beans.ApplicationCheck;
import org.infosystema.study_abroad.beans.EntryValue;
import org.infosystema.study_abroad.beans.Param;
import org.infosystema.study_abroad.service.FunctionService;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class FunctionServiceImpl implements FunctionService {

    @Inject
    protected EntityManager em;
    @Resource(mappedName = "java:jboss/datasources/study_abroadDS")
    protected DataSource ds;

    protected final static Map<Class<?>, EntryValue<Integer, String>> types = new HashMap<>();

    static {
        types.put(byte[].class, new EntryValue<Integer, String>(Types.BIT, "bit"));
        types.put(Byte[].class, new EntryValue<Integer, String>(Types.BIT, "bit"));

        types.put(short[].class, new EntryValue<Integer, String>(Types.INTEGER, "integer"));
        types.put(Short[].class, new EntryValue<Integer, String>(Types.INTEGER, "integer"));

        types.put(int[].class, new EntryValue<Integer, String>(Types.INTEGER, "integer"));
        types.put(Integer[].class, new EntryValue<Integer, String>(Types.INTEGER, "integer"));

        types.put(long[].class, new EntryValue<Integer, String>(Types.BIGINT, "bigint"));
        types.put(Long[].class, new EntryValue<Integer, String>(Types.BIGINT, "bigint"));

        types.put(float[].class, new EntryValue<Integer, String>(Types.REAL, "real"));
        types.put(Float[].class, new EntryValue<Integer, String>(Types.REAL, "real"));

        types.put(double[].class, new EntryValue<Integer, String>(Types.DOUBLE, "double"));
        types.put(Double[].class, new EntryValue<Integer, String>(Types.DOUBLE, "double"));

        types.put(Date[].class, new EntryValue<Integer, String>(Types.TIMESTAMP, "timestamp"));
        types.put(String[].class, new EntryValue<Integer, String>(Types.VARCHAR, "varchar"));
    }

    @Override
    public <U> U callAndGetValue(String functionName, Collection<Param> params, String value) {
        if (detectArrayClass(params)) {
            return callJDBCFunction(functionName, params, value);
        }

        StoredProcedureQuery storedProcedure = callFunction(functionName, params);

        @SuppressWarnings("unchecked")
        U result = (U) storedProcedure.getSingleResult();

        return result;
    }

    @Override
    public Integer callAndGetValue(String functionName, Collection<Param> params) {
        StoredProcedureQuery storedProcedure = callFunction(functionName, params);

        return new Integer(storedProcedure.getFirstResult());
    }

    @Override
    public void call(String functionName, Collection<Param> params) {
        callFunction(functionName, params);
    }

    protected StoredProcedureQuery callFunction(String functionName, Collection<Param> params) {
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery(functionName);

        if (params == null) return null;

        for (Param param : params) {
            //System.out.println(param);
            storedProcedure.registerStoredProcedureParameter(param.getName(), param.getType(), param.getMode());
            if (param.getMode().ordinal() != ParameterMode.OUT.ordinal())
                storedProcedure.setParameter(param.getName(), param.getValue());
        }

        storedProcedure.execute();
        //System.out.println("called StoredProcedure " + functionName);

        return storedProcedure;
    }

    @Override
    public Map<String, Object> callAndGetValues(String functionName, Collection<Param> params) {
        Map<String, Object> map = new HashMap<>(params.size());

        StoredProcedureQuery storedProcedure = callFunction(functionName, params);

        for (Param value : params) {
            if (!value.getMode().equals(ParameterMode.OUT)) continue;

            Object result = (Object) storedProcedure.getOutputParameterValue(value.getName());
            map.put(value.getName(), result);
        }

        return map;
    }

    @Override
    public ApplicationCheck callAndGetValueAsString(String functionName, Collection<Param> params) {
        StringBuffer buffer = new StringBuffer("SELECT " + getCorrectName(functionName) + " as check_appl_detail FROM " + functionName + "(");

        for (Param param : params) {
            buffer.append(getValue(param) + ",");
        }

        String sql = buffer.toString();
        sql = sql.substring(0, sql.length() - 1) + ")";

        //System.out.println(sql);
        Query query = em.createNativeQuery(sql, "ApplicationCheck").setMaxResults(1000);

        @SuppressWarnings("unchecked")
        List<ApplicationCheck> applications = query.getResultList();

        return applications.size() < 1 ? new ApplicationCheck("") : applications.get(0);
    }

    private String getCorrectName(String functionName) {
        if (functionName == null || functionName.indexOf('.') == -1) return functionName;
        return functionName.substring(functionName.indexOf('.') + 1);
    }

    private Object getValue(Param param) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (param.getType().equals(Date.class)) {
            return "'" + format.format(param.getValue()) + "'";
        } else if (param.getType().equals(String.class)) return "'" + param.getValue() + "'";
        else return param.getValue();
    }

    @SuppressWarnings("unchecked")
    private <U> U callJDBCFunction(String functionName, Collection<Param> params, String value) {
        U u = null;

        try {
            StringBuffer buffer = new StringBuffer("select " + functionName + "(?" + StringUtils.left(",?", params.size() - 1) + ")");
            //System.out.println(buffer.toString());
            Connection connection = ds.getConnection();
            CallableStatement statement = connection.prepareCall(buffer.toString());
            int i = 1;
            int out = -1;

            for (Param param : params) {
                if (ParameterMode.OUT.equals(param.getMode())) {
                    out = i++;
                    statement.registerOutParameter(out, getType(param.getType()).getK());
                } else if (param.getType().isArray()) {
                    Array array = connection.createArrayOf(getType(param.getType()).getV(), (Object[]) param.getValue());
                    statement.setArray(i++, array);
                } else {
                    statement.setObject(i++, param.getValue());
                }
            }

            if (value == null) {
                ResultSet set = statement.executeQuery();
                if (set.next()) u = (U) set.getString(functionName.substring(functionName.indexOf('.') + 1));
                set.close();
            }

            if (out != -1) u = (U) statement.getString(out);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println(u);

        return u;
    }

    private boolean detectArrayClass(Collection<Param> params) {
        for (Param param : params) {
            if (param.getType().isArray()) return true;
        }

        return false;
    }

    public EntryValue<Integer, String> getType(Class<?> arrayClass) {
        //System.out.println(arrayClass);

        EntryValue<Integer, String> value = types.get(arrayClass);
        if (value == null) value = new EntryValue<Integer, String>(Types.VARCHAR, "varchar");

        return value;
    }

}

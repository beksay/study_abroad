package org.infosystema.iselect.service;

import java.util.Collection;
import java.util.Map;

import org.infosystema.iselect.beans.ApplicationCheck;
import org.infosystema.iselect.beans.Param;

public interface FunctionService {

    <U> U callAndGetValue(String functionName, Collection<Param> params, String value);

    Map<String, Object> callAndGetValues(String functionName, Collection<Param> params);

    void call(String functionName, Collection<Param> params);

    Integer callAndGetValue(String functionName, Collection<Param> params);

    ApplicationCheck callAndGetValueAsString(String functionName, Collection<Param> params);

}
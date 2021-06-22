package org.infosystema.study_abroad.beans;

import javax.persistence.ParameterMode;

public class Param {

    private String name;
    private Object value;
    private Class<?> type;
    private ParameterMode mode;

    public Param() {
    }

    public Param(String name, Class<?> type, ParameterMode mode) {
        this.name = name;
        this.type = type;
        this.mode = mode;
    }

    public Param(String name, Class<?> type, ParameterMode mode, Object value) {
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ParameterMode getMode() {
        return mode;
    }

    public void setMode(ParameterMode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Param [name=" + name + ", value=" + value + ", type=" + type + ", mode=" + mode + "]";
    }

}

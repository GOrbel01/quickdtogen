package org.fsq.model;

public class GenItem {
    private String fieldName;
    private String getter;
    private String setter;
    private String type;

    public GenItem(String fieldName, String getter, String setter, String type) {
        this.fieldName = fieldName;
        this.getter = getter;
        this.setter = setter;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getSetter() {
        return setter;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GenItem{" +
                "fieldName='" + fieldName + '\'' +
                ", getter='" + getter + '\'' +
                ", setter='" + setter + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

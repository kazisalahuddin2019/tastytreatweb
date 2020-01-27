package com.prangroup.kazi.tastytreat.model;

public class ShowroomDataModel {
    private  String id,code,location;

    public ShowroomDataModel(String id, String code, String location) {
        this.id = id;
        this.code = code;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

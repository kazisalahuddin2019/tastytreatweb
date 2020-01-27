package com.prangroup.kazi.tastytreat.model;

public class OrderCollectionInfoModel {
    private String orderid,name,address,mobno,lat,lng;

    public OrderCollectionInfoModel(String orderid, String name, String address, String mobno, String lat, String lng) {
        this.orderid = orderid;
        this.name = name;
        this.address = address;
        this.mobno = mobno;
        this.lat = lat;
        this.lng = lng;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}

package com.prangroup.kazi.tastytreat.model;

public class OrderDetailsDataModel {
    private String itemname,qnty,rate,sold_type;

    public OrderDetailsDataModel(String itemname, String qnty, String rate, String sold_type) {
        this.itemname = itemname;
        this.qnty = qnty;
        this.rate = rate;
        this.sold_type = sold_type;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getQnty() {
        return qnty;
    }

    public void setQnty(String qnty) {
        this.qnty = qnty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSold_type() {
        return sold_type;
    }

    public void setSold_type(String sold_type) {
        this.sold_type = sold_type;
    }
}

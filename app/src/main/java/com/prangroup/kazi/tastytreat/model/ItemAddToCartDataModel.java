package com.prangroup.kazi.tastytreat.model;

public class ItemAddToCartDataModel {
    private String showroomId,itemCode,itemName,qnty,rate,totalprice,type;

    public ItemAddToCartDataModel(String showroomId, String itemCode, String itemName, String qnty, String rate, String totalprice, String type) {
        this.showroomId = showroomId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qnty = qnty;
        this.rate = rate;
        this.totalprice = totalprice;
        this.type = type;
    }

    public String getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(String showroomId) {
        this.showroomId = showroomId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

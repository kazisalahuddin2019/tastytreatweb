package com.prangroup.kazi.tastytreat.model;

public class ItemDataModel {
    private String itemcode,itemname,image,haveimage,haveoffer,offerstatus,offertype,amount,qnty,freeqnty,freeItemCodes,freeItemName,actualPrice,mrp,isShown;

    public ItemDataModel(String itemcode, String itemname, String image, String haveimage, String haveoffer, String offerstatus, String offertype, String amount, String qnty, String freeqnty, String freeItemCodes, String freeItemName, String actualPrice, String mrp, String isShown) {
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.image = image;
        this.haveimage = haveimage;
        this.haveoffer = haveoffer;
        this.offerstatus = offerstatus;
        this.offertype = offertype;
        this.amount = amount;
        this.qnty = qnty;
        this.freeqnty = freeqnty;
        this.freeItemCodes = freeItemCodes;
        this.freeItemName = freeItemName;
        this.actualPrice = actualPrice;
        this.mrp = mrp;
        this.isShown = isShown;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHaveimage() {
        return haveimage;
    }

    public void setHaveimage(String haveimage) {
        this.haveimage = haveimage;
    }

    public String getHaveoffer() {
        return haveoffer;
    }

    public void setHaveoffer(String haveoffer) {
        this.haveoffer = haveoffer;
    }

    public String getOfferstatus() {
        return offerstatus;
    }

    public void setOfferstatus(String offerstatus) {
        this.offerstatus = offerstatus;
    }

    public String getOffertype() {
        return offertype;
    }

    public void setOffertype(String offertype) {
        this.offertype = offertype;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQnty() {
        return qnty;
    }

    public void setQnty(String qnty) {
        this.qnty = qnty;
    }

    public String getFreeqnty() {
        return freeqnty;
    }

    public void setFreeqnty(String freeqnty) {
        this.freeqnty = freeqnty;
    }

    public String getFreeItemCodes() {
        return freeItemCodes;
    }

    public void setFreeItemCodes(String freeItemCodes) {
        this.freeItemCodes = freeItemCodes;
    }

    public String getFreeItemName() {
        return freeItemName;
    }

    public void setFreeItemName(String freeItemName) {
        this.freeItemName = freeItemName;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getIsShown() {
        return isShown;
    }

    public void setIsShown(String isShown) {
        this.isShown = isShown;
    }
}

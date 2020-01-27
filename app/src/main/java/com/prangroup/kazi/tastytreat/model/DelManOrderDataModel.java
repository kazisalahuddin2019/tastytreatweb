package com.prangroup.kazi.tastytreat.model;

public class DelManOrderDataModel {
    private String id, showroomname, showroomaddress, showroommobno, showroomlat, showroomlng, customername, customermobno,
            delivery_address, purchagedProduct, freeProduct, productprice;

    public DelManOrderDataModel(String id, String showroomname, String showroomaddress, String showroommobno,
                                String showroomlat, String showroomlng, String customername, String customermobno,
                                String delivery_address, String purchagedProduct, String freeProduct, String productprice) {
        this.id = id;
        this.showroomname = showroomname;
        this.showroomaddress = showroomaddress;
        this.showroommobno = showroommobno;
        this.showroomlat = showroomlat;
        this.showroomlng = showroomlng;
        this.customername = customername;
        this.customermobno = customermobno;
        this.delivery_address = delivery_address;
        this.purchagedProduct = purchagedProduct;
        this.freeProduct = freeProduct;
        this.productprice = productprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowroomname() {
        return showroomname;
    }

    public void setShowroomname(String showroomname) {
        this.showroomname = showroomname;
    }

    public String getShowroomaddress() {
        return showroomaddress;
    }

    public void setShowroomaddress(String showroomaddress) {
        this.showroomaddress = showroomaddress;
    }

    public String getShowroommobno() {
        return showroommobno;
    }

    public void setShowroommobno(String showroommobno) {
        this.showroommobno = showroommobno;
    }

    public String getShowroomlat() {
        return showroomlat;
    }

    public void setShowroomlat(String showroomlat) {
        this.showroomlat = showroomlat;
    }

    public String getShowroomlng() {
        return showroomlng;
    }

    public void setShowroomlng(String showroomlng) {
        this.showroomlng = showroomlng;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomermobno() {
        return customermobno;
    }

    public void setCustomermobno(String customermobno) {
        this.customermobno = customermobno;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getPurchagedProduct() {
        return purchagedProduct;
    }

    public void setPurchagedProduct(String purchagedProduct) {
        this.purchagedProduct = purchagedProduct;
    }

    public String getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(String freeProduct) {
        this.freeProduct = freeProduct;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
}

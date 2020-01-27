package com.prangroup.kazi.tastytreat.model;

public class UserOrderListDataModel {
    private String id,order_status,delivery_address,purchageProduct,freeProduct,total_amount;

    public UserOrderListDataModel(String id, String order_status, String delivery_address, String purchageProduct, String freeProduct, String total_amount) {
        this.id = id;
        this.order_status = order_status;
        this.delivery_address = delivery_address;
        this.purchageProduct = purchageProduct;
        this.freeProduct = freeProduct;
        this.total_amount = total_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getPurchageProduct() {
        return purchageProduct;
    }

    public void setPurchageProduct(String purchageProduct) {
        this.purchageProduct = purchageProduct;
    }

    public String getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(String freeProduct) {
        this.freeProduct = freeProduct;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}

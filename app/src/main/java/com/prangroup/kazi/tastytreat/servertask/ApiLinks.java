package com.prangroup.kazi.tastytreat.servertask;


public class ApiLinks {
    public static String imagebase="http://172.17.2.114:90/tastytreat/dashboard/pages/forms/filesuploads/";
    public static String base="http://172.17.2.114:90/tastytreat/dashboard/pages/forms/api/";
    public static String remoteBase="http://hris.prangroup.com:8686/pranapi/pranapi.svc/";
    public static String showroomItem=remoteBase+"GetShowroom/";
    public static String itemStockInfo=remoteBase+"GetShowroomWithItem/";
    public static String get_registerApi=base+"regmob.php";
    public static String get_loginApi=base+"deliverymanlogin.php";
    public static String get_orderlistApi=base+"deliverymanwiseorder.php";
    public static String get_userAddressApi=base+"useraddress.php?id=";
    public static String get_UserOrderlistApi=base+"userorderlist.php";
    public static String get_UserorderdetailsApi=base+"userorderdetails.php";
    public static String get_showroomdataApi=base+"showroomdata.php";
    public static String get_productOrderdataApi=base+"productOrderdata.php";
    public static String get_productOrderUpdateApi=base+"deliverymanorderupdate.php";
    public static String securityKey="prgpran0192px8736x78/";
    public static String get_itemdataApi=showroomItem+securityKey;
    public static String get_itemStockInfoApi=itemStockInfo+securityKey;

}

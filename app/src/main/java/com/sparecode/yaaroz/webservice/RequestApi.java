package com.sparecode.yaaroz.webservice;

/**
 * Created by user_1 on 10/14/2016.
 */

public interface RequestApi {

    String USERDATA = "userData";
    String LOGIN = "login";
    String FBLOGIN = "login_with_fb";
    String MYPRODUCT = "MyProduct";
    String SEARCH_PRODUCT = "search_product";
    String FORGOT_PASSWORD = "forgotPassword";
    String PRODUCT_DETAIL = "product_detail";
    String PRODUCT_DELETE = "DeleteProduct";
    String PRODUCT_ORDER = "OrderHistory";
    String REVIEWS_RATINGS = "Reviews_ratings";
    String PRODUCT_DISLIKE = "Review_likeDislike";
    String START_CHAT = "startChat";
    String UPDATE_DETAILS = "UpdateDeviceDetails";
    String HISTORY_CHAT = "getConversation";
    //String BASEURL = "http://192.168.2.16/projects/wilo/api/";
    String BASEURL = "http://yaaroz.com/admin/index.php/API/?data=";

}

package com.sparecode.yaaroz.webservice;

import android.support.v4.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user_1 on 10/14/2016.
 */

public class ReqestParameter {

    //email,password
    public JSONObject toLogin(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "signup");
            object.put("email", vars[0]);
            object.put("image", vars[1]);
            //object.put("lat", vars[2]);
            // object.put("long", vars[3]);
            object.put("fb_id", vars[4]);
            object.put("device_type", vars[5]);
            object.put("device_id", vars[6]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetCity() {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "get_city");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetLocation(String cityId) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "get_location");
            object.put("nCityId", "" + cityId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toUserCitySelection(String cityId, String userId) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "update_location");
            object.put("nCityId", "" + cityId);
            object.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetQuestions() {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "get_que");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetRoomMatches(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "room_match");
            object.put("nCityId", vars[0]);
            object.put("nLocationId", vars[1]);
        } catch (JSONException e) {
        }
        return object;
    }
    public JSONObject toFilterRoom(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "filter_room");
            object.put("sLength", vars[0]);
            object.put("sRent", vars[1]);
            object.put("startdate", vars[2]);
            object.put("enddate", vars[3]);
            object.put("sFurnish", vars[4]);
            object.put("sBathroom", vars[5]);
            object.put("sSmoking", vars[6]);
            object.put("sPets", vars[7]);
            object.put("sLookingForAge", vars[8]);
            object.put("sLookingForAgeMax", vars[9]);
            object.put("MinLength", vars[10]);
            object.put("MaxLength", vars[11]);
            object.put("MinBudget", vars[12]);
            object.put("MaxBudget", vars[13]);
        } catch (JSONException e) {
        }
        return object;
    }
    public List<Pair<String, String>> toAddroom(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("nCityId", vars[0]));
        pairList.add(new Pair<>("sAddress", vars[1]));
        pairList.add(new Pair<>("sDesc", vars[2]));
        pairList.add(new Pair<>("sRent", vars[3]));
        pairList.add(new Pair<>("sRoommates", vars[4]));
        pairList.add(new Pair<>("sGender", vars[5]));
        pairList.add(new Pair<>("sLength", vars[6]));
        pairList.add(new Pair<>("sFurnish", vars[7]));
        pairList.add(new Pair<>("sBathroom", vars[8]));
        pairList.add(new Pair<>("sSmoking", vars[9]));
        pairList.add(new Pair<>("sPets", vars[10]));
        pairList.add(new Pair<>("sLookingForGender", vars[11]));
        pairList.add(new Pair<>("sLookingForAge", vars[12]));
        pairList.add(new Pair<>("dAvailableDate", vars[13]));
        pairList.add(new Pair<>("nLocationId", vars[14]));
        pairList.add(new Pair<>("nUserId", vars[15]));
        pairList.add(new Pair<>("sLookingForAgeMax", vars[16]));
        pairList.add(new Pair<>("sLat", vars[17]));
        pairList.add(new Pair<>("sLong", vars[18]));


        return pairList;
    }
    public JSONObject toUpadateProfile(String... vars) {
        JSONObject object = new JSONObject();
        try {
            //index.php/API/?data={"method":"update_profile","fname":"kenil","lname":"shah","smoking":"1","pets":"1","school":"yeaskjh","profession":"it","aboutme":"test","user_id":"2","questions":[{"qid":"1","aid":"1"},{"qid":"2","aid":"1"}]}
            //profession,school,age,smoking,gender,pets,aboutme
            object.put("method", "update_profile");
            object.put("fname", "" + vars[0]);
            object.put("lname", "" + vars[1]);
            object.put("profession", "" + vars[2]);
            object.put("school", "" + vars[3]);
            object.put("age", "" + vars[4]);
            object.put("smoking", "" + vars[5]);
            object.put("pets", "" + vars[6]);
            object.put("aboutme", "" + vars[7]);
            object.put("user_id", "" + vars[8]);
            object.put("questions", "" + vars[9]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toGetRoomDetail(String id) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", "room_details");
            object.put("nRoomId", "" + id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    //login_with_fb
    public JSONObject toFbLogin(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.FBLOGIN);
            object.put("email", vars[0]);
            object.put("password", vars[1]);
            object.put("lat", vars[2]);
            object.put("long", vars[3]);
            object.put("device_type", vars[4]);
            object.put("device_id", vars[5]);
            object.put("fb_id", vars[6]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetAllAreas(int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_all_areas");
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toCategoryList(int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_all_categories");
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toAddActiveArea(String userId, String areaId, String lat, String lng) {
        //add_active_area
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "add_active_area");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("area_id", "" + areaId);
            jsonObject.put("lat", "" + lat);
            jsonObject.put("long", "" + lng);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toRemoveActivrArea(String areaId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "remove_active_area");
            jsonObject.put("a_id", "" + areaId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toAddFavCategory(String userId, String catId) {
        //add_fav_category
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "add_fav_category");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("cat_id", "" + catId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toRemoveFavCategory(String catId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "remove_fav_category");
            jsonObject.put("id", "" + catId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONObject toGetFavCategory(String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_fav_categories");
            jsonObject.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetCoupnsByCategory(String userId, String catId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_coupons_by_category");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("cat_id", "" + catId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetNotification(String userId, int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_my_notifications");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetMyListSaved(String userId, int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_saved_coupons");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetUserShops(String userId, int page, int type) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_user_shops");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);
            jsonObject.put("type", "" + type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetReviewed(String userId, int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_reviewed_shops");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetCouponById(String couponId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_coupon_by_id");
            jsonObject.put("id", "" + couponId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toSignup(String... vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "signup");
            jsonObject.put("fname", "" + vars[0]);
            jsonObject.put("lname", "" + vars[1]);
            jsonObject.put("email", "" + vars[2]);
            jsonObject.put("password", "" + vars[3]);
            jsonObject.put("gender", "" + vars[4]);
            jsonObject.put("birthday", "" + vars[5]);
            jsonObject.put("lat", "" + vars[6]);
            jsonObject.put("long", "" + vars[7]);
            jsonObject.put("recieve_email", "" + vars[8]);
            jsonObject.put("device_type", "" + vars[9]);
            jsonObject.put("device_id", "" + vars[10]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toFbSignup(String... vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "signup");
            jsonObject.put("fname", "" + vars[0]);
            jsonObject.put("lname", "" + vars[1]);
            jsonObject.put("email", "" + vars[2]);
            jsonObject.put("password", "" + vars[3]);
            jsonObject.put("gender", "" + vars[4]);
            jsonObject.put("birthday", "" + vars[5]);
            jsonObject.put("lat", "" + vars[6]);
            jsonObject.put("long", "" + vars[7]);
            jsonObject.put("recieve_email", "" + vars[8]);
            jsonObject.put("device_type", "" + vars[9]);
            jsonObject.put("device_id", "" + vars[10]);
            jsonObject.put("fb_id", "" + vars[11]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetNearByCoupons(String lat, String lng, String keyword) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_nearby_coupons");
            jsonObject.put("lat", "" + lat);
            jsonObject.put("long", "" + lng);
            jsonObject.put("keyword", "" + keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetShop(String shopId, String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_shop");
            jsonObject.put("shop_id", "" + shopId);
            jsonObject.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONObject toGetFavShop(String shopId, String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "add_fav_shop");
            jsonObject.put("shop_id", "" + shopId);
            jsonObject.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetRemoveFavShop(String Id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "remove_shop_from_list");
            jsonObject.put("id", "" + Id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetReviewShop(String shopId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_shop_reviews");
            jsonObject.put("shop_id", "" + shopId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toLogout(String UserId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "logout");
            jsonObject.put("user_id", "" + UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetChangePassword(String UserId, String OldPassword, String NewPassword) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "change_password");
            jsonObject.put("user_id", "" + UserId);
            jsonObject.put("old_password", "" + OldPassword);
            jsonObject.put("new_password", "" + NewPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetUpdateProfile(String UserId, String fname, String lname) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "update_profile");
            jsonObject.put("user_id", "" + UserId);
            jsonObject.put("fname", "" + fname);
            jsonObject.put("lname", "" + lname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONObject toGetProfileUpdate(String UserId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "profile");
            jsonObject.put("user_id", "" + UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetCouponSave(String... vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "save_coupon");
            jsonObject.put("user_id", "" + vars[0]);
            jsonObject.put("coupon_id", "" + vars[1]);
            jsonObject.put("name", "" + vars[2]);
            jsonObject.put("instruction", "" + vars[3]);
            jsonObject.put("shop_name", "" + vars[4]);
            jsonObject.put("shop_details", "" + vars[5]);
            jsonObject.put("description", "" + vars[6]);
            jsonObject.put("imageURL", "" + vars[7]);
            jsonObject.put("end_date", "" + vars[8]);
            jsonObject.put("cat_name", "" + vars[9]);
            jsonObject.put("area", "" + vars[10]);
            jsonObject.put("rating", "" + vars[11]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toGetSelectFavCategory(String UserId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_categories");
            jsonObject.put("user_id", "" + UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONObject toGetSelectFavArea(String UserId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_all_areas");
            jsonObject.put("user_id", "" + UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toMyProduct(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.MYPRODUCT);
            object.put("vendor_id", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toProductDetails(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_DETAIL);
            object.put("user_id", vars[0]);
            object.put("product_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toProductDelete(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_DELETE);
            object.put("vendor_id", vars[0]);
            object.put("product_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


    public JSONObject toForgotPassword(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.FORGOT_PASSWORD);
            object.put("email", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toSearchProduct(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.SEARCH_PRODUCT);
            object.put("user_id", vars[0]);
            object.put("page", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    //fname,lname,email,password,phone_no,licence_image,licence_no
    public List<Pair<String, String>> toVendorRegistration(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("fname", vars[0]));
        pairList.add(new Pair<>("lname", vars[1]));
        pairList.add(new Pair<>("email", vars[2]));
        pairList.add(new Pair<>("password", vars[3]));
        pairList.add(new Pair<>("phone_no", vars[4]));
        pairList.add(new Pair<>("licence_no", vars[5]));
        return pairList;
    }

    //fname,lname,email,password,phone_no
    public List<Pair<String, String>> toCustomerRegistration(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("fname", vars[0]));
        pairList.add(new Pair<>("lname", vars[1]));
        pairList.add(new Pair<>("email", vars[2]));
        pairList.add(new Pair<>("password", vars[3]));
        pairList.add(new Pair<>("phone_no", vars[4]));
        return pairList;
    }

    //vendor_id,name,description,qty,price,shipping_charge
    public List<Pair<String, String>> toAddProduct(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("vendor_id", vars[0]));
        pairList.add(new Pair<>("name", vars[1]));
        pairList.add(new Pair<>("description", vars[2]));
        pairList.add(new Pair<>("qty", vars[3]));
        pairList.add(new Pair<>("price", vars[4]));
        pairList.add(new Pair<>("shipping_charge", vars[5]));

        return pairList;
    }


    //vendor_id,name,description,qty,price,shipping_charge
    public List<Pair<String, String>> toUpdateProduct(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("product_id", vars[0]));
        pairList.add(new Pair<>("vendor_id", vars[1]));
        pairList.add(new Pair<>("name", vars[2]));
        pairList.add(new Pair<>("description", vars[3]));
        pairList.add(new Pair<>("qty", vars[4]));
        pairList.add(new Pair<>("price", vars[5]));
        pairList.add(new Pair<>("shipping_charge", vars[6]));

        return pairList;
    }

    public JSONObject toReviewRatings(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.REVIEWS_RATINGS);
            object.put("user_id", vars[0]);
            object.put("product_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toProductHistory(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_ORDER);
            object.put("user_id", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toProductLike(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_DISLIKE);
            object.put("user_id", vars[0]);
            object.put("review_id", vars[1]);
            object.put("action", vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toStartChat(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.START_CHAT);
            object.put("user_id", vars[0]);
            object.put("vendor_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public List<Pair<String, String>> toSendChat(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("user_id", vars[0]));
        pairList.add(new Pair<>("chat_id", vars[1]));
        pairList.add(new Pair<>("message", vars[2]));

        return pairList;
    }

    public JSONObject toUpdataeDevice(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.UPDATE_DETAILS);
            object.put("user_id", vars[0]);
            object.put("device_id", vars[1]);
            object.put("device_type", vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetConversation(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.HISTORY_CHAT);
            object.put("chat_id", vars[0]);
            object.put("page", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

}

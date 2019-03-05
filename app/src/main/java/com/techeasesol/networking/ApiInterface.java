package com.techeasesol.networking;


import com.techeasesol.myface.models.SignUpDataModels.SignupResponseModel;
import com.techeasesol.myface.models.loginDataModels.LoginResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by eapple on 29/08/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<SignupResponseModel> userSignup(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("password") String password,
                                         @Field("latitude") String latitude,
                                         @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> userLogin(@Field("email") String email,
                                       @Field("password") String password);


//    @FormUrlEncoded
//    @POST("verifyCode")
//    Call<VerifyResponseModel> userVerification(@Field("code") String code);
//
//    @POST("sendCode")
//    Call<ResendCodeModel> resendCode();
//
//    @FormUrlEncoded
//    @POST("getItems")
//    Call<ItemResponseModel> getItems(@Field("latitude") String lat,
//                                     @Field("longitude") String lng);
//
//    @GET("getItem?")
//    Call<ItemResponseModel> getResturants(@Query("id") int id);
//
//    @GET("userProfile")
//    Call<ProfileResponseModel> getUserProfile();
//
//    @FormUrlEncoded
//    @POST("updateProfile")
//    Call<ProfileUpdateResponseModel> updateProfile(@Field("name") String name,
//                                                   @Field("phoneNumber") String number,
//                                                   @Field("email") String email);
//
//    @Multipart
//    @POST("updateProfilePicture")
//    Call<ProfileImageResponseModel> updateProfilePicture(@Part MultipartBody.Part photo,
//                                                         @Part("profilePicture") RequestBody fileName);
//    @FormUrlEncoded
//    @POST("getItems/search")
//    Call<ItemResponseModel> getFiltersItems(@Field("price") String category,
//                                            @Field("category") String price,
//                                            @Field("latitude") String lat,
//                                            @Field("longitude") String lng);
//
//
//    @FormUrlEncoded
//    @POST("resetPassword")
//    Call<ForgotPasswordModel> resetPassword(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("resetPasswordVerify")
//    Call<VerifyCodeResponseModel> verifyPasswordCode(@Field("code") String code,
//                                                     @Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("changePassword")
//    Call<ChangePasswordModel> changePassword(@Field("newPassword") String code,
//                                             @Field("confirmPassword") String email);
//
//    @FormUrlEncoded
//    @POST("addMoreInfo")
//    Call<MoreInfoProfileModel> addMoreInfo(@Field("gender") String code,
//                                           @Field("age") String age,
//                                           @Field("height") String height,
//                                           @Field("weight") String weight,
//                                           @Field("profession") String profession,
//                                           @Field("lifeStyle") String lifeStyle,
//                                           @Field("address") String address);
//
//    @FormUrlEncoded
//    @POST("addTravelInfo")
//    Call<TravelReponseModel> travelInfo(@Field("travel") String walk);


    //use this for query request
//    @GET("getItems/search?")
//    Call<ItemResponseModel> getFiltersItems(@Query("price") String category,
//                                            @Query("category") String price,
//                                            @Query("latitude") String lat,
//                                            @Query("longitude") String lng);


}

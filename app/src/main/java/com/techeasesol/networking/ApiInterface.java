package com.techeasesol.networking;


import com.techeasesol.myface.models.SignUpDataModels.SignupResponseModel;
import com.techeasesol.myface.models.acceptedCardsDataModel.AcceptdCardResponseModel;
import com.techeasesol.myface.models.acceptedCardsDataModel.AcceptedCardStatusModel;
import com.techeasesol.myface.models.cardStatusDataModels.CardStatusResponseModel;
import com.techeasesol.myface.models.profileDataModels.ProfileResponseModel;
import com.techeasesol.myface.models.profileUpdateDataModel.ProfileImageResponseModel;
import com.techeasesol.myface.models.profileUpdateDataModel.ProfileUpdateResponseModel;
import com.techeasesol.myface.models.resetPasswordDataModels.ChangePasswordModel;
import com.techeasesol.myface.models.resetPasswordDataModels.ResetPasswordModel;
import com.techeasesol.myface.models.resetPasswordDataModels.verifycodemodel.VerifyCodeResponseModel;
import com.techeasesol.myface.models.saveCardDataModel.SaveCardResponseModel;
import com.techeasesol.myface.models.sendCardDataModel.SendCardResponseModel;
import com.techeasesol.myface.models.shareCardDataModels.ShareResponseModel;
import com.techeasesol.myface.models.updateCardDataModel.AddCardResponseModel;
import com.techeasesol.myface.models.cardDataModel.CardResponseModel;
import com.techeasesol.myface.models.loginDataModels.LoginResponseModel;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleResponseModel;

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
                                         @Field("longitude") String longitude,
                                         @Field("deviceToken") String deviceToken);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> userLogin(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("latitude") String latitude,
                                       @Field("longitude") String longitude,
                                       @Field("deviceToken") String deviceToken);

    @GET("usersNearBy")
    Call<NearPeopleResponseModel> nearPeoples();

    @GET("getCardDetail?")
    Call<CardResponseModel> getCardDetail(@Query("id") int id);

    @FormUrlEncoded
    @POST("saveCard?")
    Call<CardResponseModel> saveCard(@Field("id") int id);

    @FormUrlEncoded
    @POST("sharedCard")
    Call<SendCardResponseModel> shareCard(@Field("shareWithUserId") int userID,
                                          @Field("userCardId") int userCardID);

    @Multipart
    @POST("addCardDetail")
    Call<AddCardResponseModel> updateCard(@Part("cardNumber") int id,
                                          @Part("name") RequestBody name,
                                          @Part("phoneNumber") RequestBody number,
                                          @Part("email") RequestBody email,
                                          @Part("designation") RequestBody password,
                                          @Part("address") RequestBody passwordConfirmation,
                                          @Part("facebook") RequestBody phoneNumber,
                                          @Part("twitter") RequestBody deviceType,
                                          @Part("instagram") RequestBody lat,
                                          @Part("linkedin") RequestBody lon,
                                          @Part("skype") RequestBody skype,
                                          @Part("youtube") RequestBody youtube,
                                          @Part MultipartBody.Part photo,
                                          @Part("picture") RequestBody fileName);

    @GET("getSavedCards")
    Call<SaveCardResponseModel> savedCards();

    @GET("getSharedCards")
    Call<AcceptdCardResponseModel> acceptedCards();

    @FormUrlEncoded
    @POST("updateShareCardStatus")
    Call<CardStatusResponseModel> acceptDeleteCard(@Field("id") int ID,
                                                   @Field("status") int status);

    @FormUrlEncoded
    @POST("resetPassword")
    Call<ResetPasswordModel> resetPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("resetPasswordVerify")
    Call<VerifyCodeResponseModel> verifyPasswordCode(@Field("code") String code,
                                                     @Field("email") String email);

    @FormUrlEncoded
    @POST("changePassword")
    Call<ChangePasswordModel> changePassword(@Field("newPassword") String code,
                                             @Field("confirmPassword") String email);

    @GET("userProfile")
    Call<ProfileResponseModel> getUserProfile();

    @FormUrlEncoded
    @POST("update-profile")
    Call<ProfileUpdateResponseModel> updateProfile(@Field("name") String name,
                                                   @Field("phoneNumber") String number);

    @Multipart
    @POST("update-profile-picture")
    Call<ProfileImageResponseModel> updateProfilePicture(@Part MultipartBody.Part photo,
                                                         @Part("profilePicture") RequestBody fileName);



    //use this for query request
//    @GET("getItems/search?")
//    Call<ItemResponseModel> getFiltersItems(@Query("price") String category,
//                                            @Query("category") String price,
//                                            @Query("latitude") String lat,
//                                            @Query("longitude") String lng);


}

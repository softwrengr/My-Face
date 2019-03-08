package com.techeasesol.myface.models.loginDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profilePicture")
    @Expose
    private Object profilePicture;
    @SerializedName("phoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("designation")
    @Expose
    private Object designation;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("facebook")
    @Expose
    private Object facebook;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("youtube")
    @Expose
    private Object youtube;
    @SerializedName("instagram")
    @Expose
    private Object instagram;
    @SerializedName("skype")
    @Expose
    private Object skype;
    @SerializedName("linkedin")
    @Expose
    private Object linkedin;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("deviceType")
    @Expose
    private Object deviceType;
    @SerializedName("deviceToken")
    @Expose
    private Object deviceToken;
    @SerializedName("accountStatus")
    @Expose
    private String accountStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("notificationStatus")
    @Expose
    private String notificationStatus;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getDesignation() {
        return designation;
    }

    public void setDesignation(Object designation) {
        this.designation = designation;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getFacebook() {
        return facebook;
    }

    public void setFacebook(Object facebook) {
        this.facebook = facebook;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public Object getYoutube() {
        return youtube;
    }

    public void setYoutube(Object youtube) {
        this.youtube = youtube;
    }

    public Object getInstagram() {
        return instagram;
    }

    public void setInstagram(Object instagram) {
        this.instagram = instagram;
    }

    public Object getSkype() {
        return skype;
    }

    public void setSkype(Object skype) {
        this.skype = skype;
    }

    public Object getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(Object linkedin) {
        this.linkedin = linkedin;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Object deviceType) {
        this.deviceType = deviceType;
    }

    public Object getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(Object deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

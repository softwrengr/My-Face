package com.techeasesol.myface.models.SignUpDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCardDataModel {
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("card_number")
    @Expose
    private String cardNumber;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("instagram")
    @Expose
    private Object instagram;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("skype")
    @Expose
    private String skype;
    @SerializedName("linkedin")
    @Expose
    private Object linkedin;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Object getInstagram() {
        return instagram;
    }

    public void setInstagram(Object instagram) {
        this.instagram = instagram;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public Object getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(Object linkedin) {
        this.linkedin = linkedin;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

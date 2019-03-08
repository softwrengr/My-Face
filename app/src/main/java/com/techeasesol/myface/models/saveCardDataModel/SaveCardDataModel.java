package com.techeasesol.myface.models.saveCardDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveCardDataModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("card_number")
    @Expose
    private String cardNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("saved")
    @Expose
    private Integer saved;
    @SerializedName("picture")
    @Expose
    private String picture;
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
    private Object facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("skype")
    @Expose
    private String skype;
    @SerializedName("linkedin")
    @Expose
    private Object linkedin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public Integer getSaved() {
        return saved;
    }

    public void setSaved(Integer saved) {
        this.saved = saved;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Object getFacebook() {
        return facebook;
    }

    public void setFacebook(Object facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
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
}

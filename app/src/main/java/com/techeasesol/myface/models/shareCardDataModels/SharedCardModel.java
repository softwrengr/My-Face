package com.techeasesol.myface.models.shareCardDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharedCardModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userCard_id")
    @Expose
    private String userCardId;
    @SerializedName("share_to_user_id")
    @Expose
    private String shareToUserId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusUpdatedAt")
    @Expose
    private Object statusUpdatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("receiver_user")
    @Expose
    private RecieverUserModel receiverUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getShareToUserId() {
        return shareToUserId;
    }

    public void setShareToUserId(String shareToUserId) {
        this.shareToUserId = shareToUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public void setStatusUpdatedAt(Object statusUpdatedAt) {
        this.statusUpdatedAt = statusUpdatedAt;
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

    public RecieverUserModel getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(RecieverUserModel receiverUser) {
        this.receiverUser = receiverUser;
    }
}

package com.techeasesol.myface.models.sendCardDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendCardDataModel {
    @SerializedName("userCard_id")
    @Expose
    private String userCardId;
    @SerializedName("share_to_user_id")
    @Expose
    private String shareToUserId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

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

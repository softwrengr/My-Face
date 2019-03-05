package com.techeasesol.myface.models.loginDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user")
    @Expose
    private UserDetailModel userDetailModel;

    public UserDetailModel getUser() {
        return userDetailModel;
    }

    public void setUser(User user) {
        this.userDetailModel = userDetailModel;
    }
}

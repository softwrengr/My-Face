package com.techeasesol.myface.models.resetPasswordDataModels.verifycodemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyDataModel {
    @SerializedName("user")
    @Expose
    private VerifyUserModel user;

    public VerifyUserModel getUser() {
        return user;
    }

    public void setUser(VerifyUserModel user) {
        this.user = user;
    }
}

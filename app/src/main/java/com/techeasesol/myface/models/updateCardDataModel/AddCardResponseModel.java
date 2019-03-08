package com.techeasesol.myface.models.updateCardDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.techeasesol.myface.models.SignUpDataModels.UpdateCardDataModel;

public class AddCardResponseModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AddCardDataModel data;
    @SerializedName("code")
    @Expose
    private Integer code;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddCardDataModel getData() {
        return data;
    }

    public void setData(AddCardDataModel data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}


package com.sss.testproject.userLogin.loginModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("dataArray")
    @Expose
    private List<DataArray> dataArray = null;
    @SerializedName("userid")
    @Expose
    private Integer userid;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataArray> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<DataArray> dataArray) {
        this.dataArray = dataArray;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}

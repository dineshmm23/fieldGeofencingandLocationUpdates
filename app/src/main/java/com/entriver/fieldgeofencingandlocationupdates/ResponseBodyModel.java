package com.entriver.fieldgeofencingandlocationupdates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fieldPro on 8/22/2018.
 */
public class ResponseBodyModel implements Serializable {

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    public int getWoid() {
        return woid;
    }

    public void setWoid(int woid) {
        this.woid = woid;
    }

    public String getWoStatus() {
        return woStatus;
    }

    public void setWoStatus(String woStatus) {
        this.woStatus = woStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName(value = "msg", alternate = {"message"})
    @Expose
    private String message;
    @SerializedName("location")
    @Expose
    private String locationStatus;
    @SerializedName("wo_id")
    @Expose
    private int woid;
    @SerializedName("wo_status")
    @Expose
    private String woStatus;
    @SerializedName("Status")
    @Expose
    private String status;

}

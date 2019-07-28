package com.smartcard.iotaspirantsvendor.data.model;

import com.google.gson.annotations.SerializedName;

public class AbstractResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("responseCode")
    private int responseCode;

}

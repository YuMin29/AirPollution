package com.yumin.airpollution.data;

import com.google.gson.annotations.SerializedName;

public class Extras {
    @SerializedName("api_key")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}

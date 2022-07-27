package com.yumin.airpollution.data;

import com.google.gson.annotations.SerializedName;

public class Fields {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("info")
    private Info info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

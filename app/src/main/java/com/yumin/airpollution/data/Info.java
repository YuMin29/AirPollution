package com.yumin.airpollution.data;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("label")
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

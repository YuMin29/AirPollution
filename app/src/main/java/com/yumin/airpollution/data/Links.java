package com.yumin.airpollution.data;

import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("start")
    private String start;
    @SerializedName("next")
    private String next;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}

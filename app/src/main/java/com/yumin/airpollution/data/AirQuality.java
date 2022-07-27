package com.yumin.airpollution.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AirQuality {
    @SerializedName("resource_id")
    private String resourceId;
    @SerializedName("__extras")
    private Extras extras;
    @SerializedName("include_total")
    private Boolean includeTotal;
    @SerializedName("total")
    private String total;
    @SerializedName("resource_format")
    private String resourceFormat;
    @SerializedName("limit")
    private String limit;
    @SerializedName("offset")
    private String offset;
    @SerializedName("_links")
    private Links links;
    @SerializedName("fields")
    private List<Fields> fields;
    @SerializedName("records")
    private List<Records> records;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public Boolean isIncludeTotal() {
        return includeTotal;
    }

    public void setIncludeTotal(Boolean includeTotal) {
        this.includeTotal = includeTotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getResourceFormat() {
        return resourceFormat;
    }

    public void setResourceFormat(String resourceFormat) {
        this.resourceFormat = resourceFormat;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }
}

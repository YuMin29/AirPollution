package com.yumin.airpollution.data;

import com.google.gson.annotations.SerializedName;

public class Records {
    @SerializedName("sitename")
    private String siteName;
    @SerializedName("county")
    private String county;
    @SerializedName("aqi")
    private String aqi;
    @SerializedName("pollutant")
    private String pollutant;
    @SerializedName("status")
    private String status;
    @SerializedName("so2")
    private String so2;
    @SerializedName("co")
    private String co;
    @SerializedName("o3")
    private String o3;
    @SerializedName("o3_8hr")
    private String o38hr;
    @SerializedName("pm10")
    private String pm10;
    @SerializedName("pm2.5")
    private String pm25;
    @SerializedName("no2")
    private String no2;
    @SerializedName("nox")
    private String nox;
    @SerializedName("no")
    private String no;
    @SerializedName("wind_speed")
    private String windSpeed;
    @SerializedName("wind_direc")
    private String windDirec;
    @SerializedName("publishtime")
    private String publishTime;
    @SerializedName("co_8hr")
    private String co8hr;
    @SerializedName("pm2.5_avg")
    private String pm25Avg;
    @SerializedName("pm10_avg")
    private String pm10Avg;
    @SerializedName("so2_avg")
    private String so2Avg;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("siteid")
    private String siteId;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPollutant() {
        return pollutant;
    }

    public void setPollutant(String pollutant) {
        this.pollutant = pollutant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO38hr() {
        return o38hr;
    }

    public void setO38hr(String o38hr) {
        this.o38hr = o38hr;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getNox() {
        return nox;
    }

    public void setNox(String nox) {
        this.nox = nox;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirec() {
        return windDirec;
    }

    public void setWindDirec(String windDirec) {
        this.windDirec = windDirec;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCo8hr() {
        return co8hr;
    }

    public void setCo8hr(String co8hr) {
        this.co8hr = co8hr;
    }

    public String getPm25Avg() {
        return pm25Avg;
    }

    public void setPm25Avg(String pm25Avg) {
        this.pm25Avg = pm25Avg;
    }

    public String getPm10Avg() {
        return pm10Avg;
    }

    public void setPm10Avg(String pm10Avg) {
        this.pm10Avg = pm10Avg;
    }

    public String getSo2Avg() {
        return so2Avg;
    }

    public void setSo2Avg(String so2Avg) {
        this.so2Avg = so2Avg;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}

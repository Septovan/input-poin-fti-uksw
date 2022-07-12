package com.example.inputpointfti.models.publicapi;

import com.google.gson.annotations.SerializedName;

public class Entry {
    @SerializedName("API")
    private String apiName;

    @SerializedName("Description")
    private String description;

    @SerializedName("Auth")
    private String auth;

    @SerializedName("HTTPS")
    private boolean isHttps;

    @SerializedName("Cors")
    private String corsStatus;

    @SerializedName("Link")
    private String link;

    @SerializedName("Category")
    private String category;

    public Entry(String apiName, String description, String auth, boolean isHttps, String corsStatus, String link, String category) {
        this.apiName = apiName;
        this.description = description;
        this.auth = auth;
        this.isHttps = isHttps;
        this.corsStatus = corsStatus;
        this.link = link;
        this.category = category;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public boolean isHttps() {
        return isHttps;
    }

    public void setHttps(boolean https) {
        isHttps = https;
    }

    public String getCorsStatus() {
        return corsStatus;
    }

    public void setCorsStatus(String corsStatus) {
        this.corsStatus = corsStatus;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

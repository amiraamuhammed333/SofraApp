package com.example.sofraapp.data.model.GeneralResponse;

import com.google.gson.annotations.SerializedName;

public class GeneralResponseData {

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("city")
    private GeneralResponseData city;

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private Object createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("city_id")
    private String cityId;

    public GeneralResponseData(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public GeneralResponseData(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setCity(GeneralResponseData city) {
        this.city = city;
    }

    public GeneralResponseData getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    @Override
    public String toString() {
        return
                "GeneralResponseData{" +
                        "updated_at = '" + updatedAt + '\'' +
                        ",city = '" + city + '\'' +
                        ",name = '" + name + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",city_id = '" + cityId + '\'' +
                        "}";
    }
}
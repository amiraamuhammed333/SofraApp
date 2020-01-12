package com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category;

public class CategotyResaurantResponse {
    private String msg;
    private categoryRestaurantPagination data;
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(categoryRestaurantPagination data) {
        this.data = data;
    }

    public categoryRestaurantPagination getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "CategotyResaurantResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}

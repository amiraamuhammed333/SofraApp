package com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item;

import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantMealsItemPagination;
import com.google.gson.annotations.SerializedName;

public class RestaurantMealsItemResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private RestaurantMealsItemPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(RestaurantMealsItemPagination data){
		this.data = data;
	}

	public RestaurantMealsItemPagination getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RestaurantMealsItemResponse{" +
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
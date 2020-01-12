package com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant;

import com.google.gson.annotations.SerializedName;

public class RestaurantResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private ResataurantData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(ResataurantData data){
		this.data = data;
	}

	public ResataurantData getData(){
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
			"RestaurantDataResponse{" +
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
package com.example.sofraapp.data.model.restaurant;

import com.google.gson.annotations.SerializedName;

public class RestaurantDataResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private Restaurantpagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(Restaurantpagination data){
		this.data = data;
	}

	public Restaurantpagination getData(){
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
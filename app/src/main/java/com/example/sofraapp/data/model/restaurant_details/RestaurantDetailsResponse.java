package com.example.sofraapp.data.model.restaurant_details;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailsResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private RstaurantDetailsPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(RstaurantDetailsPagination data){
		this.data = data;
	}

	public RstaurantDetailsPagination getData(){
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
			"RestaurantDetailsResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
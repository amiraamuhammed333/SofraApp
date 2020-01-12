package com.example.sofraapp.data.model.restaurantt.restaurant_item.review;

import com.google.gson.annotations.SerializedName;

public class ReviewRestaurantResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private ReviewRestaurantPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(ReviewRestaurantPagination data){
		this.data = data;
	}

	public ReviewRestaurantPagination getData(){
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
			"ReviewRestaurantResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
package com.example.sofraapp.data.model.order_restaurant.order_restaurant;

import com.google.gson.annotations.SerializedName;

public class OrderRestaurantResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OrderRestaurantPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OrderRestaurantPagination data){
		this.data = data;
	}

	public OrderRestaurantPagination getData(){
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
			"OrderRestaurantResponse{" +
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
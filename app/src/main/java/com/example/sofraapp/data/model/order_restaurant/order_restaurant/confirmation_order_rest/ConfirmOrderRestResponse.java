package com.example.sofraapp.data.model.order_restaurant.order_restaurant.confirmation_order_rest;

import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.google.gson.annotations.SerializedName;

public class ConfirmOrderRestResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OrderRestaurantData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OrderRestaurantData data){
		this.data = data;
	}

	public OrderRestaurantData getData(){
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
			"ConfirmOrderRestResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
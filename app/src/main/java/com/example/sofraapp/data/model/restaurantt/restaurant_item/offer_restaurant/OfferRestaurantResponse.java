package com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant;

import com.google.gson.annotations.SerializedName;

public class OfferRestaurantResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OfferRestaurantPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OfferRestaurantPagination data){
		this.data = data;
	}

	public OfferRestaurantPagination getData(){
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
			"OfferRestaurantResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
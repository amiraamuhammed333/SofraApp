package com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant;

import com.google.gson.annotations.SerializedName;

public class OfferupdateRestaurantResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OfferRestaurantData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OfferRestaurantData data){
		this.data = data;
	}

	public OfferRestaurantData getData(){
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
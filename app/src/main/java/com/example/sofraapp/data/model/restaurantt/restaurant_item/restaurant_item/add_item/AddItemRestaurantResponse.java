package com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.add_item;

import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantDataMealsItem;
import com.google.gson.annotations.SerializedName;

public class AddItemRestaurantResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private RestaurantDataMealsItem data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(RestaurantDataMealsItem data){
		this.data = data;
	}

	public RestaurantDataMealsItem getData(){
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
			"AddItemRestaurantResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
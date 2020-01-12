package com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.new_category;

import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;
import com.google.gson.annotations.SerializedName;

public class NewCategoryRestaurantResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private CategoryRestaurantDataItem data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(CategoryRestaurantDataItem data){
		this.data = data;
	}

	public CategoryRestaurantDataItem getData(){
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
			"NewCategoryRestaurantResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
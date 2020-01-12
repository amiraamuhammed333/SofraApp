package com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.update_category;

import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;

public class UpdateCategoryResponse {
	private String msg;
	private CategoryRestaurantDataItem data;
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
			"UpdateCategoryResponse{" +
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}

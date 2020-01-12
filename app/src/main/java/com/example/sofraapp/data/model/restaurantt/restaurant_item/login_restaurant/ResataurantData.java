package com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant;

import com.google.gson.annotations.SerializedName;

public class ResataurantData {

	@SerializedName("api_token")
	private String apiToken;

	@SerializedName("user")
	private Restaurant user;

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	public void setUser(Restaurant user){
		this.user = user;
	}

	public Restaurant getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"ResataurantData{" +
			"api_token = '" + apiToken + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}
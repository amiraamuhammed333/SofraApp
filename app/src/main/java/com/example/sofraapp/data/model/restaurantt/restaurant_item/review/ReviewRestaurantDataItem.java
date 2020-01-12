package com.example.sofraapp.data.model.restaurantt.restaurant_item.review;

import com.example.sofraapp.data.model.client.login_client.Client;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.Restaurant;
import com.google.gson.annotations.SerializedName;

public class ReviewRestaurantDataItem {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("rate")
	private String rate;

	@SerializedName("restaurant_id")
	private String restaurantId;

	@SerializedName("restaurant")
	private Restaurant restaurant;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("client")
	private Client client;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id")
	private int id;

	@SerializedName("client_id")
	private String clientId;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setRestaurantId(String restaurantId){
		this.restaurantId = restaurantId;
	}

	public String getRestaurantId(){
		return restaurantId;
	}

	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant(){
		return restaurant;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setClient(Client client){
		this.client = client;
	}

	public Client getClient(){
		return client;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"ReviewRestaurantDataItem{" +
			"updated_at = '" + updatedAt + '\'' + 
			",rate = '" + rate + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",restaurant = '" + restaurant + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",client = '" + client + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			",client_id = '" + clientId + '\'' + 
			"}";
		}
}
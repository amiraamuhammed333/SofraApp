package com.example.sofraapp.data.model.client.client_review;

import com.example.sofraapp.data.model.client.login_client.Client;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.Restaurant;

public class ReviewClientData {
	private String updatedAt;
	private String rate;
	private String restaurantId;
	private Restaurant restaurant;
	private String createdAt;
	private Client client;
	private String comment;
	private int id;
	private int clientId;

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

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"ReviewClientData{" +
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

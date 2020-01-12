package com.example.sofraapp.data.model.offer_client.list_order_client;

import com.example.sofraapp.data.model.restaurant.RestaurantDataItem;
import com.google.gson.annotations.SerializedName;

public class OfferClientData {

	@SerializedName("restaurant_id")
	private String restaurantId;

	@SerializedName("starting_at")
	private String startingAt;

	@SerializedName("restaurant")
	private RestaurantDataItem restaurant;

	@SerializedName("available")
	private boolean available;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("photo")
	private String photo;

	@SerializedName("ending_at")
	private String endingAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private String price;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("photo_url")
	private String photoUrl;

	public void setRestaurantId(String restaurantId){
		this.restaurantId = restaurantId;
	}

	public String getRestaurantId(){
		return restaurantId;
	}

	public void setStartingAt(String startingAt){
		this.startingAt = startingAt;
	}

	public String getStartingAt(){
		return startingAt;
	}

	public void setRestaurant(RestaurantDataItem restaurant){
		this.restaurant = restaurant;
	}

	public RestaurantDataItem getRestaurant(){
		return restaurant;
	}

	public void setAvailable(boolean available){
		this.available = available;
	}

	public boolean isAvailable(){
		return available;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setEndingAt(String endingAt){
		this.endingAt = endingAt;
	}

	public String getEndingAt(){
		return endingAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPhotoUrl(String photoUrl){
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl(){
		return photoUrl;
	}

	@Override
 	public String toString(){
		return 
			"OfferClientData{" +
			"restaurant_id = '" + restaurantId + '\'' + 
			",starting_at = '" + startingAt + '\'' + 
			",restaurant = '" + restaurant + '\'' + 
			",available = '" + available + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",photo = '" + photo + '\'' + 
			",ending_at = '" + endingAt + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photoUrl + '\'' + 
			"}";
		}
}
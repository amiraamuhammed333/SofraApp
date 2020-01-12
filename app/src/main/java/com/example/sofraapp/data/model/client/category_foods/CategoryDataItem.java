package com.example.sofraapp.data.model.client.category_foods;

import com.google.gson.annotations.SerializedName;

public class CategoryDataItem {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("restaurant_id")
	private String restaurantId;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private int id;

	@SerializedName("photo_url")
	private String photoUrl;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setRestaurantId(String restaurantId){
		this.restaurantId = restaurantId;
	}

	public String getRestaurantId(){
		return restaurantId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
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
			"CategoryDataItem{" +
			"updated_at = '" + updatedAt + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",photo = '" + photo + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photoUrl + '\'' + 
			"}";
		}
}
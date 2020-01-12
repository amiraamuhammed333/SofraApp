package com.example.sofraapp.data.model.restaurant_details;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailsDataItem {

	@SerializedName("has_offer")
	private boolean hasOffer;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("price")
	private String price;

	@SerializedName("restaurant_id")
	private String restaurantId;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private int id;

	@SerializedName("photo_url")
	private String photoUrl;

	@SerializedName("offer_price")
	private String offerPrice;

	public void setHasOffer(boolean hasOffer){
		this.hasOffer = hasOffer;
	}

	public boolean isHasOffer(){
		return hasOffer;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
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

	public void setOfferPrice(String offerPrice){
		this.offerPrice = offerPrice;
	}

	public String getOfferPrice(){
		return offerPrice;
	}

	@Override
 	public String toString(){
		return 
			"RestaurantDetailsDataItem{" +
			"has_offer = '" + hasOffer + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",price = '" + price + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",photo = '" + photo + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photoUrl + '\'' +
			",offer_price = '" + offerPrice + '\'' +
			"}";
		}
}
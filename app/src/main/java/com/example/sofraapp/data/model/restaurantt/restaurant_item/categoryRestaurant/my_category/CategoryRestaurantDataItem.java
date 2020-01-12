package com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category;

public class
CategoryRestaurantDataItem {
	private String updatedAt;
	private String restaurantId;
	private String name;
	private String createdAt;
	private String photo;
	private int id;
	private String photo_url;

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

	public void setPhoto_url(String photo_url){
		this.photo_url = photo_url;
	}

	public String getPhoto_url(){
		return photo_url;
	}

	@Override
 	public String toString(){
		return 
			"CategoryRestaurantDataItem{" +
			"updated_at = '" + updatedAt + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",photo = '" + photo + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photo_url + '\'' +
			"}";
		}
}

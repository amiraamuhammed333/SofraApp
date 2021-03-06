package com.example.sofraapp.data.model.restaurant;

import com.example.sofraapp.data.model.GeneralResponse.GeneralResponseData;
import com.google.gson.annotations.SerializedName;

public class RestaurantDataItem {

	@SerializedName("whatsapp")
	private String whatsapp;

	@SerializedName("region_id")
	private String regionId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("photo")
	private String photo;

	@SerializedName("delivery_time")
	private String deliveryTime;

	@SerializedName("availability")
	private String availability;

	@SerializedName("delivery_cost")
	private String deliveryCost;

	@SerializedName("minimum_charger")
	private String minimumCharger;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("rate")
	private float rate;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("photo_url")
	private String photoUrl;

	@SerializedName("email")
	private String email;

	@SerializedName("activated")
	private String activated;

	@SerializedName("region")
	private GeneralResponseData region;

	public void setWhatsapp(String whatsapp){
		this.whatsapp = whatsapp;
	}

	public String getWhatsapp(){
		return whatsapp;
	}

	public void setRegionId(String regionId){
		this.regionId = regionId;
	}

	public String getRegionId(){
		return regionId;
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

	public void setDeliveryTime(String deliveryTime){
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryTime(){
		return deliveryTime;
	}

	public void setAvailability(String availability){
		this.availability = availability;
	}

	public String getAvailability(){
		return availability;
	}

	public void setDeliveryCost(String deliveryCost){
		this.deliveryCost = deliveryCost;
	}

	public String getDeliveryCost(){
		return deliveryCost;
	}

	public void setMinimumCharger(String minimumCharger){
		this.minimumCharger = minimumCharger;
	}

	public String getMinimumCharger(){
		return minimumCharger;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setRate(int rate){
		this.rate = rate;
	}

	public float getRate(){
		return rate;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setActivated(String activated){
		this.activated = activated;
	}

	public String getActivated(){
		return activated;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public GeneralResponseData getRegion() {
		return region;
	}

	public void setRegion(GeneralResponseData region) {
		this.region = region;
	}

	@Override
 	public String toString(){
		return 
			"RestaurantDetailsDataItem{" +
			"whatsapp = '" + whatsapp + '\'' + 
			",region_id = '" + regionId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",photo = '" + photo + '\'' + 
			",delivery_time = '" + deliveryTime + '\'' + 
			",availability = '" + availability + '\'' + 
			",delivery_cost = '" + deliveryCost + '\'' + 
			",minimum_charger = '" + minimumCharger + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",rate = '" + rate + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photoUrl + '\'' + 
			",email = '" + email + '\'' + 
			",activated = '" + activated + '\'' + 
			"}";
		}
}
package com.example.sofraapp.data.model.client.login_client;

import com.google.gson.annotations.SerializedName;

public class Client {

	@SerializedName("profile_image")
	private String profileImage;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("region_id")
	private String regionId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("photo_url")
	private String photoUrl;

	@SerializedName("region")
	private Region region;

	@SerializedName("email")
	private String email;

	public void setProfileImage(String profileImage){
		this.profileImage = profileImage;
	}

	public String getProfileImage(){
		return profileImage;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setRegion(Region region){
		this.region = region;
	}

	public Region getRegion(){
		return region;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Client{" +
			"profile_image = '" + profileImage + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",region_id = '" + regionId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photoUrl + '\'' + 
			",region = '" + region + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
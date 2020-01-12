package com.example.sofraapp.data.model.client.login_client;

import com.google.gson.annotations.SerializedName;

public class Region{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("city")
	private City city;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("city_id")
	private String cityId;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"Region{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",city = '" + city + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}
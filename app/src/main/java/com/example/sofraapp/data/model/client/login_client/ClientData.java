package com.example.sofraapp.data.model.client.login_client;

import com.google.gson.annotations.SerializedName;

public class ClientData {

	@SerializedName("api_token")
	private String apiToken;

	@SerializedName("user")
	private Client user;

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	public void setUser(Client user){
		this.user = user;
	}

	public Client getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"ClientData{" +
			"api_token = '" + apiToken + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}
package com.example.sofraapp.data.model.client.login_client;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("code")
	private int code;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	@Override
 	public String toString(){
		return 
			"DataConatct{" +
			"code = '" + code + '\'' + 
			"}";
		}
}
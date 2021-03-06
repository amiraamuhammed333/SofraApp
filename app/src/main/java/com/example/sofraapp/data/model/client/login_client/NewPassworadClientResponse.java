package com.example.sofraapp.data.model.client.login_client;

import com.google.gson.annotations.SerializedName;

public class NewPassworadClientResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private Object data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"NewPassworadClientResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
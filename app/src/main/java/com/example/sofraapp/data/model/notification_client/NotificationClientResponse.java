package com.example.sofraapp.data.model.notification_client;

import com.google.gson.annotations.SerializedName;

public class NotificationClientResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private NotificationClientPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(NotificationClientPagination data){
		this.data = data;
	}

	public NotificationClientPagination getData(){
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
			"NotificationClientResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
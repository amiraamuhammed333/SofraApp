package com.example.sofraapp.data.model.restaurantt.restaurant_item.notification_restaurant;

import com.google.gson.annotations.SerializedName;

public class NotificationRestResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private NotificationRestPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(NotificationRestPagination data){
		this.data = data;
	}

	public NotificationRestPagination getData(){
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
			"NotificationRestResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
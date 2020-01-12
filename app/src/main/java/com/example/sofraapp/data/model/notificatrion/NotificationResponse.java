package com.example.sofraapp.data.model.notificatrion;

import com.google.gson.annotations.SerializedName;

public class NotificationResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("send")
	private Object send;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}
	public String getMsg(){
		return msg;
	}

	public void setSend(Object send){
		this.send = send;
	}

	public Object getSend(){
		return send;
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
			"NotificationResponse{" + 
			"msg = '" + msg + '\'' + 
			",send = '" + send + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
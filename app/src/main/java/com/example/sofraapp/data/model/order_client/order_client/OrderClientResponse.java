package com.example.sofraapp.data.model.order_client.order_client;

import com.google.gson.annotations.SerializedName;

public class OrderClientResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OrderClientpagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OrderClientpagination data){
		this.data = data;
	}

	public OrderClientpagination getData(){
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
			"OrderClientResponse{" +
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
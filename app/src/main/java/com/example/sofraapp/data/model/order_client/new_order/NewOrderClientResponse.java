package com.example.sofraapp.data.model.order_client.new_order;

import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.google.gson.annotations.SerializedName;

public class NewOrderClientResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OrderClientData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OrderClientData data){
		this.data = data;
	}

	public OrderClientData getData(){
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
			"NewOrderClientResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
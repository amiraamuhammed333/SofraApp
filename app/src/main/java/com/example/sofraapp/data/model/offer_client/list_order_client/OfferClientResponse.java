package com.example.sofraapp.data.model.offer_client.list_order_client;

import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientPagination;
import com.google.gson.annotations.SerializedName;

public class OfferClientResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OfferClientPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OfferClientPagination data){
		this.data = data;
	}

	public OfferClientPagination getData(){
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
			"OfferClientResponse{" +
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
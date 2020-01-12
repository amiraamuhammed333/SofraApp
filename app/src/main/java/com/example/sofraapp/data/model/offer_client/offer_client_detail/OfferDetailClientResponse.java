package com.example.sofraapp.data.model.offer_client.offer_client_detail;

import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientData;
import com.google.gson.annotations.SerializedName;

public class OfferDetailClientResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OfferClientData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OfferClientData data){
		this.data = data;
	}

	public OfferClientData getData(){
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
			"OfferDetailClientResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
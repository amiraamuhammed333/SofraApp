package com.example.sofraapp.data.model.restaurantt.restaurant_item.commission;

import com.google.gson.annotations.SerializedName;

public class CommissionResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private CommissionData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(CommissionData data){
		this.data = data;
	}

	public CommissionData getData(){
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
			"CommissionResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
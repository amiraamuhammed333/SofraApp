package com.example.sofraapp.data.model.contact_us;

import com.google.gson.annotations.SerializedName;

public class ContactResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private DataConatct data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(DataConatct data){
		this.data = data;
	}

	public DataConatct getData(){
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
			"ContactResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
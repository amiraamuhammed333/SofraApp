package com.example.sofraapp.data.model.client.category_foods;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private List<CategoryDataItem> data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(List<CategoryDataItem> data){
		this.data = data;
	}

	public List<CategoryDataItem> getData(){
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
			"CategoryResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
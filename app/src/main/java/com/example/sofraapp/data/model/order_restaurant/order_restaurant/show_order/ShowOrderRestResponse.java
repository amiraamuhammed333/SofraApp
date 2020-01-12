package com.example.sofraapp.data.model.order_restaurant.order_restaurant.show_order;

import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;

public class ShowOrderRestResponse{
	private String msg;
	private OrderRestaurantData data;
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OrderRestaurantData data){
		this.data = data;
	}

	public OrderRestaurantData getData(){
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
			"ShowOrderRestResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}

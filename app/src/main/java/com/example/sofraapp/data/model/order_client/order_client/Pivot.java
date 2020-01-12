package com.example.sofraapp.data.model.order_client.order_client;

import com.google.gson.annotations.SerializedName;

public class Pivot{

	@SerializedName("note")
	private String note;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("item_id")
	private String itemId;

	@SerializedName("price")
	private String price;

	@SerializedName("order_id")
	private String orderId;

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setItemId(String itemId){
		this.itemId = itemId;
	}

	public String getItemId(){
		return itemId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	@Override
 	public String toString(){
		return 
			"Pivot{" + 
			"note = '" + note + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",item_id = '" + itemId + '\'' + 
			",price = '" + price + '\'' + 
			",order_id = '" + orderId + '\'' + 
			"}";
		}
}
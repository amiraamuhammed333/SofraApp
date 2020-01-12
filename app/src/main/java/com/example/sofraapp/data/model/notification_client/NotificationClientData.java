package com.example.sofraapp.data.model.notification_client;

import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.google.gson.annotations.SerializedName;

public class NotificationClientData {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("action")
	private String action;

	@SerializedName("title_en")
	private String titleEn;

	@SerializedName("id")
	private int id;

	@SerializedName("content_en")
	private String contentEn;

	@SerializedName("title")
	private String title;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("pivot")
	private Pivot pivot;

	@SerializedName("content")
	private String content;

	@SerializedName("order")
	private OrderClientData order;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}
	public void setPivot(Pivot pivot){
		this.pivot = pivot;
	}

	public Pivot getPivot(){
		return pivot;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
	}

	public void setTitleEn(String titleEn){
		this.titleEn = titleEn;
	}

	public String getTitleEn(){
		return titleEn;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setContentEn(String contentEn){
		this.contentEn = contentEn;
	}

	public String getContentEn(){
		return contentEn;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setOrder(OrderClientData order){
		this.order = order;
	}

	public OrderClientData getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"NotificationClientData{" +
			"updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",action = '" + action + '\'' + 
			",title_en = '" + titleEn + '\'' + 
			",id = '" + id + '\'' + 
			",content_en = '" + contentEn + '\'' + 
			",title = '" + title + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",content = '" + content + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}
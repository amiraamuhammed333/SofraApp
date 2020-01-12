package com.example.sofraapp.data.model.order_restaurant.order_restaurant;

import java.util.List;

import com.example.sofraapp.data.model.client.login_client.Client;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.Restaurant;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantDataMealsItem;
import com.google.gson.annotations.SerializedName;

public class OrderRestaurantData {

	@SerializedName("note")
	private String note;

	@SerializedName("address")
	private String address;

	@SerializedName("cost")
	private String cost;

	@SerializedName("restaurant_id")
	private String restaurantId;

	@SerializedName("refuse_reason")
	private String refuseReason;

	@SerializedName("restaurant")
	private Restaurant restaurant;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("payment_method_id")
	private String paymentMethodId;

	@SerializedName("total")
	private String total;

	@SerializedName("delivery_cost")
	private String deliveryCost;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("client")
	private Client client;

	@SerializedName("commission")
	private String commission;

	@SerializedName("id")
	private int id;

	@SerializedName("state")
	private String state;

	@SerializedName("net")
	private String net;

	@SerializedName("delivered_at")
	private Object deliveredAt;

	@SerializedName("items")
	private List<RestaurantDataMealsItem> items;

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCost(String cost){
		this.cost = cost;
	}

	public String getCost(){
		return cost;
	}

	public void setRestaurantId(String restaurantId){
		this.restaurantId = restaurantId;
	}

	public String getRestaurantId(){
		return restaurantId;
	}

	public void setRefuseReason(String refuseReason){
		this.refuseReason = refuseReason;
	}

	public String getRefuseReason(){
		return refuseReason;
	}

	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant(){
		return restaurant;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	public void setPaymentMethodId(String paymentMethodId){
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentMethodId(){
		return paymentMethodId;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setDeliveryCost(String deliveryCost){
		this.deliveryCost = deliveryCost;
	}

	public String getDeliveryCost(){
		return deliveryCost;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setClient(Client client){
		this.client = client;
	}

	public Client getClient(){
		return client;
	}

	public void setCommission(String commission){
		this.commission = commission;
	}

	public String getCommission(){
		return commission;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setNet(String net){
		this.net = net;
	}

	public String getNet(){
		return net;
	}

	public void setDeliveredAt(Object deliveredAt){
		this.deliveredAt = deliveredAt;
	}

	public Object getDeliveredAt(){
		return deliveredAt;
	}

	public void setItems(List<RestaurantDataMealsItem> items){
		this.items = items;
	}

	public List<RestaurantDataMealsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"OfferClientData{" +
			"note = '" + note + '\'' + 
			",address = '" + address + '\'' + 
			",cost = '" + cost + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",refuse_reason = '" + refuseReason + '\'' + 
			",restaurant = '" + restaurant + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",client_id = '" + clientId + '\'' + 
			",payment_method_id = '" + paymentMethodId + '\'' + 
			",total = '" + total + '\'' + 
			",delivery_cost = '" + deliveryCost + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",client = '" + client + '\'' + 
			",commission = '" + commission + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",net = '" + net + '\'' + 
			",delivered_at = '" + deliveredAt + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}
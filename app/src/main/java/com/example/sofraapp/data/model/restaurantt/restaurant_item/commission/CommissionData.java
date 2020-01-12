package com.example.sofraapp.data.model.restaurantt.restaurant_item.commission;

import com.google.gson.annotations.SerializedName;

public class CommissionData {

	@SerializedName("total")
	private String total;

	@SerializedName("commissions")
	private String commissions;

	@SerializedName("payments")
	private String payments;

	@SerializedName("net_commissions")
	private double netCommissions;

	@SerializedName("count")
	private int count;

	@SerializedName("commission")
	private String commission;

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setCommissions(String commissions){
		this.commissions = commissions;
	}

	public String getCommissions(){
		return commissions;
	}

	public void setPayments(String payments){
		this.payments = payments;
	}

	public String getPayments(){
		return payments;
	}

	public void setNetCommissions(double netCommissions){
		this.netCommissions = netCommissions;
	}

	public double getNetCommissions(){
		return netCommissions;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setCommission(String commission){
		this.commission = commission;
	}

	public String getCommission(){
		return commission;
	}

	@Override
 	public String toString(){
		return 
			"CommissionData{" +
			"total = '" + total + '\'' + 
			",commissions = '" + commissions + '\'' + 
			",payments = '" + payments + '\'' + 
			",net_commissions = '" + netCommissions + '\'' + 
			",count = '" + count + '\'' + 
			",commission = '" + commission + '\'' + 
			"}";
		}
}
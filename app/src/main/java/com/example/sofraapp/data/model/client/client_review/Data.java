package com.example.sofraapp.data.model.client.client_review;

public class Data{
	private ReviewClientData review;

	public void setReview(ReviewClientData review){
		this.review = review;
	}

	public ReviewClientData getReview(){
		return review;
	}

	@Override
 	public String toString(){
		return 
			"ReviewRestaurantPagination{" +
			"review = '" + review + '\'' + 
			"}";
		}
}

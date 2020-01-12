package com.example.sofraapp.data.api;


import com.example.sofraapp.data.model.GeneralResponse.GeneralResponse;
import com.example.sofraapp.data.model.about_app.AbourAppResponse;
import com.example.sofraapp.data.model.client.client_review.ReviewClientResponse;
import com.example.sofraapp.data.model.client.login_client.NewPassworadClientResponse;
import com.example.sofraapp.data.model.client.login_client.RsetPasswordResponse;
import com.example.sofraapp.data.model.contact_us.ContactResponse;
import com.example.sofraapp.data.model.notification_client.NotificationClientResponse;
import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientResponse;
import com.example.sofraapp.data.model.offer_client.offer_client_detail.OfferDetailClientResponse;
import com.example.sofraapp.data.model.order_client.confirm_order_client.ConfirmOredrClientResponse;
import com.example.sofraapp.data.model.order_client.decline_order_client.DeclineOredrClinetResponse;
import com.example.sofraapp.data.model.order_client.new_order.NewOrderClientResponse;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.accept_odder_rest.AcceptOrderRestResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.confirmation_order_rest.ConfirmOrderRestResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.decline_order_rest.DeclineorderRestResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.delete.DeleteResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategotyResaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.new_category.NewCategoryRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.update_category.UpdateCategoryResponse;
import com.example.sofraapp.data.model.client.category_foods.CategoryResponse;
import com.example.sofraapp.data.model.client.login_client.ClientResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.commission.CommissionResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.RestaurantResponse;
import com.example.sofraapp.data.model.restaurant.RestaurantDataResponse;
import com.example.sofraapp.data.model.restaurant_details.RestaurantDetailsResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.notification_restaurant.NotificationRestResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferAddRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferupdateRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.add_item.AddItemRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantMealsItemResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.UpdateItemRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.review.ReviewRestaurantResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface WebService {

   /* @GET("blood-types")
    Call<GeneralResponse> getBloodTypes(); */


    @POST("client/login")
    @FormUrlEncoded
    Call<ClientResponse> loginClient(@Field("email") String email,
                                     @Field("password") String password);


    @POST("client/reset-password")
    @FormUrlEncoded
    Call<RsetPasswordResponse> resetPasswordClient(@Field("email") String email);

    @POST("client/new-order")
    @FormUrlEncoded
    Call<NewOrderClientResponse> newOrderClient(@Field("restaurant_id") int intrestaurant_id,
                                                @Field("note") String note,
                                                @Field("address") String address,
                                                @Field("payment_method_id") String payment_method_id,
                                                @Field("api_token") String api_token,
                                                @Field("items[]") List<String> items,
                                                @Field("quantities[]") List<String> quantities,
                                                @Field("notes[]") List<String> notes);

    @GET("settings")
    Call<AbourAppResponse> aboutApp();


    @POST("client/new-password")
    @FormUrlEncoded
    Call<NewPassworadClientResponse> newPasswordClient(@Field("code") String email,
                                                       @Field("password") String password,
                                                       @Field("password_confirmation") String password_confirmation);

    @POST("restaurant/change-password")
    @FormUrlEncoded
    Call<NewPassworadClientResponse> newPasswordRestauarnt(@Field("api_token") String api_token,
                                                           @Field("old_password") String old_password,
                                                           @Field("password") String password,
                                                           @Field("password_confirmation") String password_confirmation);

    @POST("restaurant/login")
    @FormUrlEncoded
    Call<RestaurantResponse> loginRestaurant(@Field("email") String email,
                                             @Field("password") String password);

    @GET("cities-not-paginated")
    Call<GeneralResponse> getCities();

    @GET("regions-not-paginated")
    Call<GeneralResponse> getRegions(@Query("city_id") int cityId);


    @POST("client/sign-up")
    @Multipart
    Call<ClientResponse> registerClient(@Part("name") RequestBody name,
                                        @Part("email") RequestBody email,
                                        @Part("password") RequestBody password,
                                        @Part("password_confirmation") RequestBody password_confirmation,
                                        @Part("phone") RequestBody phone,
                                        @Part("region_id") RequestBody region_id,
                                        @Part MultipartBody.Part profile_image);

    @POST("client/profile")
    @Multipart
    Call<ClientResponse> editProfileClient(@Part("name") RequestBody name,
                                           @Part("email") RequestBody email,
                                           @Part("phone") RequestBody phone,
                                           @Part("region_id") RequestBody region_id,
                                           @Part MultipartBody.Part profile_image,
                                           @Part("api_token") RequestBody api_token);

    @POST("restaurant/sign-up")
    @Multipart
    Call<RestaurantResponse> registerRestaurant(@Part("name") RequestBody name,
                                                @Part("email") RequestBody email,
                                                @Part("password") RequestBody password,
                                                @Part("password_confirmation") RequestBody password_confirmation,
                                                @Part("phone") RequestBody phone,
                                                @Part("whatsapp") RequestBody whatsapp,
                                                @Part("region_id") RequestBody region_id,
                                                @Part("delivery_cost") RequestBody delivery_cost,
                                                @Part("minimum_charger") RequestBody minimum_charger,
                                                @Part("delivery_time") RequestBody delivery_time,
                                                @Part MultipartBody.Part photo);

    @POST("restaurant/profile")
    @Multipart
    Call<RestaurantResponse> updqteRestaurant(@Part("name") RequestBody name,
                                              @Part("email") RequestBody email,
                                              @Part("api_token") RequestBody api_token,
                                              @Part("phone") RequestBody phone,
                                              @Part("whatsapp") RequestBody whatsapp,
                                              @Part("region_id") RequestBody region_id,
                                              @Part("delivery_cost") RequestBody delivery_cost,
                                              @Part("minimum_charger") RequestBody minimum_charger,
                                              @Part("delivery_time") RequestBody delivery_time,
                                              @Part("availability") RequestBody availability,
                                              @Part MultipartBody.Part photo);


    @GET("restaurants")
    Call<RestaurantDataResponse> getRestaurant(@Query("page") int page);

    @GET("restaurants")
    Call<RestaurantDataResponse> getFilterRestaurant(@Query("keyword") String keyword,
                                                     @Query("city_id") int city_id,
                                                     @Query("page") int page);

    @GET("items")
    Call<RestaurantDetailsResponse> getRestaurantsFoods(@Query("restaurant_id") int restaurant_id,
                                                        @Query("category_id") int category_id,
                                                        @Query("page") int page);

    @GET("categories")
    Call<CategoryResponse> getCategory(@Query("restaurant_id") int restaurant_id,
                                       @Query("category_id") int category_id);

    @GET("restaurant/my-items")
    Call<RestaurantMealsItemResponse> getItem(@Query("api_token") String api_token,
                                              @Query("category_id") int category_id);

    @POST("restaurant/new-item")
    @Multipart
    Call<AddItemRestaurantResponse> addItem(@Part("description") RequestBody description,
                                            @Part("price") RequestBody price,
                                            @Part("name") RequestBody name,
                                            @Part MultipartBody.Part photo,
                                            @Part("api_token") RequestBody api_token,
                                            @Part("offer_price") RequestBody offer_price,
                                            @Part("category_id") RequestBody category_id);

    @POST("restaurant/update-item")
    @Multipart
    Call<UpdateItemRestaurantResponse> updateItem(@Part("description") RequestBody description,
                                                  @Part("price") RequestBody price,
                                                  @Part("category_id") RequestBody category_id,
                                                  @Part("name") RequestBody name,
                                                  @Part MultipartBody.Part photo,
                                                  @Part("item_id") RequestBody item_id,
                                                  @Part("api_token") RequestBody api_token,
                                                  @Part("offer_price") RequestBody offer_price
    );


    @GET("restaurant/my-categories")
    Call<CategotyResaurantResponse> getCategory(@Query("api_token") String api_token,
                                                @Query("page") int page);

    @POST("restaurant/new-category")
    @Multipart
    Call<NewCategoryRestaurantResponse> newItem(@Part("name") RequestBody name,
                                                @Part MultipartBody.Part photo,
                                                @Part("api_token") RequestBody api_token);

    @POST("restaurant/update-category")
    @Multipart
    Call<UpdateCategoryResponse> updateCategory(@Part("name") RequestBody name,
                                                @Part MultipartBody.Part photo,
                                                @Part("api_token") RequestBody api_token,
                                                @Part("category_id") RequestBody category_id);

    @POST("restaurant/delete-category")
    @FormUrlEncoded
    Call<DeleteResponse> deleteCategory(@Field("api_token") String api_token,
                                        @Field("category_id") int category_id);


    @POST("restaurant/delete-item")
    @FormUrlEncoded
    Call<DeleteResponse> deleteitem(@Field("api_token") String api_token,
                                    @Field("item_id") int item_id);

    @POST("restaurant/delete-offer")
    @FormUrlEncoded
    Call<DeleteResponse> deleteOffer(@Field("api_token") String api_token,
                                     @Field("offer_id") int item_id);


    @GET("restaurant/reviews")
    Call<ReviewRestaurantResponse> getReview(@Query("api_token") String api_token,
                                             @Query("restaurant_id") int restaurant_id);

    @POST("client/restaurant/review")
    @FormUrlEncoded
    Call<ReviewClientResponse> addReview(@Field("rate") String rate,
                                         @Field("comment") String comment,
                                         @Field("restaurant_id") int restaurant_id,
                                         @Field("api_token") String api_token);

    @GET("client/my-orders")
    Call<OrderClientResponse> getCurrentOrderClient(@Query("api_token") String api_token,
                                                    @Query("state") String state);

    @GET("restaurant/my-orders")
    Call<OrderRestaurantResponse> getOrderRestaurant(@Query("api_token") String api_token,
                                                     @Query("state") String state);

    @POST("client/confirm-order")
    @FormUrlEncoded
    Call<ConfirmOredrClientResponse> confirmOrder(@Field("api_token") String api_token,
                                                  @Field("order_id") int order_id);

    @POST("restaurant/reject-order")
    @FormUrlEncoded
    Call<DeclineorderRestResponse> declineOrderRestaurant(@Field("api_token") String api_token,
                                                          @Field("order_id") int order_id);

    @POST("restaurant/reject-order")
    @FormUrlEncoded
    Call<DeclineorderRestResponse> declineOrderRest(@Field("api_token") String api_token,
                                                    @Field("order_id") int order_id,
                                                    @Field("refuse_reason") String refuse_reason);

    @POST("restaurant/confirm-order")
    @FormUrlEncoded
    Call<ConfirmOrderRestResponse> confirmOrderRedt(@Field("api_token") String api_token,
                                                    @Field("order_id") int order_id);

    @POST("restaurant/accept-order")
    @FormUrlEncoded
    Call<AcceptOrderRestResponse> acceptOrderRedt(@Field("api_token") String api_token,
                                                  @Field("order_id") int order_id);


    @POST("client/decline-order")
    @FormUrlEncoded
    Call<DeclineOredrClinetResponse> declineOrder(@Field("api_token") String api_token,
                                                  @Field("order_id") int order_id);


    @GET("offers")
    Call<OfferClientResponse> getOffers(@Query("page") int page,
                                        @Query("restaurant_id") int restaurant_id);

    @GET("offer")
    Call<OfferDetailClientResponse> getDetail(@Query("offer_id") int offer_id);

    @GET("restaurant/my-offers")
    Call<OfferRestaurantResponse> getOfferRestaurant(@Query("api_token") String api_token,
                                                     @Query("page") int page);

    @POST("restaurant/update-offer")
    @Multipart
    Call<OfferupdateRestaurantResponse> updateOffer(@Part("description") RequestBody description,
                                                    @Part("price") RequestBody price,
                                                    @Part("starting_at") RequestBody starting_at,
                                                    @Part("name") RequestBody name,
                                                    @Part MultipartBody.Part photo,
                                                    @Part("ending_at") RequestBody ending_at,
                                                    @Part("offer_id") RequestBody offer_id,
                                                    @Part("api_token") RequestBody api_token);

    @POST("restaurant/new-offer")
    @Multipart
    Call<OfferAddRestaurantResponse> addOffer(@Part("description") RequestBody description,
                                              @Part("price") RequestBody price,
                                              @Part("starting_at") RequestBody starting_at,
                                              @Part("name") RequestBody name,
                                              @Part MultipartBody.Part photo,
                                              @Part("ending_at") RequestBody ending_at,
                                              @Part("api_token") RequestBody api_token);

    @POST("contact")
    @FormUrlEncoded
    Call<ContactResponse> contact(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("phone") String phone,
                                  @Field("type") String type,
                                  @Field("content") String content);

    @GET("restaurant/commissions")
    Call<CommissionResponse> getCommission(@Query("api_token") String api_token);

    @GET("restaurant/notifications")
    Call<NotificationRestResponse> getNotificationRest(@Query("api_token") String api_token);

    @GET("client/notifications")
    Call<NotificationClientResponse> getNotification(@Query("api_token") String api_token);

}



package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.delete.DeleteResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantDataMealsItem;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.BaseActivity;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.AddNewItemRestaurant;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.UpdateItemRestaurant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.UPDATE;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class FoodsRestaurantAdapter extends RecyclerView.Adapter<FoodsRestaurantAdapter.ViewHolder> {


    private Context context;
    BaseActivity baseActivity;
    ResataurantData resataurantData;
    List<RestaurantDataMealsItem> restaurantDataMealsItemsList = new ArrayList<>();

    public FoodsRestaurantAdapter(Context context, List<RestaurantDataMealsItem> restaurantDataMealsItemsList) {
        this.context = context;
        baseActivity = (BaseActivity) context;
        this.restaurantDataMealsItemsList = restaurantDataMealsItemsList; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_foods_restaurant, viewGroup, false);
        resataurantData = loadUserDataRestaurant(baseActivity);
        return new ViewHolder(view); }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        final RestaurantDataMealsItem item = restaurantDataMealsItemsList.get(pos);
        viewHolder.itemFoodsRestaurantTvName.setText(item.getName());
        viewHolder.itemFoodsRestaurantTvDesc.setText(item.getDescription());
        viewHolder.itemFoodsRestaurantTvPrice.setText("price" + "     "+item.getPrice());
        onLoadImageFromUrl(viewHolder.itemFoodsRestaurantIvPhoto, item.getPhotoUrl(), context);
        viewHolder.itemFoodsRestaurantUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SaveData(baseActivity, USER_TYPE, UPDATE);
                UpdateItemRestaurant updateItemRestaurant= new UpdateItemRestaurant();
                updateItemRestaurant.items=restaurantDataMealsItemsList.get(pos);
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(),updateItemRestaurant,
                        R.id.fram,null,null); }});
        viewHolder.itemFoodsRestaurantDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManger.getApis().deleteitem(resataurantData.getApiToken(),item.getId()).enqueue(new Callback<DeleteResponse>() {
                    @Override
                    public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            restaurantDataMealsItemsList.remove(pos);
                            notifyDataSetChanged(); } }
                    @Override
                    public void onFailure(Call<DeleteResponse> call, Throwable t) {

                    }}); }}); }

    @Override
    public int getItemCount() {
        return restaurantDataMealsItemsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_foods_restaurant_delete)
        ImageButton itemFoodsRestaurantDelete;
        @BindView(R.id.item_foods_restaurant_update)
        ImageButton itemFoodsRestaurantUpdate;
        @BindView(R.id.item_foods_restaurant_iv_photo)
        ImageView itemFoodsRestaurantIvPhoto;
        @BindView(R.id.item_foods_restaurant_tv_name)
        TextView itemFoodsRestaurantTvName;
        @BindView(R.id.item_foods_restaurant_tv_desc)
        TextView itemFoodsRestaurantTvDesc;
        @BindView(R.id.item_foods_restaurant_tv_price)
        TextView itemFoodsRestaurantTvPrice;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view); }}
}

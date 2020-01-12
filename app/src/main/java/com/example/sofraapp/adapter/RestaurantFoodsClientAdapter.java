package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.restaurant_details.RestaurantDetailsDataItem;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.BaseActivity;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.RequestOrderClientFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class RestaurantFoodsClientAdapter extends RecyclerView.Adapter<RestaurantFoodsClientAdapter.ViewHolder> {


    List<RestaurantDetailsDataItem> restaurantDetailsDataItems = new ArrayList<>();


    private Context context;
    BaseActivity baseActivity;

    public RestaurantFoodsClientAdapter(Context context, List<RestaurantDetailsDataItem> restaurantDetailsDataItemList) {
        this.context = context;
        this.restaurantDetailsDataItems = restaurantDetailsDataItemList;
        baseActivity = (BaseActivity) context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant_foods_client,
                viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        final RestaurantDetailsDataItem item = restaurantDetailsDataItems.get(pos);
        viewHolder.itemRestaurantFoodsTvName.setText(item.getName());
        viewHolder.itemRestaurantFoodsOfferPrice.setText("offer" + "       " + item.getOfferPrice());
        viewHolder.itemRestaurantFoodsTvDescription.setText(item.getDescription());
        viewHolder.itemRestaurantFoodsTvPrice.setText(item.getPrice());
        onLoadImageFromUrl(viewHolder.itemRestaurantFoodsPhoto, item.getPhotoUrl(), context);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestOrderClientFragment = new RequestOrderClientFragment();
                RequestOrderClientFragment.restaurant_foods = restaurantDetailsDataItems.get(pos);
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(), requestOrderClientFragment,
                        R.id.fram, null, null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurantDetailsDataItems.size();
    }
    RestaurantClientAdapter.OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(RestaurantClientAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }


    RequestOrderClientFragment requestOrderClientFragment;



    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_restaurant_foods_photo)
        ImageView itemRestaurantFoodsPhoto;
        @BindView(R.id.item_restaurant_foods_offerPrice)
        TextView itemRestaurantFoodsOfferPrice;
        @BindView(R.id.item_restaurant_foods_tv_name)
        TextView itemRestaurantFoodsTvName;
        @BindView(R.id.item_restaurant_foods_tv_description)
        TextView itemRestaurantFoodsTvDescription;
        @BindView(R.id.item_restaurant_foods_tv_price)
        TextView itemRestaurantFoodsTvPrice;

        private View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

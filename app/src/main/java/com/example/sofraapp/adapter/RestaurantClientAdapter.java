package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.restaurant.RestaurantDataItem;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.BaseActivity;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.InformationClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.RestaurantFoodsClientFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class RestaurantClientAdapter extends RecyclerView.Adapter<RestaurantClientAdapter.ViewHolder> {


    private Context context;
    BaseActivity baseActivity;

    public RestaurantClientAdapter(Context context, List<RestaurantDataItem> restaurantDataItemsList) {
        this.context = context;
        baseActivity = (BaseActivity) context;
        this.restaurantDataItemsList = restaurantDataItemsList;
    }

    List<RestaurantDataItem> restaurantDataItemsList = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restauean_clientt,
                viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        final RestaurantDataItem item = restaurantDataItemsList.get(pos);
        viewHolder.itemRestaurantTvName.setText(item.getName());
        viewHolder.itemRestaurantRb.setRating(item.getRate());
        viewHolder.itemRestaurantTvMinCharger.setText(item.getMinimumCharger());
        viewHolder.itemRestaurantTvDeliveryCost.setText(item.getDeliveryCost());
        viewHolder.itemRestaurantTvState.setText(item.getAvailability());
        onLoadImageFromUrl(viewHolder.itemRestaurantIvPhoto, item.getPhotoUrl(), context);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InformationClientFragment informationClientFragment= new InformationClientFragment();
                    informationClientFragment.restaurant=restaurantDataItemsList.get(pos);
                    RestaurantFoodsClientFragment restaurantFoodsFragment = new RestaurantFoodsClientFragment();
                    restaurantFoodsFragment.restaurant = restaurantDataItemsList.get(pos);
                    HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(), restaurantFoodsFragment,
                            R.id.fram, null, null);
                }
            }); }


    @Override
    public int getItemCount() {
        return restaurantDataItemsList.size();
    }
    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        @BindView(R.id.item_restaurant_tv_name)
        TextView itemRestaurantTvName;
        @BindView(R.id.item_restaurant_rb)
        RatingBar itemRestaurantRb;
        @BindView(R.id.item_restaurant_tv_min_charger)
        TextView itemRestaurantTvMinCharger;
        @BindView(R.id.item_restaurant_tv_delivery_cost)
        TextView itemRestaurantTvDeliveryCost;
        @BindView(R.id.item_restaurant_tv_state)
        TextView itemRestaurantTvState;
        @BindView(R.id.item_restaurant_constrain)
        ConstraintLayout itemRestaurantConstrain;
        @BindView(R.id.item_restaurant_iv_photo)
        ImageView itemRestaurantIvPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

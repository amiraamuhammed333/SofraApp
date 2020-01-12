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
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.delete.DeleteResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferRestaurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferupdateRestaurantResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.ui.more.more_client.OfferDetailClientFragment;
import com.example.sofraapp.view.fragment.ui.more.more_restaurant.OfferUpdateRestaurantFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.UPDATE;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class OfferRestaurantAdapter extends RecyclerView.Adapter<OfferRestaurantAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    ResataurantData resataurantData;
    List<OfferRestaurantData> offerRestaurantDataList = new ArrayList<>();

    public OfferRestaurantAdapter(Context context, FragmentActivity activity, List<OfferRestaurantData> offerRestaurantDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.offerRestaurantDataList = offerRestaurantDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_offer_restaurant, parent, false);
        resataurantData = loadUserDataRestaurant(baseActivity);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OfferRestaurantData item = offerRestaurantDataList.get(position);
        holder.itemOfferRestaurantTvName.setText(item.getName());
        onLoadImageFromUrl(holder.itemOfferRestaurantIvPhoto, item.getPhotoUrl(), baseActivity);
        holder.itemOfferRestaurantDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManger.getApis().deleteOffer(resataurantData.getApiToken(),item.getId()).enqueue(new Callback<DeleteResponse>() {
                    @Override
                    public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            offerRestaurantDataList.remove(position);
                            notifyDataSetChanged(); }
                    }
                    @Override
                    public void onFailure(Call<DeleteResponse> call, Throwable t) {
                    }}); }});
        holder.itemOfferRestaurantUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData(baseActivity, USER_TYPE, UPDATE);
                OfferUpdateRestaurantFragment offerUpdateRestaurantFragment=new OfferUpdateRestaurantFragment();
                offerUpdateRestaurantFragment.offer=offerRestaurantDataList.get(position);
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(),offerUpdateRestaurantFragment,
                        R.id.fram,null,null);
            }
        });


    }


    @Override
    public int getItemCount() {
        return offerRestaurantDataList.size();
    }

    @OnClick(R.id.sub_view)
    public void onClick() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        List<OfferRestaurantData> offerRestaurantDataList = new ArrayList<>();
        @BindView(R.id.item_offer_restaurant_delete)
        ImageButton itemOfferRestaurantDelete;
        @BindView(R.id.item_offer_restaurant_update)
        ImageButton itemOfferRestaurantUpdate;
        @BindView(R.id.item_offer_restaurant_iv_photo)
        ImageView itemOfferRestaurantIvPhoto;
        @BindView(R.id.item_offer_restaurant_tv_name)
        TextView itemOfferRestaurantTvName;
        @BindView(R.id.item_offer_restaurant)
        SwipeRevealLayout itemOfferRestaurant;
        @BindView(R.id.sub_view)
        ConstraintLayout subView;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.delete.DeleteResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.CustomUpdateDialog;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.FoodsRestaurantFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class CategoryRestaurantAdapter extends RecyclerView.Adapter<CategoryRestaurantAdapter.ViewHolder> {

    List<CategoryRestaurantDataItem> categoryRestaurantDataItemList = new ArrayList<>();

    private Context context;
    FragmentActivity baseActivity;
    ResataurantData resataurantData;

    public CategoryRestaurantAdapter(Context context, FragmentActivity activity, List<CategoryRestaurantDataItem> categoryRestaurantDataItemList) {
        this.context = context;
        baseActivity = (activity);
        this.categoryRestaurantDataItemList = categoryRestaurantDataItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_restaurant,
                viewGroup, false);
        resataurantData = loadUserDataRestaurant(baseActivity);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        resataurantData = loadUserDataRestaurant(baseActivity);
        final CategoryRestaurantDataItem item = categoryRestaurantDataItemList.get(pos);
        viewHolder.itemMealsRestaurantTvName.setText(item.getName());
        onLoadImageFromUrl(viewHolder.itemMealsRestaurantIvPhoto, item.getPhoto_url(), context);
        if (onItemClickListener != null) {
            viewHolder.sub_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodsRestaurantFragment foodsRestaurantFragment = new FoodsRestaurantFragment();
                    foodsRestaurantFragment.restaurant = categoryRestaurantDataItemList.get(pos);
                    HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(), foodsRestaurantFragment,
                            R.id.fram, null, null);
                }
            });
        }

        if (updateClickListner != null) {
            viewHolder.itemMealsRestaurantUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomUpdateDialog customUpdateDialog = new CustomUpdateDialog(baseActivity);
                    customUpdateDialog.restaurant = categoryRestaurantDataItemList.get(pos);
                    customUpdateDialog.resataurantData = resataurantData;
                    customUpdateDialog.show();
                }
            });

            viewHolder.itemMealsRestaurantDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    APIManger.getApis().deleteCategory(resataurantData.getApiToken(),
                            item.getId()).enqueue(new Callback<DeleteResponse>() {
                        @Override
                        public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                categoryRestaurantDataItemList.remove(pos);
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteResponse> call, Throwable t) {
                        }
                    });
                }
            });
        }
    }

    OnItemClickListener updateClickListner;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setUpdateClickListner(OnItemClickListener updateClickListner) {
        this.updateClickListner = updateClickListner;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return categoryRestaurantDataItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_meals_restaurant_delete)
        ImageButton itemMealsRestaurantDelete;
        @BindView(R.id.item_meals_restaurant_update)
        ImageButton itemMealsRestaurantUpdate;
        @BindView(R.id.item_meals_restaurant_iv_photo)
        ImageView itemMealsRestaurantIvPhoto;
        @BindView(R.id.item_meals_restaurant_tv_name)
        TextView itemMealsRestaurantTvName;
        @BindView(R.id.item_meals_restaurant)
        SwipeRevealLayout itemMealsRestaurant;
        @BindView(R.id.sub_view)
        View sub_view;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

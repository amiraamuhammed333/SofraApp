package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.confirmation_order_rest.ConfirmOrderRestResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.decline_order_rest.DeclineorderRestResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.view.activity.CustomDialogCancelOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class CurrentOrderRestaurantAdapter extends RecyclerView.Adapter<CurrentOrderRestaurantAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    ResataurantData resataurantData;
    List<OrderRestaurantData> orderRestaurantDataList = new ArrayList<>();


    public CurrentOrderRestaurantAdapter(Context context, FragmentActivity activity, List<OrderRestaurantData> orderRestaurantDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.orderRestaurantDataList = orderRestaurantDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_curent_order_restaurantt,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderRestaurantData item = orderRestaurantDataList.get(position);
        holder.itemCurrentOrderRestaurantTvName.setText(item.getClient().getName());
        holder.itemCurrentOrderRestaurantTvOrderId.setText(item.getClientId());
        holder.itemCurrentOrderRestaurantEtTotal.setText(item.getTotal());
        holder.itemCurrentOrderRestaurantEtAddress.setText(item.getAddress());
        onLoadImageFromUrl(holder.itemCurrentOrderRestaurantImage, item.getRestaurant().getPhotoUrl(), context);
        holder.itemCurrentOrderRestaurantBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManger.getApis().confirmOrderRedt("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx"
                        , item.getId()).enqueue(new Callback<ConfirmOrderRestResponse>() {
                    @Override
                    public void onResponse(Call<ConfirmOrderRestResponse> call, Response<ConfirmOrderRestResponse> response) {
                        if (response.isSuccessful()
                        ){ Toast.makeText(baseActivity,response.body().getMsg(),Toast.LENGTH_LONG).show(); }
                          orderRestaurantDataList.remove(position);}
                    @Override
                    public void onFailure(Call<ConfirmOrderRestResponse> call, Throwable t) { }}); }});
        holder.itemCurrentOrderRestaurantBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogCancelOrder customDialogCancelOrder= new CustomDialogCancelOrder(baseActivity);
                customDialogCancelOrder.item=orderRestaurantDataList.get(position);
                CustomDialogCancelOrder.resataurantData = resataurantData;
                customDialogCancelOrder.show();
            }});
    }



    @Override
    public int getItemCount() {
        return orderRestaurantDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_current_order_restaurant_Image)
        ImageView itemCurrentOrderRestaurantImage;
        @BindView(R.id.item_current_order_restaurant_tv_name)
        TextView itemCurrentOrderRestaurantTvName;
        @BindView(R.id.item_current_order_restaurant_tv_order_id)
        TextView itemCurrentOrderRestaurantTvOrderId;
        @BindView(R.id.item_current_order_restaurant_et_total)
        TextView itemCurrentOrderRestaurantEtTotal;
        @BindView(R.id.item_current_order_restaurant_et_address)
        TextView itemCurrentOrderRestaurantEtAddress;
        @BindView(R.id.item_current_order_restaurant_btn_confirm)
        Button itemCurrentOrderRestaurantBtnConfirm;
        @BindView(R.id.item_current_order_restaurant_btn_cancel)
        Button itemCurrentOrderRestaurantBtnCancel;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

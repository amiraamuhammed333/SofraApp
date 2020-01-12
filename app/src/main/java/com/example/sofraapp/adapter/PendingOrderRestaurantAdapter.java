package com.example.sofraapp.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.Pivot;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.accept_odder_rest.AcceptOrderRestResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.confirmation_order_rest.ConfirmOrderRestResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.decline_order_rest.DeclineorderRestResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.ui.order.order_restaurant.OrderDetailRestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class PendingOrderRestaurantAdapter extends RecyclerView.Adapter<PendingOrderRestaurantAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<OrderRestaurantData> orderRestaurantDataList = new ArrayList<>();


    public PendingOrderRestaurantAdapter(Context context, FragmentActivity activity, List<OrderRestaurantData> orderRestaurantDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.orderRestaurantDataList = orderRestaurantDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pending_order_restaurantt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderRestaurantData item = orderRestaurantDataList.get(position);
        holder.itemPendingOrderRestaurantTvName.setText(item.getClient().getName());
        holder.itemPendingOrderRestaurantTvOrderId.setText(item.getClientId());
        holder.itemPendingOrderRestaurantEtTotal.setText(item.getTotal());
        holder.itemPendingOrderRestaurantEtAddress.setText(item.getAddress());
        onLoadImageFromUrl(holder.itemPendingOrderRestaurantCircleImage, item.getRestaurant().getPhotoUrl(), context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailRestFragment orderDetailRestFragment=new OrderDetailRestFragment();
                orderDetailRestFragment.item=orderRestaurantDataList.get(position);
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(),orderDetailRestFragment,R.id.fram,
                        null,null);
            }});

        holder.itemPendingOrderRestaurantBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData( Uri.parse("tel:" + item.getRestaurant().getPhone()));
                if (ActivityCompat.checkSelfPermission(baseActivity,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {return;}
                context.startActivity(intent); }});

        holder.itemPendingOrderRestaurantBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManger.getApis().acceptOrderRedt("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx"
                        , item.getId()).enqueue(new Callback<AcceptOrderRestResponse>() {
                    @Override
                    public void onResponse(Call<AcceptOrderRestResponse> call, Response<AcceptOrderRestResponse> response) {
                        if (response.isSuccessful()
                        ){
                            Toast.makeText(baseActivity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                            orderRestaurantDataList.remove(position); } }
                    @Override
                    public void onFailure(Call<AcceptOrderRestResponse> call, Throwable t) { }}); }});
        holder.itemPendingOrderRestaurantBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManger.getApis().declineOrderRestaurant("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx",
                        item.getId()).enqueue(new Callback<DeclineorderRestResponse>() {
                    @Override
                    public void onResponse(Call<DeclineorderRestResponse> call, Response<DeclineorderRestResponse> response) {
                        if (response.isSuccessful()
                        ){
                            Toast.makeText(baseActivity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                            orderRestaurantDataList.remove(position); } }

                    @Override
                    public void onFailure(Call<DeclineorderRestResponse> call, Throwable t) {

                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return orderRestaurantDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_pending_order_restaurant_circleImage)
        ImageView itemPendingOrderRestaurantCircleImage;
        @BindView(R.id.item_pending_order_restaurant_tv_name)
        TextView itemPendingOrderRestaurantTvName;
        @BindView(R.id.item_pending_order_restaurant_tv_order_id)
        TextView itemPendingOrderRestaurantTvOrderId;
        @BindView(R.id.item_pending_order_restaurant_et_total)
        TextView itemPendingOrderRestaurantEtTotal;
        @BindView(R.id.item_pending_order_restaurant_et_address)
        TextView itemPendingOrderRestaurantEtAddress;
        @BindView(R.id.item_pending_order_restaurant_btn_accept)
        Button itemPendingOrderRestaurantBtnAccept;
        @BindView(R.id.item_pending_order_restaurant_btn_call)
        Button itemPendingOrderRestaurantBtnCall;
        @BindView(R.id.item_pending_order_restaurant_btn_cancel)
        Button itemPendingOrderRestaurantBtnCancel;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_client.decline_order_client.DeclineOredrClinetResponse;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class CompletedOrderRestaurantAdapter extends RecyclerView.Adapter<CompletedOrderRestaurantAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<OrderRestaurantData> orderRestaurantDataList = new ArrayList<>();


    public CompletedOrderRestaurantAdapter(Context context, FragmentActivity activity, List<OrderRestaurantData> orderRestaurantDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.orderRestaurantDataList = orderRestaurantDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_client,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderRestaurantData item = orderRestaurantDataList.get(position);
         holder.itemOrderClientEtName.setText(item.getClient().getName());
         holder.itemOrderClientEtOrderId.setText(item.getClientId());
         holder.itemOrderClientEtTotal.setText(item.getTotal());
         holder.itemOrderClientEtAddress.setText(item.getAddress());
         holder.itemOrderClientBtn.setText(item.getState());
         onLoadImageFromUrl(holder.itemOrderClientCircleImage, item.getRestaurant().getPhotoUrl(), context);
    }


    @Override
    public int getItemCount() {
        return orderRestaurantDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_order_client_circleImage)
        CircleImageView itemOrderClientCircleImage;
        @BindView(R.id.item_order_client_et_name)
        TextView itemOrderClientEtName;
        @BindView(R.id.item_order_client_et_order_id)
        TextView itemOrderClientEtOrderId;
        @BindView(R.id.item_order_client_et_total)
        TextView itemOrderClientEtTotal;
        @BindView(R.id.item_order_client_et_address)
        TextView itemOrderClientEtAddress;
        @BindView(R.id.item_order_client_btn)
        Button itemOrderClientBtn;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

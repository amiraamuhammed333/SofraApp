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
import com.example.sofraapp.data.model.order_client.confirm_order_client.ConfirmOredrClientResponse;
import com.example.sofraapp.data.model.order_client.decline_order_client.DeclineOredrClinetResponse;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class OrderClientAdapter extends RecyclerView.Adapter<OrderClientAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<OrderClientData> currentOrderClientDataList = new ArrayList<>();


    public OrderClientAdapter(Context context, FragmentActivity activity, List<OrderClientData> currentOrderClientDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.currentOrderClientDataList = currentOrderClientDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_client,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderClientData item = currentOrderClientDataList.get(position);
         holder.itemOrderClientEtName.setText(item.getRestaurant().getName());
         holder.itemOrderClientEtOrderId.setText(item.getClientId());
         holder.itemOrderClientEtTotal.setText(item.getTotal());
         holder.itemOrderClientEtAddress.setText(item.getAddress());
         holder.itemOrderClientBtn.setText(item.getState());
         onLoadImageFromUrl(holder.itemOrderClientCircleImage, item.getRestaurant().getPhotoUrl(), context);
        holder.itemOrderClientBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (item.getState().equals("pending")){
                APIManger.getApis().declineOrder("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB",item.getId())
                        .enqueue(new Callback<DeclineOredrClinetResponse>() {
                            @Override
                            public void onResponse(Call<DeclineOredrClinetResponse> call, Response<DeclineOredrClinetResponse> response) {
                                if (response.body().getStatus()==1){
                                    Toast.makeText(baseActivity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                                    currentOrderClientDataList.remove(position);
                                    notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onFailure(Call<DeclineOredrClinetResponse> call, Throwable t) {

                            }
                        });
            }else if (item.getState().equals("accepted")){
                    APIManger.getApis().confirmOrder("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB",item.getId())
                            .enqueue(new Callback<ConfirmOredrClientResponse>() {
                                @Override
                                public void onResponse(Call<ConfirmOredrClientResponse> call, Response<ConfirmOredrClientResponse> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(baseActivity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ConfirmOredrClientResponse> call, Throwable t) {

                                }
                            });
                }

            }

        });


    }


    @Override
    public int getItemCount() {
        return currentOrderClientDataList.size();
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

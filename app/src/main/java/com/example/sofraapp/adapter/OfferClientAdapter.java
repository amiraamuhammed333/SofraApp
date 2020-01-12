package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientData;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.ui.more.more_client.OfferDetailClientFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class OfferClientAdapter extends RecyclerView.Adapter<OfferClientAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<OfferClientData> offerClientDataList = new ArrayList<>();

    public OfferClientAdapter(Context context, FragmentActivity activity, List<OfferClientData> offerClientDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.offerClientDataList = offerClientDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_offer_client,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OfferClientData item = offerClientDataList.get(position);
        holder.itemOfferClientTvName.setText(item.getName());
        onLoadImageFromUrl(holder.itemOfferClientCircleImage, item.getPhotoUrl(), baseActivity);
        holder.itemOfferClientBtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OfferDetailClientFragment offerDetailClientFragment = new OfferDetailClientFragment();
                offerDetailClientFragment.offer=offerClientDataList.get(position);
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(),
                        offerDetailClientFragment,R.id.fram,null,null);
            }
        });


    }


    @Override
    public int getItemCount() {
        return offerClientDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_offer_client_tv_name)
        TextView itemOfferClientTvName;
        @BindView(R.id.item_offer_client_btn_detail)
        Button itemOfferClientBtnDetail;
        @BindView(R.id.item_offer_client_circleImage)
        CircleImageView itemOfferClientCircleImage;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

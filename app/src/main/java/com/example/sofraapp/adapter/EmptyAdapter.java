package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sofraapp.R;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;



public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<OrderClientData> currentOrderClientDataList = new ArrayList<>();


    public EmptyAdapter(Context context, FragmentActivity activity, List<OrderClientData> currentOrderClientDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.currentOrderClientDataList = currentOrderClientDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderClientData item = currentOrderClientDataList.get(position);
    }


    @Override
    public int getItemCount() {
        return currentOrderClientDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

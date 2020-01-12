package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.notification_restaurant.NotificationRestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationRestAdapter extends RecyclerView.Adapter<NotificationRestAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<NotificationRestData> notificationDataList = new ArrayList<>();

    public NotificationRestAdapter(Context context, FragmentActivity activity, List<NotificationRestData> notificationDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.notificationDataList = notificationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NotificationRestData item = notificationDataList.get(position);

        holder.itemNotificationTvTitle.setText(item.getContentEn());
        holder.itemNotificationTvTime.setText(item.getUpdatedAt());
    }


    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_notification_tv_title)
        TextView itemNotificationTvTitle;
        @BindView(R.id.item_notification_tv_time)
        TextView itemNotificationTvTime;
        @BindView(R.id.item_notification_iv)
        ImageView itemNotificationIv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

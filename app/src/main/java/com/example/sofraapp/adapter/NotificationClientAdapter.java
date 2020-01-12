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
import com.example.sofraapp.data.model.notification_client.NotificationClientData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationClientAdapter extends RecyclerView.Adapter<NotificationClientAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<NotificationClientData> notificationClientDataList = new ArrayList<>();

    public NotificationClientAdapter(Context context, FragmentActivity activity, List<NotificationClientData> notificationClientDataList) {
        this.context = context;
        this.baseActivity = (activity);
        this.notificationClientDataList = notificationClientDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NotificationClientData item = notificationClientDataList.get(position);

        holder.itemNotificationTvTitle.setText(item.getContentEn());
        holder.itemNotificationTvTime.setText(item.getUpdatedAt());
    }


    @Override
    public int getItemCount() {
        return notificationClientDataList.size();
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

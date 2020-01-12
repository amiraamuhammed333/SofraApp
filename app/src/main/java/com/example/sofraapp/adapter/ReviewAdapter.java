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
import com.example.sofraapp.data.model.restaurantt.restaurant_item.review.ReviewRestaurantDataItem;
import com.example.sofraapp.view.fragment.ui.more.more_restaurant.ReviewAndCommentRestaurantFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    List<ReviewRestaurantDataItem> reviewRestaurantDataItemList = new ArrayList<>();


    public ReviewAdapter(Context context, FragmentActivity activity, List<ReviewRestaurantDataItem> reviewRestaurantDataItemList) {
        this.context = context;
        this.baseActivity = (activity);
        this.reviewRestaurantDataItemList = reviewRestaurantDataItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ReviewRestaurantDataItem item = reviewRestaurantDataItemList.get(position);
        ReviewAndCommentRestaurantFragment.review=reviewRestaurantDataItemList.get(position);
        holder.itmeReviewTvName.setText(item.getClient().getName());
        holder.itmeReviewTvDesc.setText(item.getComment());

        if (item.getRate().equals(String.valueOf(1))){
            holder.itemReviewIvPhoto.setBackgroundResource(R.drawable.ic_very_angry);
        }
        else if (item.getRate().equals(String.valueOf(2))){
            holder.itemReviewIvPhoto.setBackgroundResource(R.drawable.ic_mood_bad);
        }
        else if (item.getRate().equals(String.valueOf(3))){
            holder.itemReviewIvPhoto.setBackgroundResource(R.drawable.ic_smile);
        }
        else if (item.getRate().equals(String.valueOf(4))){
            holder.itemReviewIvPhoto.setBackgroundResource(R.drawable.ic_very_satisfied_);
        }
        else if (item.getRate().equals(String.valueOf(5))){
            holder.itemReviewIvPhoto.setBackgroundResource(R.drawable.ic_love);
        }


    }


    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return reviewRestaurantDataItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itme_review_tv_name)
        TextView itmeReviewTvName;
        @BindView(R.id.itme_review_tv_desc)
        TextView itmeReviewTvDesc;
        @BindView(R.id.item_review_iv_photo)
        ImageView itemReviewIvPhoto;
//@BindView(R.id.item_review_smile_rating_photo)
//SmileRating itemReviewSmileRatingPhoto;


        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

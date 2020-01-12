package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.client.category_foods.CategoryDataItem;
import com.example.sofraapp.view.activity.BaseActivity;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.ListOfFoodClientFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class CategoryClientAdapter extends RecyclerView.Adapter<CategoryClientAdapter.ViewHolder> {

    private List<CategoryDataItem> categoryDataItems = new ArrayList<>();
    private BaseActivity baseActivity;
    private ListOfFoodClientFragment listOfFoodClientFragment;
    private Context context;

    public CategoryClientAdapter(Context context, List<CategoryDataItem> categoryDataItems, ListOfFoodClientFragment listOfFoodClientFragment) {
        this.context = context;
        baseActivity = (BaseActivity) context;
        this.categoryDataItems = categoryDataItems;
        this.listOfFoodClientFragment = listOfFoodClientFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_client, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        final CategoryDataItem item = categoryDataItems.get(pos);
        viewHolder.itemCategoryTvName.setText(item.getName());
        onLoadImageFromUrl(viewHolder.itemCategoryCircleImageview, item.getPhotoUrl(), context);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listOfFoodClientFragment.category = categoryDataItems.get(pos).getId();
                listOfFoodClientFragment.getListOfRestauramtFoods(1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_category_circle_imageview)
        CircleImageView itemCategoryCircleImageview;
        @BindView(R.id.item_category_tv_name)
        TextView itemCategoryTvName;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

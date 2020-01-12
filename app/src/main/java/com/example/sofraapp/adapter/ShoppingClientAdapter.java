package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.database.model.Order;
import com.example.sofraapp.database.model.OrderDataBase;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.RequestOrderClientFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.RequestOrderClientFragment.restaurant_foods;


public class ShoppingClientAdapter extends RecyclerView.Adapter<ShoppingClientAdapter.ViewHolder> {

    public Context context;
    FragmentActivity baseActivity;
    int i = 0;

    List<Order> orders = new ArrayList<>();


    public ShoppingClientAdapter(Context context, FragmentActivity activity, List<Order> orders) {
        this.context = context;
        this.baseActivity = (activity);
        this.orders = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopping_client,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Order item = orders.get(position);
        holder.itemShoppingTvName.setText(item.getName());
        holder.itemShoppingTvPrice.setText(item.getPrice());
        holder.itemShoppingEtNumber.setText(item.getNumber());
        onLoadImageFromUrl(holder.itemShoppingIv, item.getPhoto(), baseActivity);
        holder.itemShoppingBtnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumber(holder, '+');
                item.setNumber(String.valueOf(i));
                OrderDataBase.getInstance(baseActivity).orderDAOs().updateOrder(orders.get(position));

            }

        });

        holder.itemShoppingBtnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumber(holder, '-');
                OrderDataBase.getInstance(baseActivity).orderDAOs().updateOrder(orders.get(position));

            }
        });
        holder.itemShoppingBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDataBase.getInstance(baseActivity).orderDAOs().deleteOrder(orders.get(position));
                orders.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    public void getNumber(ViewHolder holder, char c) {
        i = Integer.parseInt(holder.itemShoppingEtNumber.getText().toString());
        if (c == '+') {
           i++;
        } else {
            i--;
        }
        if (i < 0) {
           i = 0;
        }
        holder.itemShoppingEtNumber.setText(i + "");

    }

    @Override
    public int getItemCount() {
        if (orders == null) return 0;
        return orders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_shopping_iv)
        ImageView itemShoppingIv;
        @BindView(R.id.item_shopping_tv_name)
        TextView itemShoppingTvName;
        @BindView(R.id.item_shopping_tv_price)
        TextView itemShoppingTvPrice;
        @BindView(R.id.item_shopping_tv_required_number)
        TextView itemShoppingTvRequiredNumber;
        @BindView(R.id.item_shopping_et_number)
        EditText itemShoppingEtNumber;
        @BindView(R.id.item_shopping_btn_decrease)
        ImageButton itemShoppingBtnDecrease;
        @BindView(R.id.item_shopping_btn_increase)
        ImageButton itemShoppingBtnIncrease;
        @BindView(R.id.item_shopping_btn_cancel)
        ImageButton itemShoppingBtnCancel;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

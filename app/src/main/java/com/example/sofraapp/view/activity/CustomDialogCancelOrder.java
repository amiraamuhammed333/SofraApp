package com.example.sofraapp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.decline_order_rest.DeclineorderRestResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomDialogCancelOrder extends Dialog {


    public static ResataurantData resataurantData;
    public OrderRestaurantData item;
    Unbinder unbinder;
    public Activity c;
    @BindView(R.id.cancel_order_res_et_reason)
    EditText cancelOrderResEtReason;
    @BindView(R.id.cancel_order_res_btn_cancel)
    Button cancelOrderResBtnCancel;
    String reason;


    public CustomDialogCancelOrder(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cancel_order_rest);
        unbinder = ButterKnife.bind(this);

    }


    @OnClick(R.id.cancel_order_res_btn_cancel)
    public void onClick() {
        reason=cancelOrderResEtReason.getText().toString();
        APIManger.getApis().declineOrderRest("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx",item.getId(),reason).enqueue(new Callback<DeclineorderRestResponse>() {
            @Override
            public void onResponse(Call<DeclineorderRestResponse> call, Response<DeclineorderRestResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<DeclineorderRestResponse> call, Throwable t) {

            }
        });

    }
}

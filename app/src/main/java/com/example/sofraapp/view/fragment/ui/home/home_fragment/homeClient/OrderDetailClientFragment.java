package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.login_client.ClientData;
import com.example.sofraapp.data.model.order_client.new_order.NewOrderClientResponse;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.example.sofraapp.database.model.Order;
import com.example.sofraapp.database.model.OrderDataBase;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataClient;


public class OrderDetailClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    BaseFragment baseFragment;
    @BindView(R.id.order_detail_client_et_note)
    EditText orderDetailClientEtNote;
    @BindView(R.id.order_detail_client_et_address)
    EditText orderDetailClientEtAddress;
    @BindView(R.id.order_detail_client_ib_online)
    ImageButton orderDetailClientIbOnline;
    @BindView(R.id.order_detail_client_ib_receive)
    ImageButton orderDetailClientIbReceive;
    @BindView(R.id.order_detail_client_tv_total)
    TextView orderDetailClientTvTotal;
    @BindView(R.id.order_detail_client_tv_delivary_charge)
    TextView orderDetailClientTvDelivaryCharge;
    @BindView(R.id.order_detail_client_tv_total_payment)
    TextView orderDetailClientTvTotalPayment;
    @BindView(R.id.order_detail_client_btn_confirm)
    Button orderDetailClientBtnConfirm;
    ShoppingClientFragment shoppingClientFragment;
    List<Order> orders = new ArrayList<>();
    OrderClientData orderClientData;
    private List<String> items, quantities, notes;
    ClientData clientData;
    List<Order>orderList=new ArrayList<>();
    String i;
    public OrderDetailClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.order_detail_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        clientData=loadUserDataClient(getActivity());
        items = new ArrayList<>();
        quantities = new ArrayList<>();
        notes = new ArrayList<>();
        orderList= OrderDataBase.getInstance(getActivity()).orderDAOs().getOrderList();

        for (int i = 0; i < orderList.size(); i++) {
            items.add(String.valueOf(orderList.get(i).getId()));
            quantities.add(orderList.get(i).getNumber());
            notes.add(orderList.get(i).getDesc());
        }


        return view;
    }

    private void newOrder(){
        String note=orderDetailClientEtNote.getText().toString();
        String adddres=orderDetailClientEtAddress.getText().toString();
        APIManger.getApis().newOrderClient(1,note,adddres,i,clientData.getApiToken(),items,
                quantities,notes).enqueue(new Callback<NewOrderClientResponse>() {
            @Override
            public void onResponse(Call<NewOrderClientResponse> call, Response<NewOrderClientResponse> response) {
                if (response.body().getStatus()==1){
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    OrderDataBase.getInstance(getActivity()).orderDAOs().deleteAllnOrder();
                    response.body().getData();
                }
            }
            @Override
            public void onFailure(Call<NewOrderClientResponse> call, Throwable t) { }}); }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.order_detail_client_ib_online, R.id.order_detail_client_ib_receive, R.id.order_detail_client_btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_detail_client_ib_online:
                orderDetailClientIbOnline.setBackgroundResource(R.drawable.ic_check_red);
                orderDetailClientIbReceive.setBackgroundResource(R.drawable.shadow_oval);
                i="1";
                break;
            case R.id.order_detail_client_ib_receive:
                orderDetailClientIbReceive.setBackgroundResource(R.drawable.ic_check_red);
                orderDetailClientIbOnline.setBackgroundResource(R.drawable.shadow_oval);
                i="2";
                break;
            case R.id.order_detail_client_btn_confirm:
                newOrder();
                break;
        }
    }
}

package com.example.sofraapp.view.fragment.ui.order.order_restaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.accept_odder_rest.AcceptOrderRestResponse;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.decline_order_rest.DeclineorderRestResponse;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class OrderDetailRestFragment extends BaseFragment {
    public OrderRestaurantData item;
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.order_detail_rest_citrcleImage)
    CircleImageView orderDetailRestCitrcleImage;
    @BindView(R.id.order_detail_rest_name)
    TextView orderDetailRestName;
    @BindView(R.id.order_detail_rest_date)
    TextView orderDetailRestDate;
    @BindView(R.id.order_detail_rest_tv_address)
    TextView orderDetailRestTvAddress;
    @BindView(R.id.order_detail_rest_des)
    TextView orderDetailRestDes;
    @BindView(R.id.order_detail_rest_tv_delicery_cost)
    TextView orderDetailRestTvDeliceryCost;
    @BindView(R.id.order_detail_rest_tv_delicery_charge)
    TextView orderDetailRestTvDeliceryCharge;
    @BindView(R.id.order_detail_rest_tv_total)
    TextView orderDetailRestTvTotal;
    @BindView(R.id.order_detail_rest_tv_payment)
    TextView orderDetailRestTvPayment;
    @BindView(R.id.order_detail_rest_btn_call)
    Button orderDetailRestBtnCall;
    @BindView(R.id.order_detail_rest_btn_accept)
    Button orderDetailRestBtnAccept;
    @BindView(R.id.order_detail_rest_btn_cancel)
    Button orderDetailRestBtnCancel;
    List<OrderRestaurantData> orderRestaurantDataList = new ArrayList<>();


    public OrderDetailRestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.order_detail_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        getDetail();
        return view;
    }
    public void  getDetail(){
        onLoadImageFromUrl(orderDetailRestCitrcleImage, item.getRestaurant().getPhotoUrl(), getActivity());
        orderDetailRestName.setText(item.getRestaurant().getName());
        orderDetailRestDate.setText(item.getUpdatedAt());
        orderDetailRestTvAddress.setText(item.getAddress());
        orderDetailRestDes.setText(item.getNote());
        orderDetailRestTvDeliceryCost.setText(item.getCost());
        orderDetailRestTvDeliceryCharge.setText(item.getDeliveryCost());
        orderDetailRestTvTotal.setText(item.getTotal());
        orderDetailRestTvPayment.setText(item.getPaymentMethodId());


    }
    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.order_detail_rest_btn_call, R.id.order_detail_rest_btn_accept, R.id.order_detail_rest_btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_detail_rest_btn_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData( Uri.parse("tel:" + item.getRestaurant().getPhone()));
                if (ActivityCompat.checkSelfPermission(baseActivity,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {return;}
                getActivity().startActivity(intent);
                break;
            case R.id.order_detail_rest_btn_accept:
                APIManger.getApis().acceptOrderRedt("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx"
                        , item.getId()).enqueue(new Callback<AcceptOrderRestResponse>() {
                    @Override
                    public void onResponse(Call<AcceptOrderRestResponse> call, Response<AcceptOrderRestResponse> response) {
                        if (response.isSuccessful()
                        ){
                            Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                            } }
                    @Override
                    public void onFailure(Call<AcceptOrderRestResponse> call, Throwable t) { }});
                break;
            case R.id.order_detail_rest_btn_cancel:
                APIManger.getApis().declineOrderRestaurant("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx",
                        item.getId()).enqueue(new Callback<DeclineorderRestResponse>() {
                    @Override
                    public void onResponse(Call<DeclineorderRestResponse> call, Response<DeclineorderRestResponse> response) {
                        if (response.isSuccessful()
                        ){
                            Toast.makeText(baseActivity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                        } }

                    @Override
                    public void onFailure(Call<DeclineorderRestResponse> call, Throwable t) {

                    }
                });
                break;
        }
    }
}

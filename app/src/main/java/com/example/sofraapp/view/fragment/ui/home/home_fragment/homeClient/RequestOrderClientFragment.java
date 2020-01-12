package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.restaurant_details.RestaurantDetailsDataItem;
import com.example.sofraapp.database.model.Order;
import com.example.sofraapp.database.model.OrderDataBase;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class RequestOrderClientFragment extends BaseFragment {
    public static RestaurantDetailsDataItem restaurant_foods;
    public int id;
    View view;
    BaseFragment baseFragment;
    @BindView(R.id.request_order_iv_photo)
    ImageView requestOrderIvPhoto;
    @BindView(R.id.request_order_tv_offerPrice)
    TextView requestOrderTvOfferPrice;
    @BindView(R.id.request_order_tv_name)
    TextView requestOrderTvName;
    @BindView(R.id.request_order_tv_description)
    TextView requestOrderTvDescription;
    @BindView(R.id.request_order_tv_price)
    TextView requestOrderTvPrice;
    @BindView(R.id.request_ordertv_private_request)
    TextView requestOrdertvPrivateRequest;
    @BindView(R.id.request_order_et_enter_your_request)
    EditText requestOrderEtEnterYourRequest;
    @BindView(R.id.request_ordert_tv_theRequired_number)
    TextView requestOrdertTvTheRequiredNumber;
    @BindView(R.id.request_ordert_tv_number)
    EditText requestOrdertTvNumber;
    @BindView(R.id.request_ordert_ib_decrease)
    ImageButton requestOrdertIbDecrease;
    @BindView(R.id.request_ordert_ib_increase)
    ImageButton requestOrdertIbIncrease;
    @BindView(R.id.request_ordert_ib_shopping)
    ImageButton requestOrdertIbShopping;
    Unbinder unbinder;
    String request, number;
    int i = 0;

    public RequestOrderClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.request_order_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        getFoods();
        return view;
    }

    private void getFoods() {
        requestOrderTvName.setText(restaurant_foods.getName());
        requestOrderTvDescription.setText(restaurant_foods.getDescription());
        requestOrderTvPrice.setText(restaurant_foods.getPrice());
        requestOrderTvOfferPrice.setText("offer" + "          " + restaurant_foods.getOfferPrice());
        onLoadImageFromUrl(requestOrderIvPhoto, restaurant_foods.getPhotoUrl(), getActivity());


    }

    @Override
    public void onBack() {

        super.onBack();
    }

    @OnClick({R.id.request_ordert_ib_decrease, R.id.request_ordert_ib_increase, R.id.request_ordert_ib_shopping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request_ordert_ib_decrease:
                getNumber('-');
                break;
            case R.id.request_ordert_ib_increase:
                getNumber('+');
                break;
            case R.id.request_ordert_ib_shopping:
                add();
                ShoppingClientFragment shoppingClientFragment = new ShoppingClientFragment();
                HelperMethod.ReplaceFragment(getFragmentManager(), shoppingClientFragment, R.id.fram,
                        null, null);
                break;
        }
    }

    public void getNumber(char c) {
        i = Integer.parseInt(requestOrdertTvNumber.getText().toString());
        if (c == '+') {
            i++;
        } else {
            i--;
        }
        if (i < 0) {
            i = 0;
        }
        requestOrdertTvNumber.setText(i + "");

    }

    private void add() {
        request = requestOrderEtEnterYourRequest.getText().toString();
        number =  requestOrdertTvNumber.getText().toString();
        Order order = new Order(restaurant_foods.getPhotoUrl(), restaurant_foods.getName(),
                restaurant_foods.getDescription(), restaurant_foods.getPrice(), restaurant_foods.getOfferPrice(), request, number);
        OrderDataBase.getInstance(getActivity()).orderDAOs().insertOrder(order);

    }
}

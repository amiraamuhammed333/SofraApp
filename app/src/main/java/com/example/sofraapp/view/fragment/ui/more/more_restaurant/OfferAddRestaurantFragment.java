package com.example.sofraapp.view.fragment.ui.more.more_restaurant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.DateModel;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferAddRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferRestaurantData;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;


public class OfferAddRestaurantFragment extends BaseFragment {
    public OfferRestaurantData offer;
    View view;
    Unbinder unbinder;
    String name;
    String desc;
    String starting_at;
    String ending_at;
    String price;
    @BindView(R.id.offer_restaurant_tv)
    TextView offerRestaurantTv;
    @BindView(R.id.offer_restaurant_et_price)
    EditText offerRestaurantEtPrice;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;
    OfferRestaurantFragment offerRestaurantFragment;

    BaseFragment baseFragment;
    @BindView(R.id.offer_restaurant_iv_photo)
    ImageView offerRestaurantIvPhoto;
    @BindView(R.id.offer_restaurant_et_name)
    EditText offerRestaurantEtName;
    @BindView(R.id.offer_restaurant_et_desc)
    EditText offerRestaurantEtDesc;
    @BindView(R.id.offer_restaurant_et_satrtAt)
    EditText offerRestaurantEtSatrtAt;
    @BindView(R.id.offer_restaurant_et_EndingAt)
    EditText offerRestaurantEtEndingAt;
    @BindView(R.id.offer_restaurant_btn_add)
    Button offerRestaurantBtnAdd;
    ResataurantData resataurantData;
    DateModel from, to;

    public OfferAddRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.offer_restaurant_add, container, false);
        unbinder = ButterKnife.bind(this, view);
        resataurantData = loadUserDataRestaurant(getActivity());
        from = new DateModel("01", "01", "1970", "1970-01-01");
        to = new DateModel("01", "01", "1970", "1970-01-01");

        return view;
    }


    private void add() {
        name = offerRestaurantEtName.getText().toString();
        desc = offerRestaurantEtDesc.getText().toString();
        starting_at = offerRestaurantEtSatrtAt.getText().toString();
        ending_at = offerRestaurantEtEndingAt.getText().toString();
        price = offerRestaurantEtPrice.getText().toString();


        APIManger.getApis().addOffer(convertToRequestBody(desc), convertToRequestBody(price),
                convertToRequestBody(starting_at), convertToRequestBody(name), convertFileToMultipart(path, "photo"),
                convertToRequestBody(ending_at), convertToRequestBody(resataurantData.getApiToken()))
                .enqueue(new Callback<OfferAddRestaurantResponse>() {
                    @Override
                    public void onResponse(Call<OfferAddRestaurantResponse> call, Response<OfferAddRestaurantResponse> response) {
                        if (response.body().getStatus() == 1) {

                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
//                          offerRestaurantFragment.offerRestaurantDataList.add(response.body().getData());
//                          offerRestaurantFragment.offerRestaurantAdapter.notifyDataSetChanged();
//
//                          OfferRestaurantFragment offerRestaurantFragment= new OfferRestaurantFragment();
//                          HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),offerRestaurantFragment,
//                                  R.id.fram,null,null
//                          );
                        }
                    }

                    @Override
                    public void onFailure(Call<OfferAddRestaurantResponse> call, Throwable t) {
                        Log.d("", "onFailure: ");
                    }
                });

    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.offer_restaurant_iv_photo, R.id.offer_restaurant_btn_add,R.id.offer_restaurant_et_satrtAt, R.id.offer_restaurant_et_EndingAt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.offer_restaurant_iv_photo:
                HelperMethod.openGallery(getContext(), 1, imagesList, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        imagesList.clear();
                        imagesList = result;
                        path = imagesList.get(0).getPath();
                        HelperMethod.onLoadImageFromUrl(offerRestaurantIvPhoto, path, getContext());
                    }
                });
                break;
            case R.id.offer_restaurant_btn_add:
                add();
                break;
            case R.id.offer_restaurant_et_satrtAt:
                HelperMethod.showCalender(getActivity(),"from",offerRestaurantEtSatrtAt,from);
                break;
            case R.id.offer_restaurant_et_EndingAt:
                HelperMethod.showCalender(getActivity(),"to",offerRestaurantEtEndingAt,to);

                break;
        }
    }


    }


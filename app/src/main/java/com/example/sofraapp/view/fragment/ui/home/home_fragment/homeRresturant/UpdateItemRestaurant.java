package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant;

import android.os.Bundle;
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
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantDataMealsItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.UpdateItemRestaurantResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.HomeActivity;
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
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class UpdateItemRestaurant extends BaseFragment {
    public  RestaurantDataMealsItem items;
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.add_new_item_restaurant_tv_addPhoto)
    TextView addNewItemRestaurantTvAddPhoto;
    @BindView(R.id.add_new_item_restaurant_iv_photo)
    ImageView addNewItemRestaurantIvPhoto;
    @BindView(R.id.add_new_item_restaurant_et_name)
    EditText addNewItemRestaurantEtName;
    @BindView(R.id.add_new_item_restaurant_et_desc)
    EditText addNewItemRestaurantEtDesc;
    @BindView(R.id.add_new_item_restaurant_et_price)
    EditText addNewItemRestaurantEtPrice;
    @BindView(R.id.add_new_item_restaurant_et_priceOffer)
    EditText addNewItemRestaurantEtPriceOffer;
    @BindView(R.id.add_new_item_restaurant_btn_add)
    Button addNewItemRestaurantBtnAdd;
    ResataurantData resataurantData;
    FoodsRestaurantFragment foodsRestaurantFragment;
    String name;
    String desc;
    String price;
    String offer;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;

    public UpdateItemRestaurant() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.addnewitemrestaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        resataurantData = loadUserDataRestaurant(getActivity());
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        addNewItemRestaurantEtName.setText(items.getName());
        addNewItemRestaurantEtDesc.setText(items.getDescription());
        addNewItemRestaurantEtPrice.setText(items.getPrice());
        addNewItemRestaurantEtPriceOffer.setText(items.getOfferPrice());
        onLoadImageFromUrl ( addNewItemRestaurantIvPhoto, items.getPhotoUrl(),getContext() );

        return view;
    }

    public void getData() {
        name = addNewItemRestaurantEtName.getText().toString();
        desc = addNewItemRestaurantEtDesc.getText().toString();
        price = addNewItemRestaurantEtPrice.getText().toString();
        offer = addNewItemRestaurantEtPriceOffer.getText().toString();
    }

    public void updateItem(){
        getData();
        APIManger.getApis().updateItem(convertToRequestBody(desc),convertToRequestBody(price),
                convertToRequestBody(String.valueOf(items.getCategoryId())),
                convertToRequestBody(name), convertFileToMultipart(path,"photo"),
                convertToRequestBody(String.valueOf(items.getId())),
                convertToRequestBody(resataurantData.getApiToken()),convertToRequestBody(offer)).
                enqueue(new Callback<UpdateItemRestaurantResponse>() {
                    @Override
                    public void onResponse(Call<UpdateItemRestaurantResponse> call, Response<UpdateItemRestaurantResponse> response) {
                        if (response.body().getStatus()==1){
                            if (response.isSuccessful()) {

                                Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateItemRestaurantResponse> call, Throwable t) {

                    }
                });
    }

    @OnClick({R.id.add_new_item_restaurant_iv_photo, R.id.add_new_item_restaurant_btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_new_item_restaurant_iv_photo:
                HelperMethod.openGallery(getActivity(), 1, imagesList, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        imagesList.clear();
                        imagesList = result;
                        path = imagesList.get(0).getPath();
                        HelperMethod.onLoadImageFromUrl(addNewItemRestaurantIvPhoto, path,getActivity());
                    }
                });
                break;
            case R.id.add_new_item_restaurant_btn_add:
                updateItem();
                break;
        }
    }
    @Override
    public void onBack() {

        super.onBack();
    }
}

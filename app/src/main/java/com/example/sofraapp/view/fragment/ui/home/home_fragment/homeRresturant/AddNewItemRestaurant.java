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
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.add_item.AddItemRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantDataMealsItem;

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


public class AddNewItemRestaurant extends BaseFragment {
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

    String name;
    String desc;
    String price;
    String offer;
    ResataurantData resataurantData;
    FoodsRestaurantFragment foodsRestaurantFragment;

    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;

    public AddNewItemRestaurant() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.addnewitemrestaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        resataurantData = loadUserDataRestaurant(getActivity());
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        return view;
    }

    public void getData() {
        name = addNewItemRestaurantEtName.getText().toString();
        desc = addNewItemRestaurantEtDesc.getText().toString();
        price = addNewItemRestaurantEtPrice.getText().toString();
        offer = addNewItemRestaurantEtPriceOffer.getText().toString();
    }

    public void addNewItem(){
        APIManger.getApis().addItem(convertToRequestBody(desc),convertToRequestBody(price),
                 convertToRequestBody(name),
                convertFileToMultipart(path, "photo"),
                convertToRequestBody(resataurantData.getApiToken()),convertToRequestBody(offer),
                convertToRequestBody(String.valueOf(foodsRestaurantFragment.restaurant.getId())))
                .enqueue(new Callback<AddItemRestaurantResponse>() {
                    @Override
                    public void onResponse(Call<AddItemRestaurantResponse> call, Response<AddItemRestaurantResponse> response) {
                        if (response.body().getStatus()==1){
                            Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
//                            foodsRestaurantFragment.restaurantDataMealsItemList.add(response.body().getData());
//                            foodsRestaurantFragment.foodsRestaurantAdapter.notifyDataSetChanged();
//                            FoodsRestaurantFragment foodsRestaurantFragment= new FoodsRestaurantFragment();
//                            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
//                                    foodsRestaurantFragment,R.id.fram,null,null);
                        }
                    }
                    @Override
                    public void onFailure(Call<AddItemRestaurantResponse> call, Throwable t) {

                    }
                }); }


    @Override
    public void onBack() {
        super.onBack();
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
                   getData();
                    addNewItem();
                    break;
        }
    }
}

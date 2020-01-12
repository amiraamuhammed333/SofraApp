package com.example.sofraapp.view.fragment.ui.profile.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.RestaurantResponse;
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
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.GeneralRequests.getSpinnerData;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class EditProfilrRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.edit_profile_restaurant_image)
    CircleImageView editProfileRestaurantImage;
    @BindView(R.id.edit_profile_restaurant_name)
    EditText editProfileRestaurantName;
    @BindView(R.id.edit_profile_restaurant_email)
    EditText editProfileRestaurantEmail;
    @BindView(R.id.edit_profile_restaurant_delivery_time)
    EditText editProfileRestaurantDeliveryTime;
    @BindView(R.id.edit_profile_restaurant_spinner_city)
    Spinner editProfileRestaurantSpinnerCity;
    @BindView(R.id.edit_profile_restaurant_spinner_region)
    Spinner editProfileRestaurantSpinnerRegion;
    @BindView(R.id.edit_profile_restaurant_min_order)
    EditText editProfileRestaurantMinOrder;
    @BindView(R.id.edit_profile_restaurant_delivery_charge)
    EditText editProfileRestaurantDeliveryCharge;
    @BindView(R.id.edit_profile_restaurant_tv)
    TextView editProfileRestaurantTv;
    @BindView(R.id.edit_profile_restaurant_switch)
    Switch editProfileRestaurantSwitch;
    @BindView(R.id.edit_profile_restaurant_phone)
    EditText editProfileRestaurantPhone;
    @BindView(R.id.edit_profile_restaurant_whats)
    EditText editProfileRestaurantWhats;
    @BindView(R.id.edit_profile_restaurant_btn_update)
    Button editProfileRestaurantBtnUpdate;
    CustomSpinnerAdapter citiesAdapter;
    CustomSpinnerAdapter regionsAdapter;
    ResataurantData resataurantData;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;

    public EditProfilrRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.edit_profile_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        resataurantData =loadUserDataRestaurant(getActivity());
        editProfileRestaurantName.setText(resataurantData.getUser().getName());
        editProfileRestaurantEmail.setText(resataurantData.getUser().getEmail());
        editProfileRestaurantDeliveryTime.setText(resataurantData.getUser().getDeliveryTime());
        editProfileRestaurantMinOrder.setText(resataurantData.getUser().getMinimumCharger());
        editProfileRestaurantDeliveryCharge.setText(resataurantData.getUser().getDeliveryCost());
        editProfileRestaurantPhone.setText(resataurantData.getUser().getPhone());
        editProfileRestaurantWhats.setText(resataurantData.getUser().getWhatsapp());
        onLoadImageFromUrl(editProfileRestaurantImage, resataurantData.getUser().getPhotoUrl(), getActivity());
        citiesAdapter = new CustomSpinnerAdapter(getActivity());
        regionsAdapter = new CustomSpinnerAdapter(getActivity());
        getSpinnerData(APIManger.getApis().getCities(), editProfileRestaurantSpinnerCity
                , citiesAdapter, getString(R.string.city), new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (i != 0) {
                            getSpinnerData(APIManger.getApis().getRegions(citiesAdapter.selectedId), editProfileRestaurantSpinnerRegion
                                    , regionsAdapter, getString(R.string.region),resataurantData.getUser().getRegion().getId()); } }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) { }
                }, resataurantData.getUser().getRegion().getCity().getId());
        return view;
    }

    public void update(){
        String name=editProfileRestaurantName.getText().toString();
        String email =editProfileRestaurantEmail.getText().toString();
        String delivery_time =editProfileRestaurantDeliveryTime.getText().toString();
        String min_order=editProfileRestaurantMinOrder.getText().toString();
        String delivery_charge=editProfileRestaurantDeliveryCharge.getText().toString();
        String phone =editProfileRestaurantPhone.getText().toString();
        String whats = editProfileRestaurantWhats.getText().toString();


        APIManger.getApis().updqteRestaurant(convertToRequestBody(name),convertToRequestBody(email),
                convertToRequestBody(resataurantData.getApiToken()),convertToRequestBody(phone),convertToRequestBody(whats),
                convertToRequestBody(String.valueOf(regionsAdapter.selectedId)),convertToRequestBody(delivery_charge),
                convertToRequestBody(min_order),convertToRequestBody(delivery_time),convertToRequestBody("open"),
                convertFileToMultipart(path,"photo")).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.body().getStatus() == 1) {
                    response.body().getData().setApiToken(resataurantData.getApiToken());
                    SaveData(getActivity(), USER_DATA_CLIENT, response.body().getData());
                    if (response.isSuccessful()) {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }
                }
                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {

            }
        });


    }



    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.edit_profile_restaurant_image, R.id.edit_profile_restaurant_btn_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_profile_restaurant_image:
                HelperMethod.openGallery(getActivity(), 1, imagesList, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        imagesList.clear();
                        imagesList = result;
                        path = imagesList.get(0).getPath();
                        HelperMethod.onLoadImageFromUrl(editProfileRestaurantImage, path, getActivity());
                    }
                });
                break;
            case R.id.edit_profile_restaurant_btn_update:
                update();
                break;
        }
    }
}

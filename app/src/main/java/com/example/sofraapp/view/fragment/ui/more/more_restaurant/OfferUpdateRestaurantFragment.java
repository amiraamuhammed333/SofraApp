package com.example.sofraapp.view.fragment.ui.more.more_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferRestaurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferupdateRestaurantResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.example.sofraapp.view.fragment.login_cycle.SignUpStep1RestaurantFragment;
import com.example.sofraapp.view.fragment.login_cycle.SignupClientFragment;
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

import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.NEW;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.UPDATE;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class OfferUpdateRestaurantFragment extends BaseFragment {
    public  OfferRestaurantData offer;
    View view;
    Unbinder unbinder;
    String name;
    String desc;
    String starting_at;
    String ending_at;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;
    ResataurantData resataurantData;


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

    public OfferUpdateRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.offer_restaurant_update, container, false);
        unbinder = ButterKnife.bind(this, view);
        resataurantData = loadUserDataRestaurant(getActivity());

        getData();
        return view;
    }

    private void getData(){
        offerRestaurantEtName.setText(offer.getName());
        offerRestaurantEtDesc.setText(offer.getDescription());
        offerRestaurantEtSatrtAt.setText(offer.getStartingAt());
        offerRestaurantEtEndingAt.setText(offer.getEndingAt());
        onLoadImageFromUrl ( offerRestaurantIvPhoto,offer.getPhotoUrl(),getContext() );
    }

    private void update() {
        name=offerRestaurantEtName.getText().toString();
        desc=offerRestaurantEtDesc.getText().toString();
        starting_at= offerRestaurantEtSatrtAt.getText().toString();
        ending_at= offerRestaurantEtEndingAt.getText().toString();

        APIManger.getApis().updateOffer(convertToRequestBody(desc),convertToRequestBody(offer.getPrice()),
                convertToRequestBody(starting_at),convertToRequestBody(name),convertFileToMultipart(path,"photo"),
                convertToRequestBody(ending_at),convertToRequestBody(String.valueOf(offer.getId())),
                convertToRequestBody(resataurantData.getApiToken())).enqueue(new Callback<OfferupdateRestaurantResponse>() {
            @Override
            public void onResponse(Call<OfferupdateRestaurantResponse> call, Response<OfferupdateRestaurantResponse> response) {
                if (response.body().getStatus() == 1) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        OfferRestaurantFragment offerRestaurantFragment=new OfferRestaurantFragment();
                        HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(),offerRestaurantFragment,
                                R.id.fram,null,null);
                    } } }

            @Override
            public void onFailure(Call<OfferupdateRestaurantResponse> call, Throwable t) {
            }}); }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.offer_restaurant_iv_photo, R.id.offer_restaurant_btn_add})
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
                    }});
                break;
            case R.id.offer_restaurant_btn_add:
                    update();

                break;
        }
    }
}

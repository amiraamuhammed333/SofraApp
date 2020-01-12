package com.example.sofraapp.view.fragment.login_cycle;

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
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.RestaurantResponse;
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

import static com.example.sofraapp.data.local.SharedPreference.CONFIRMATION_PASSWORD_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.DELIVERYCOST_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.DELIVERY_TIME_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.EMAIL_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.MINIMUMCHARGER_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.NAME_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.REGION_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.loadStringData;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;


public class SignupStep2RestaurantFragment extends BaseFragment {


    @BindView(R.id.signup_step2_et_phone)
    EditText signupStep2EtPhone;
    @BindView(R.id.signup_step2_et_whats)
    EditText signupStep2EtWhats;
    @BindView(R.id.signup_step2_iv_photo)
    ImageView signupStep2IvPhoto;
    @BindView(R.id.signup_step2_btn_signup)
    Button signupStep2BtnSignup;
    Unbinder unbinder;
    View view;
    private String password;
    private String confirmation_password;
    private String name;
    private String email;
    private int region;
    private String delivery_cost;
    private String min_charger;
    private String delivery_time;




    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;

    public SignupStep2RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup_step2_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        password = loadStringData(getActivity(), PASSWORD_RESTAURANT);
        confirmation_password = loadStringData(getActivity(), CONFIRMATION_PASSWORD_RESTAURANT);
        name = loadStringData(getActivity(), NAME_RESTAURANT);
        email = loadStringData(getActivity(), EMAIL_RESTAURANT);
        region = Integer.parseInt(loadStringData(getActivity(), REGION_RESTAURANT ));
        password = loadStringData(getActivity(), PASSWORD_RESTAURANT);
        delivery_cost = loadStringData(getActivity(), DELIVERYCOST_RESTAURANT);
        min_charger = loadStringData(getActivity(), MINIMUMCHARGER_RESTAURANT);
        delivery_time = loadStringData(getActivity(), DELIVERY_TIME_RESTAURANT);

        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.signup_step2_iv_photo, R.id.signup_step2_btn_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_step2_iv_photo:
                HelperMethod.openGallery(getActivity(), 1, imagesList, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {

                        imagesList.clear();
                        imagesList = result;

                        path = imagesList.get(0).getPath();

                        HelperMethod.onLoadImageFromUrl(signupStep2IvPhoto, path, getActivity());

                    }
                });

                break;
            case R.id.signup_step2_btn_signup:
                signup();
                break;
        }
    }

    private void signup() {

        String phone=signupStep2EtPhone.getText().toString();
        String whatsup=signupStep2EtWhats.getText().toString();

        APIManger.getApis().registerRestaurant(convertToRequestBody(name), convertToRequestBody(email),
                convertToRequestBody(password),convertToRequestBody(confirmation_password),
                convertToRequestBody(phone),convertToRequestBody(whatsup),convertToRequestBody(String.valueOf(region)),
                convertToRequestBody(delivery_cost),convertToRequestBody(min_charger),convertToRequestBody(delivery_time),
                convertFileToMultipart(path, "photo")).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {

                try {

                    if (response.body().getStatus() == 1) {
                        SaveData(getActivity(), USER_DATA_RESTAURANT, response.body().getData());

                        if (response.isSuccessful()) {

                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();


                            //   Intent intent = new Intent(getActivity(), HomeActivity.class);
                            //     startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }


                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {


                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                try {

                }catch (Exception e){

                }

            }
        });

    }
}

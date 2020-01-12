package com.example.sofraapp.view.fragment.login_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.login_client.ClientResponse;
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

import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_CLIENT;
import static com.example.sofraapp.helper.GeneralRequests.getSpinnerData;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;

public class SignupClientFragment extends BaseFragment {

    @BindView(R.id.signup_client_iv_photo)
    ImageView signupClientIvPhoto;
    @BindView(R.id.signup_client_et_name)
    EditText signupClientEtName;
    @BindView(R.id.signup_client_et_email)
    EditText signupClientEtEmail;
    @BindView(R.id.signup_client_et_phone)
    EditText signupClientEtPhone;
    @BindView(R.id.signup_client_spinner_city)
    Spinner signupClientSpinnerCity;
    @BindView(R.id.signup_client_spinner_region)
    Spinner signupClientSpinnerRegion;
    @BindView(R.id.signup_client_et_password)
    EditText signupClientEtPassword;
    @BindView(R.id.signup_client_et_confiem_password)
    EditText signupClientEtConfiemPassword;
    @BindView(R.id.signup_btn_login)
    Button signupBtnLogin;
    Unbinder unbinder;
    View view;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;




    CustomSpinnerAdapter citiesAdapter;
    CustomSpinnerAdapter regionsAdapter;


    public SignupClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        citiesAdapter = new CustomSpinnerAdapter(getActivity());
        regionsAdapter = new CustomSpinnerAdapter(getActivity());

        getSpinnerData(APIManger.getApis().getCities(), signupClientSpinnerCity
                , citiesAdapter, getString(R.string.city), new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (i != 0) {
                            getSpinnerData(APIManger.getApis().getRegions(citiesAdapter.selectedId), signupClientSpinnerRegion
                                    , regionsAdapter, getString(R.string.region));
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

        return view;
    }





    @Override
    public void onBack() {
        super.onBack();
    }


    @OnClick({R.id.signup_client_iv_photo, R.id.signup_btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_client_iv_photo:
                HelperMethod.openGallery(getActivity(), 1, imagesList, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        imagesList.clear();
                        imagesList = result;
                        path = imagesList.get(0).getPath();
                        HelperMethod.onLoadImageFromUrl(signupClientIvPhoto, path, getActivity());
                    }});
                break;
            case R.id.signup_btn_login:
                onRgister();
                break;
        }
    }

    private void onRgister() {
        String name = signupClientEtName.getText().toString();
        String email = signupClientEtEmail.getText().toString();
        int region_id = regionsAdapter.selectedId;
        String phone = signupClientEtPhone.getText().toString();
        final String password = signupClientEtPassword.getText().toString();
        String confirmation_password = signupClientEtConfiemPassword.getText().toString();

        validate();
        APIManger.getApis().registerClient(convertToRequestBody(name),convertToRequestBody(email),convertToRequestBody(password),
                convertToRequestBody(confirmation_password),convertToRequestBody(phone),convertToRequestBody(String.valueOf(region_id)),
                convertFileToMultipart(path, "profile_image") ).enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {

                try {

                    if (response.body().getStatus() == 1) {
                        SaveData(getActivity(), USER_DATA_CLIENT, response.body().getData());
                        SaveData ( getActivity (), PASSWORD_CLIENT, password );
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                         //   Intent intent = new Intent(getActivity(), HomeActivity.class);
                       //     startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        } }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                } }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {

            }
        });

    }

    public boolean validate() {
        boolean valid = true;


        if (signupClientEtName.length() < 1 || signupClientEtName.length() > 32) {
            signupClientEtName.setError("Please enter valid name");
            valid = false;
        }


        if (signupClientEtEmail.length() < 1 || signupClientEtEmail.length() > 32) {
            signupClientEtEmail.setError("Please enter valid email address");
            valid = false;
        }
        if (signupClientEtPhone.length() < 1 || signupClientEtPhone.length() > 32) {
            signupClientEtPhone.setError("Please enter valid email address");
            valid = false;
        }
        if (signupClientEtPassword.length() < 1) {
            signupClientEtPassword.setError("Please enter valid password");
            valid = false;
        }
        if (signupClientEtConfiemPassword.length() < 1) {
            signupClientEtConfiemPassword.setError("Please enter valid password");
            valid = false;
        }
        if (!signupClientEtConfiemPassword.equals(signupClientEtPassword)) {
            signupClientEtPassword.setError("Passwords don't match!");
            valid = false;
        } else {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
        }
        return valid;
    }


}
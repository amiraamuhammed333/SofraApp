package com.example.sofraapp.view.fragment.ui.profile.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.login_client.ClientData;
import com.example.sofraapp.data.model.client.login_client.ClientResponse;
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

import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataClient;
import static com.example.sofraapp.helper.GeneralRequests.getSpinnerData;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;


public class EditClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    ClientData clientData;

    BaseFragment baseFragment;
    @BindView(R.id.signup_client_iv_photo)
    CircleImageView signupClientIvPhoto;
    @BindView(R.id.edit_client_et_name)
    EditText editClientEtName;
    @BindView(R.id.edit_client_et_email)
    EditText editClientEtEmail;
    @BindView(R.id.ediy_client_et_phone)
    EditText ediyClientEtPhone;
    @BindView(R.id.edit_client_spinner_city)
    Spinner editClientSpinnerCity;
    @BindView(R.id.ediy_client_spinner_region)
    Spinner ediyClientSpinnerRegion;
    @BindView(R.id.edit_btn_login)
    Button editBtnLogin;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;
    String name;
    String email;
    String phone;
    CustomSpinnerAdapter citiesAdapter;
    CustomSpinnerAdapter regionsAdapter;


    public EditClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.edit_profile_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        clientData = loadUserDataClient(getActivity());
        editClientEtName.setText(clientData.getUser().getName());

        editClientEtEmail.setText(clientData.getUser().getEmail());
        ediyClientEtPhone.setText(clientData.getUser().getPhone());
        onLoadImageFromUrl(signupClientIvPhoto, clientData.getUser().getPhotoUrl(), getActivity());
        citiesAdapter = new CustomSpinnerAdapter(getActivity());
        regionsAdapter = new CustomSpinnerAdapter(getActivity());

        getSpinnerData(APIManger.getApis().getCities(), editClientSpinnerCity
                , citiesAdapter, getString(R.string.city), new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (i != 0) {
                            getSpinnerData(APIManger.getApis().getRegions(citiesAdapter.selectedId), ediyClientSpinnerRegion
                                    , regionsAdapter, getString(R.string.region), clientData.getUser().getRegion().getId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                }, clientData.getUser().getRegion().getCity().getId());

        return view;
    }

    private void editProfile() {
        name = editClientEtName.getText().toString();
        email = editClientEtEmail.getText().toString();
        phone = ediyClientEtPhone.getText().toString();
        int region_Id = regionsAdapter.selectedId;
        APIManger.getApis().editProfileClient(convertToRequestBody(name), convertToRequestBody(email), convertToRequestBody(phone),
                convertToRequestBody(String.valueOf(region_Id)), convertFileToMultipart(path, "profile_image"),
                convertToRequestBody(clientData.getApiToken())).enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                if (response.body().getStatus() == 1) {
                    response.body().getData().setApiToken(clientData.getApiToken());
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
            public void onFailure(Call<ClientResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }


    @OnClick({R.id.signup_client_iv_photo, R.id.edit_btn_login})
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
                    }
                });
                break;
            case R.id.edit_btn_login:
                editProfile();
                break;
        }
    }
}

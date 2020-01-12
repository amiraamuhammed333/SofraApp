package com.example.sofraapp.view.fragment.ui.more.more_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.contact_us.ContactResponse;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.contact_us_et_name)
    EditText contactUsEtName;
    @BindView(R.id.contact_us_et_email)
    EditText contactUsEtEmail;
    @BindView(R.id.contact_us_et_phone)
    EditText contactUsEtPhone;
    @BindView(R.id.contact_us_et_desc)
    EditText contactUsEtDesc;
    @BindView(R.id.contact_us_rg_complain)
    RadioButton contactUsRgComplain;
    @BindView(R.id.contact_us_rg_suggestion)
    RadioButton contactUsRgSuggestion;
    @BindView(R.id.contact_us_rg_inquiry)
    RadioButton contactUsRgInquiry;
    @BindView(R.id.contact_us_rg)
    RadioGroup contactUsRg;
    @BindView(R.id.contact_us_btn_send)
    Button contactUsBtnSend;
    String type,name,email,phone,desc;


    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.conatct_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        return view;
    }

    private void contact() {
        name=contactUsEtName.getText().toString();
        email=contactUsEtEmail.getText().toString();
        phone=contactUsEtPhone.getText().toString();
        desc=contactUsEtDesc.getText().toString();
        APIManger.getApis().contact(name,email,phone,type,desc).enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.contact_us_rg_suggestion, R.id.contact_us_rg_inquiry, R.id.contact_us_rg_complain, R.id.contact_us_btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_us_rg_suggestion:
                type="suggestion";
                break;
            case R.id.contact_us_rg_inquiry:
                type="inquiry";
                break;
            case R.id.contact_us_rg_complain:
                type="complaint";
                break;
            case R.id.contact_us_btn_send:
                contact();
                break;
        }
    }
}

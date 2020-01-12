package com.example.sofraapp.view.fragment.login_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.login_client.RsetPasswordResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResetPaasswordClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.reset_password_et_email)
    EditText resetPasswordEtEmail;
    @BindView(R.id.reset_password_btn_send)
    Button resetPasswordBtnSend;
    String email;

    public ResetPaasswordClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reset_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void reset() {
        email=resetPasswordEtEmail.getText().toString();
        APIManger.getApis().resetPasswordClient(email).enqueue(new Callback<RsetPasswordResponse>() {
            @Override
            public void onResponse(Call<RsetPasswordResponse> call, Response<RsetPasswordResponse> response) {
                if(response.isSuccessful ()) {

                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    NewPasswordClientFragment newPasswordClientFragment= new NewPasswordClientFragment();
                    HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),newPasswordClientFragment,
                            R.id.fram,null,null);

            }}

            @Override
            public void onFailure(Call<RsetPasswordResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.reset_password_btn_send)
    public void onClick() {
        reset();
    }
}

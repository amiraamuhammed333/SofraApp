package com.example.sofraapp.view.fragment.ui.more.more_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.about_app.AbourAppResponse;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutAppFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.about_app_tv_detail)
    TextView aboutAppTvDetail;

    public AboutAppFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.about_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        APIManger.getApis().aboutApp().enqueue(new Callback<AbourAppResponse>() {
            @Override
            public void onResponse(Call<AbourAppResponse> call, Response<AbourAppResponse> response) {
            aboutAppTvDetail.setText(response.body().getData().getAboutApp());
            }
            @Override
            public void onFailure(Call<AbourAppResponse> call, Throwable t) { }});
        return view;
    }


    @Override
    public void onBack() {
        super.onBack();
    }
}

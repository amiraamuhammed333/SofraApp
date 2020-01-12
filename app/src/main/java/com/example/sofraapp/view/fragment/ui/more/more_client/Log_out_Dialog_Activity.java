package com.example.sofraapp.view.fragment.ui.more.more_client;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.sofraapp.R;
import com.example.sofraapp.view.activity.SplashActivity;
import com.example.sofraapp.view.activity.UserCycleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofraapp.data.local.SharedPreference.EMAIL_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.EMAIL_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_RESTAURANT;

public class Log_out_Dialog_Activity extends Dialog {

    @BindView(R.id.log_out_ib_cancel)
    ImageButton logOutIbCancel;
    @BindView(R.id.log_out_ib_confirm)
    ImageButton logOutIbConfirm;
    Unbinder unbinder;

    public Activity activity;
    private Object Log_out_Dialog_Activity;
    public SplashActivity splash;
    public boolean client;

    public Log_out_Dialog_Activity(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.log_out);
        unbinder = ButterKnife.bind(this);

    }


    @OnClick({R.id.log_out_ib_cancel, R.id.log_out_ib_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_out_ib_cancel:
                dismiss();
                break;
            case R.id.log_out_ib_confirm:

                if (client) {
                    SaveData(activity, EMAIL_CLIENT, null);
                    SaveData(activity, PASSWORD_CLIENT, null);
                    SaveData(activity, USER_DATA_CLIENT, null);
                } else {
                    SaveData(activity, EMAIL_RESTAURANT, null);
                    SaveData(activity, PASSWORD_RESTAURANT, null);
                    SaveData(activity, USER_DATA_RESTAURANT, null);
                }

                activity.startActivity(new Intent(activity, UserCycleActivity.class));
                activity.finish();

                break;
        }
    }
}

package com.example.sofraapp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofraapp.R;
import com.example.sofraapp.view.fragment.ui.more.more_client.Log_out_Dialog_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataClient;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_activity_iv_logo1)
    ImageView splashActivityIvLogo1;
    @BindView(R.id.splash_activity_iv_logo2)
    ImageView splashActivityIvLogo2;
    @BindView(R.id.splash_activity_iv_logo)
    ImageView splashActivityIvLogo;
    @BindView(R.id.splash_activity_btn_order_food)
    Button splashActivityBtnOrderFood;
    @BindView(R.id.spash_activity_btn_sell_food)
    Button spashActivityBtnSellFood;
    Unbinder unbinder;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Log_out_Dialog_Activity log_out_dialog_activity = new Log_out_Dialog_Activity(this);
        log_out_dialog_activity.splash = this;
    }

    @OnClick({R.id.splash_activity_btn_order_food, R.id.spash_activity_btn_sell_food})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.splash_activity_btn_order_food:

                SaveData(this, USER_TYPE, CLIENT);
//                intent = new Intent(this, HomeActivity.class);
//                startActivity(intent);
                if (loadUserDataClient(this) != null) {
                    intent = new Intent(this, HomeActivity.class);

                }else {
                    intent = new Intent(this, UserCycleActivity.class);
                }

                startActivity(intent);

                break;
            case R.id.spash_activity_btn_sell_food:

                SaveData(this, USER_TYPE, RESTAURANT);

                if (loadUserDataRestaurant(this) != null) {
                    intent = new Intent(this, HomeActivity.class);

                }else {
                    intent = new Intent(this, UserCycleActivity.class);
                }

                startActivity(intent);

                break;
        }
    }
}

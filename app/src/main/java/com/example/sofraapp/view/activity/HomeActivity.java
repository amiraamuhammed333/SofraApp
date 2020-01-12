package com.example.sofraapp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sofraapp.R;
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.login_cycle.NewPasswordRestaurantFragment;
import com.example.sofraapp.view.fragment.login_cycle.ResetPaasswordClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.NotificationClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.ShoppingClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.CommissionFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.NotificationRestaurantFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.fram)
    FrameLayout fram;
    @BindView(R.id.home_activity_ib_notification)
    ImageButton homeActivityIbNotification;
    @BindView(R.id.home_activity_ib_shopping)
    ImageButton homeActivityIbShopping;
    @BindView(R.id.home_activity_toolbar)
    Toolbar homeActivityToolbar;
    @BindView(R.id.homeActivity_tv)
    TextView homeActivityTv;

//    @BindView(R.id.home_activity_toolbar)
//    Toolbar homeActivityToolbar;
//    @BindView(R.id.home_activity_ib_notification)
//    ImageButton homeActivityIbNotification;
//    @BindView(R.id.home_activity_ib_shopping)
//    ImageButton homeActivityIbShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_order, R.id.navigation_profile, R.id.navigation_more)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        if (SharedPreference.loadStringData(this, USER_TYPE).equals(CLIENT)) {
            homeActivityIbShopping.setBackgroundResource(R.drawable.ic_shopping);
            homeActivityIbNotification.setBackgroundResource(R.drawable.ic_notification);

        } else if (SharedPreference.loadStringData(this, USER_TYPE).equals(RESTAURANT)) {
            homeActivityIbShopping.setBackgroundResource(R.drawable.ic_commission);
            homeActivityIbNotification.setBackgroundResource(R.drawable.ic_notification);
        }

    }
    public void setFram(int visibility) {
        this.fram.setVisibility(visibility);
    }

    public void setHomeActivityTitle(String title) {
        homeActivityTv.setText(title);
    }


    @OnClick({R.id.home_activity_ib_notification, R.id.home_activity_ib_shopping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_activity_ib_notification:
                if (SharedPreference.loadStringData(this, USER_TYPE).equals(CLIENT)) {
                    NotificationClientFragment notificationClientFragment= new NotificationClientFragment();
                    HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), notificationClientFragment, R.id.fram, null, null);
                } else if (SharedPreference.loadStringData(this, USER_TYPE).equals(RESTAURANT)) {
                    NotificationRestaurantFragment notificationRestaurantFragment= new NotificationRestaurantFragment();
                    HelperMethod.ReplaceFragment(this.getSupportFragmentManager(),notificationRestaurantFragment,R.id.fram,null,null);
                }

                break;
            case R.id.home_activity_ib_shopping:
                if (SharedPreference.loadStringData(this, USER_TYPE).equals(CLIENT)) {
                    ShoppingClientFragment shoppingClientFragment = new ShoppingClientFragment();
                     HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), shoppingClientFragment, R.id.fram, null, null);
                } else if (SharedPreference.loadStringData(this, USER_TYPE).equals(RESTAURANT)) {
                    CommissionFragment commissionFragment= new CommissionFragment();
                    HelperMethod.ReplaceFragment(this.getSupportFragmentManager(),commissionFragment,R.id.fram,null,null);
                }
                break;
        }
    }
}

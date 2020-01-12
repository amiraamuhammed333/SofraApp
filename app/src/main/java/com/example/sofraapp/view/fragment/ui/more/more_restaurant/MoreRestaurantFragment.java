package com.example.sofraapp.view.fragment.ui.more.more_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sofraapp.R;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.example.sofraapp.view.fragment.login_cycle.NewPasswordClientFragment;
import com.example.sofraapp.view.fragment.login_cycle.NewPasswordRestaurantFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.ReviewAndCommentClientFragment;
import com.example.sofraapp.view.fragment.ui.more.more_client.Log_out_Dialog_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MoreRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.nore_fragment_tv)
    TextView noreFragmentTv;
    @BindView(R.id.more_fragment_tv)
    TextView moreFragmentTv;
    @BindView(R.id.more_fragment_tv_offer)
    TextView moreFragmentTvOffer;
    @BindView(R.id.more_fragment_tv_contact_us)
    TextView moreFragmentTvContactUs;
    @BindView(R.id.more_fragment_tv_about_app)
    TextView moreFragmentTvAboutApp;
    @BindView(R.id.more_fragment_tv_review)
    TextView moreFragmentTvReview;
    @BindView(R.id.more_fragment_tv_about_change_password)
    TextView moreFragmentTvAboutChangePassword;
    @BindView(R.id.more_fragment_tv_log_out)
    TextView moreFragmentTvLogOut;

    public MoreRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_restaurant_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.more_fragment_tv_offer, R.id.more_fragment_tv_contact_us, R.id.more_fragment_tv_about_app, R.id.more_fragment_tv_review, R.id.more_fragment_tv_about_change_password, R.id.more_fragment_tv_log_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_fragment_tv_offer:
                OfferRestaurantFragment offerRestaurantFragment = new OfferRestaurantFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),offerRestaurantFragment,
                        R.id.fram,null,null);
                break;
            case R.id.more_fragment_tv_contact_us:
                break;
            case R.id.more_fragment_tv_about_app:
                break;
            case R.id.more_fragment_tv_review:
                ReviewAndCommentRestaurantFragment reviewAndCommentRestaurantFragment=new ReviewAndCommentRestaurantFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),reviewAndCommentRestaurantFragment,
                        R.id.fram,null,null);
                break;
            case R.id.more_fragment_tv_about_change_password:
                NewPasswordRestaurantFragment newPasswordRestaurantFragment= new NewPasswordRestaurantFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),newPasswordRestaurantFragment,
                        R.id.fram,null,null);
                break;
            case R.id.more_fragment_tv_log_out:
                Log_out_Dialog_Activity log_out_dialog_activity = new Log_out_Dialog_Activity(getActivity());
                log_out_dialog_activity.show();
                break;
        }
    }
}

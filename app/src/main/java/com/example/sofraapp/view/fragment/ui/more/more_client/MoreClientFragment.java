package com.example.sofraapp.view.fragment.ui.more.more_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.sofraapp.R;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.CustomDialogAddReview;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.example.sofraapp.view.fragment.login_cycle.ResetPaasswordClientFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MoreClientFragment extends BaseFragment {

    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.more_fragment_tv_offer)
    TextView moreFragmentTvOffer;
    @BindView(R.id.more_fragment_tv_contact_us)
    TextView moreFragmentTvContactUs;
    @BindView(R.id.more_fragment_tv_about_app)
    TextView moreFragmentTvAboutApp;
    @BindView(R.id.more_fragment_tv_about_change_password)
    TextView moreFragmentTvAboutChangePassword;
    @BindView(R.id.more_fragment_tv_log_out)
    TextView moreFragmentTvLogOut;

    public MoreClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_client_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.more_fragment_tv_offer, R.id.more_fragment_tv_contact_us, R.id.more_fragment_tv_about_app, R.id.more_fragment_tv_about_change_password, R.id.more_fragment_tv_log_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_fragment_tv_offer:
                OfferClientFragent offerClientFragent = new OfferClientFragent();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), offerClientFragent,
                        R.id.fram, null, null);
                break;
            case R.id.more_fragment_tv_contact_us:
                ContactFragment contactfragment= new ContactFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),contactfragment,
                        R.id.fram,null,null);
                break;
            case R.id.more_fragment_tv_about_app:
                AboutAppFragment aboutAppFragment= new AboutAppFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),aboutAppFragment,R.id.fram,
                        null,null);
                break;
            case R.id.more_fragment_tv_about_change_password:
                ResetPaasswordClientFragment resetPaasswordClientFragment= new ResetPaasswordClientFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),resetPaasswordClientFragment,
                        R.id.fram,null,null);
                break;
            case R.id.more_fragment_tv_log_out:
                Log_out_Dialog_Activity log_out_dialog_activity = new Log_out_Dialog_Activity(getActivity());
                log_out_dialog_activity.client = true;
                log_out_dialog_activity.show();
                break;
        }
    }
}

package com.example.sofraapp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.ReviewAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.client_review.ReviewClientResponse;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.RestaurantFoodsClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.ReviewAndCommentClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.HomeRestaurantFragment;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomDialogAddReview extends Dialog {


    public ReviewAndCommentClientFragment reviewAndCommentClientFragment;
    Unbinder unbinder;
    int rate;

    public Activity c;
    public HomeRestaurantFragment homeRestaurantFragment;
    @BindView(R.id.add_item_tv)
    TextView addItemTv;

    @BindView(R.id.add_review_et_rview)
    EditText addReviewEtRview;
    @BindView(R.id.add_review_btn_add)
    Button addReviewBtnAdd;
    @BindView(R.id.add_review_iv_angry)
    ImageView addReviewIvAngry;
    @BindView(R.id.add_review_iv_bad)
    ImageView addReviewIvBad;
    @BindView(R.id.add_review_iv_smile)
    ImageView addReviewIvSmile;
    @BindView(R.id.add_review_iv_satisified)
    ImageView addReviewIvSatisified;
    @BindView(R.id.add_review_iv_love)
    ImageView addReviewIvLove;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;

    public CustomDialogAddReview(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_review);
        unbinder = ButterKnife.bind(this);
    }

    private void addNewreview() {
        String comment = addReviewEtRview.getText().toString();
        APIManger.getApis().addReview(String.valueOf(rate), comment, RestaurantFoodsClientFragment.restaurant.getId(),
                "HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB"
        ).enqueue(new Callback<ReviewClientResponse>() {
            @Override
            public void onResponse(Call<ReviewClientResponse> call, Response<ReviewClientResponse> response) {
                if (response.body().getStatus() == 1) {
                    if (response.isSuccessful()) {
                        dismiss();
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }
                }}
            @Override
            public void onFailure(Call<ReviewClientResponse> call, Throwable t) { }
        }); }


        @OnClick({R.id.add_review_iv_angry, R.id.add_review_iv_bad, R.id.add_review_iv_smile, R.id.add_review_iv_satisified, R.id.add_review_iv_love, R.id.add_review_btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_review_iv_angry:
                rate=1;
                break;
            case R.id.add_review_iv_bad:
                rate=2;
                break;
            case R.id.add_review_iv_smile:
                rate=3;
                break;
            case R.id.add_review_iv_satisified:
                rate=4;
                break;
            case R.id.add_review_iv_love:
                rate=5;
                break;
            case R.id.add_review_btn_add:
                addNewreview();
                break;
        }
    }
}

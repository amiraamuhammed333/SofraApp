package com.example.sofraapp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CategoryRestaurantAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.new_category.NewCategoryRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.HomeRestaurantFragment;
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

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;
import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;

public class CustomDialogAddcategory extends Dialog {

    @BindView(R.id.add_item_food_tv_food)
    TextView addItemFoodTvFood;
    @BindView(R.id.add_item_food_circleimage_photo)
    CircleImageView addItemFoodCircleimagePhoto;
    @BindView(R.id.add_item_food_et_food_name)
    EditText addItemFoodEtFoodName;
    @BindView(R.id.add_item_food_btn_add)
    Button addItemFoodBtnAdd;
    CategoryRestaurantAdapter categoryRestaurantAdapter;
    Unbinder unbinder;

    public Activity c;
    public HomeRestaurantFragment homeRestaurantFragment;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;
    public ResataurantData resataurantData;

    public CustomDialogAddcategory(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_tiem_food);
        unbinder = ButterKnife.bind(this);

    }

    @OnClick({R.id.add_item_food_circleimage_photo, R.id.add_item_food_btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_item_food_circleimage_photo:
                HelperMethod.openGallery(getContext(), 1, imagesList, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {

                        imagesList.clear();
                        imagesList = result;

                        path = imagesList.get(0).getPath();

                        HelperMethod.onLoadImageFromUrl(addItemFoodCircleimagePhoto, path, getContext());

                    }
                });
                break;
            case R.id.add_item_food_btn_add:
                andNewItem();
                break;
        }
    }

    private void andNewItem() {
        String name = addItemFoodEtFoodName.getText().toString();
        APIManger.getApis().newItem(convertToRequestBody(name),
                convertFileToMultipart(path, "photo"),
                convertToRequestBody(resataurantData.getApiToken()))
                .enqueue(new Callback<NewCategoryRestaurantResponse>() {
                    @Override
                    public void onResponse(Call<NewCategoryRestaurantResponse> call, Response<NewCategoryRestaurantResponse> response) {
                        if (response.body().getStatus() == 1) {

                            homeRestaurantFragment.categoryRestaurantDataItemList.add(response.body().getData());
                            homeRestaurantFragment.categoryRestaurantAdapter.notifyDataSetChanged();

                            dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewCategoryRestaurantResponse> call, Throwable t) {

                    }
                });
    }

}

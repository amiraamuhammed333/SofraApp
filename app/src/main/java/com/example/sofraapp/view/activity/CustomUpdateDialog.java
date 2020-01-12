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
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.update_category.UpdateCategoryResponse;
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

import static com.example.sofraapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofraapp.helper.HelperMethod.convertToRequestBody;
import static com.example.sofraapp.helper.HelperMethod.onLoadImageFromUrl;

public class CustomUpdateDialog extends Dialog {

    public int id;
    public  CategoryRestaurantDataItem restaurant;
    @BindView(R.id.add_item_food_tv_food)
    TextView addItemFoodTvFood;
    @BindView(R.id.add_item_food_circleimage_photo)
    CircleImageView addItemFoodCircleimagePhoto;
    @BindView(R.id.add_item_food_et_food_name)
    EditText addItemFoodEtFoodName;
    @BindView(R.id.add_item_food_btn_add)
    Button addItemFoodBtnAdd;
    public ResataurantData resataurantData;

    Unbinder unbinder;

    public Activity c;
    public HomeRestaurantFragment homeRestaurantFragment;
    private ArrayList<AlbumFile> imagesList = new ArrayList<>();
    private String path;
    String name;

    public CustomUpdateDialog(Activity a) {
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
        addItemFoodEtFoodName.setText(restaurant.getName());
        onLoadImageFromUrl ( addItemFoodCircleimagePhoto, restaurant.getPhoto_url(),getContext() );
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
                updateCategory();
                break;
        }
    }
//

    private void updateCategory() {


         name = addItemFoodEtFoodName.getText().toString();
        APIManger.getApis().updateCategory(convertToRequestBody(name),
                convertFileToMultipart(path, "photo"),
                convertToRequestBody(resataurantData.getApiToken()),
                convertToRequestBody(String.valueOf(restaurant.getId())))
                .enqueue(new Callback<UpdateCategoryResponse>() {
            @Override
            public void onResponse(Call<UpdateCategoryResponse> call, Response<UpdateCategoryResponse> response) {
                if (response.body().getStatus() == 1) {
        if (response.isSuccessful()) {

            dismiss();
            Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
        }

    }
            }

            @Override
            public void onFailure(Call<UpdateCategoryResponse> call, Throwable t) {

            }
        });
    }

}

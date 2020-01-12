package com.example.sofraapp.helper;


import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.data.model.GeneralResponse.GeneralResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequests {

    public static void getSpinnerData(final Call<GeneralResponse> method, final Spinner spinner
            , final CustomSpinnerAdapter adapter, final String hint, AdapterView.OnItemSelectedListener listener) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    if (response.body().getStatus() == 1) {

                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(listener);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }


    public static void getSpinnerData(final Call<GeneralResponse> method, final Spinner spinner
            , final CustomSpinnerAdapter adapter, final String hint) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    if (response.body().getStatus() == 1) {

                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }

    public static void getSpinnerData(final Call<GeneralResponse> method, final Spinner spinner
            , final CustomSpinnerAdapter adapter, final String hint, AdapterView.OnItemSelectedListener listener, int selectedId) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    if (response.body().getStatus() == 1) {

                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);

                        int pos = 0;
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if (response.body().getData().get(i).getId() == selectedId) {
                                pos = i +1;
                                break;
                            }
                        }
                        spinner.setSelection(pos);
                        spinner.setOnItemSelectedListener(listener);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }


    public static void getSpinnerData(final Call<GeneralResponse> method, final Spinner spinner
            , final CustomSpinnerAdapter adapter, final String hint, int selectedId) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    if (response.body().getStatus() == 1) {

                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);

                        int pos = 0;
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if (response.body().getData().get(i).getId() == selectedId) {
                                pos = i +1;
                                break;
                            }
                        }
                        spinner.setSelection(pos);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }


}

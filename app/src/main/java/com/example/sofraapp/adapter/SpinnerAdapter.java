package com.example.sofraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.model.GeneralResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<GeneralResponseData> GeneralResponceDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;

    public SpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GeneralResponseData> generalResponseDataList, String hint) {
        this.GeneralResponceDataList = new ArrayList<>();
        this.GeneralResponceDataList.add(new GeneralResponseData(0, hint));
        this.GeneralResponceDataList.addAll(generalResponseDataList);
    }

    @Override
    public int getCount() {
        return GeneralResponceDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_custom_spinner, null);

        TextView names = (TextView) view.findViewById(R.id.custom_spinner_adapter_tv_name);

        names.setText(GeneralResponceDataList.get(i).getName());
        selectedId = GeneralResponceDataList.get(i).getId();

        return view;
    }
}
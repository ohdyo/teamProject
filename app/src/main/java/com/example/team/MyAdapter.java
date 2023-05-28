package com.example.team;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Data> sample;

    public MyAdapter(Context context, ArrayList<Data> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    public String getBody(String body) {
        return body;
    }

    public String getSchList(String schList) {
        return schList;
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Data getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.health_list, null);


        TextView body = (TextView)view.findViewById(R.id.body);
        TextView schList = (TextView)view.findViewById(R.id.schList);


        body.setText(sample.get(position).getBody());

        schList.setText(sample.get(position).getSportName()+"-"+sample.get(position).getSetNum()+"-"+sample.get(position).getNum());


        return view;
    }


}

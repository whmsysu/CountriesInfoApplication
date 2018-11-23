package com.example.haominwu.countriesinfoapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.haominwu.countriesinfoapplication.R;
import com.example.haominwu.countriesinfoapplication.model.Country;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CountriesAdapter extends BaseAdapter {

    public ArrayList<Country> countries = new ArrayList<>();

    private WeakReference<Context> mContext;

    public CountriesAdapter(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext.get()).inflate(R.layout.layout_country_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewCountryName.setText(countries.get(position).getName());

        return convertView;
    }

    private static class ViewHolder {
        public TextView textViewCountryName;

        public ViewHolder(View view) {
            textViewCountryName = view.findViewById(R.id.tv_country_name);
        }
    }
}

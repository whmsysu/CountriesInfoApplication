package com.example.haominwu.countriesinfoapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.haominwu.countriesinfoapplication.R
import com.example.haominwu.countriesinfoapplication.model.Country
import java.lang.ref.WeakReference

class CountriesAdapter(context: Context) : BaseAdapter() {

    private var mContext:WeakReference<Context>?= null

    var countries = ArrayList<Country>()

    init {
        mContext = WeakReference(context)
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(mContext!!.get()).inflate(R.layout.layout_country_item, null,false)
        view.findViewById<TextView>(R.id.tv_country_name).text = countries[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return countries[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return countries.size
    }

}
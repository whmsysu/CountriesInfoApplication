package com.example.haominwu.countriesinfoapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.haominwu.countriesinfoapplication.R
import com.example.haominwu.countriesinfoapplication.activity.DetailActivity
import com.example.haominwu.countriesinfoapplication.model.Country

import java.lang.ref.WeakReference
import java.util.ArrayList

class CountriesAdapter(context: Context) : BaseAdapter() {

    var countries = ArrayList<Country>()

    private val mContext: WeakReference<Context> = WeakReference(context)

    override fun getCount(): Int {
        return countries.size
    }

    override fun getItem(position: Int): Any {
        return countries[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext.get()!!).inflate(R.layout.layout_country_item, parent, false)
            viewHolder = ViewHolder(convertView!!)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.textViewCountryName.text =
                String.format("%s %s", countries[position].emoji, countries[position].name)
        convertView.setOnClickListener {
            DetailActivity.newInstance(mContext.get()!!, countries[position].name, countries[position].code)
        }
        return convertView
    }

    private class ViewHolder(view: View) {
        var textViewCountryName: TextView = view.findViewById(R.id.tv_country_name)

    }
}

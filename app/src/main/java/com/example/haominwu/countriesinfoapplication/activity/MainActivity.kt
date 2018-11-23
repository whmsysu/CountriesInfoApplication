package com.example.haominwu.countriesinfoapplication.activity

import android.view.View
import android.widget.Toast
import com.example.haominwu.countriesinfoapplication.R
import com.example.haominwu.countriesinfoapplication.adapter.CountriesAdapter
import com.example.haominwu.countriesinfoapplication.contract.CountriesDisplayContract
import com.example.haominwu.countriesinfoapplication.model.Country
import com.example.haominwu.countriesinfoapplication.presenter.CountriesDisplayPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), CountriesDisplayContract.View {

    override fun showError() {
        pb_loading.visibility = View.GONE
        lv_countries.visibility = View.GONE
        Toast.makeText(this, "Error loading api", Toast.LENGTH_LONG).show()
    }

    private var countriesAdapter: CountriesAdapter = CountriesAdapter(this)

    override fun showCountriesList(countriesList: ArrayList<Country>) {
        countriesAdapter.countries.clear()
        countriesAdapter.countries.addAll(countriesList)
        countriesAdapter.notifyDataSetChanged()
        pb_loading.visibility = View.GONE
        lv_countries.visibility = View.VISIBLE
    }

    private var presenter = CountriesDisplayPresenter()

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
        lv_countries.visibility = View.GONE
    }

    override fun initView() {
        super.initView()
        this.presenter.attachView(this)
        lv_countries.adapter = this.countriesAdapter
        showLoading()
        this.presenter.fetchCountries()
        title = "All Countries"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.detachView()
    }
}

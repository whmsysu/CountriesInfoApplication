package com.example.haominwu.countriesinfoapplication.activity

import android.view.View
import com.example.haominwu.countriesinfoapplication.R
import com.example.haominwu.countriesinfoapplication.adapter.CountriesAdapter
import com.example.haominwu.countriesinfoapplication.contract.CountriesDisplayContract
import com.example.haominwu.countriesinfoapplication.model.Country
import com.example.haominwu.countriesinfoapplication.presenter.CountriesDisplayPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), CountriesDisplayContract.View {

    private var countriesAdapter: CountriesAdapter = CountriesAdapter(this)

    override fun showCountriesList(countriesList: ArrayList<Country>) {
        pb_loading.visibility = View.GONE
        countriesAdapter.countries.clear()
        countriesAdapter.countries.addAll(countriesList)
        countriesAdapter.notifyDataSetChanged()
        lv_countries.visibility = View.VISIBLE
    }

    private var presenter = CountriesDisplayPresenter()

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
        lv_countries.visibility = View.GONE
    }

    override fun initView() {
        super.initView()
        lv_countries.adapter = this.countriesAdapter
        this.presenter.attachView(this)
        showLoading()
        this.presenter.fetchCountries()
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.detachView()
    }
}

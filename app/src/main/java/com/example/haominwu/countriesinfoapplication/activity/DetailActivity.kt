package com.example.haominwu.countriesinfoapplication.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.haominwu.countriesinfoapplication.R
import com.example.haominwu.countriesinfoapplication.contract.SingleCountryDisplayContract
import com.example.haominwu.countriesinfoapplication.model.Country
import com.example.haominwu.countriesinfoapplication.presenter.CountryDisplayPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), SingleCountryDisplayContract.View {

    private var presenter = CountryDisplayPresenter()

    companion object {
        fun newInstance(context: Context, name: String?, code: String?) {
            val intent = Intent()
            intent.setClass(context, DetailActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("code", code)
            context.startActivity(intent)
        }
    }

    override fun showError() {
        pb_loading.visibility = View.GONE
        ll_country.visibility = View.GONE
        Toast.makeText(this, "Error loading api", Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
        ll_country.visibility = View.GONE
    }

    override fun showCountryInfo(country: Country) {
        pb_loading.visibility = View.GONE
        ll_country.visibility = View.VISIBLE
        tv_country_name.text = country.name
        tv_emoji.text = country.emoji
        tv_code.text = country.code
        tv_native.text = country.native
        tv_phone.text = country.phone
        tv_currency.text = country.currency
    }

    override fun initView() {
        super.initView()
        presenter.attachView(this)
        showLoading()
        val name = intent.getStringExtra("name")
        val code = intent.getStringExtra("code")
        presenter.fetchSingleCountryByCode(code)
        title = name
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_detail
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}

package com.example.haominwu.countriesinfoapplication.contract

import com.example.haominwu.countriesinfoapplication.model.Country

interface SingleCountryDisplayContract {

    interface View {
        fun showLoading()
        fun showCountryInfo(country: Country)
        fun showError()
    }

    interface Presenter {
        fun attachView(view: View)
        fun fetchSingleCountryByCode(code: String)
        fun detachView()
    }
}
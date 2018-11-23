package com.example.haominwu.countriesinfoapplication.contract

import com.example.haominwu.countriesinfoapplication.model.Country
import com.example.haominwu.countriesinfoapplication.view.BaseView

interface SingleCountryDisplayContract {

    interface View : BaseView {
        fun showCountryInfo(country: Country)
    }

    interface Presenter {
        fun attachView(view: View)
        fun fetchSingleCountryByCode(code: String)
        fun detachView()
    }
}
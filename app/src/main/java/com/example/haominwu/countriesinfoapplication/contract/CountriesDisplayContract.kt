package com.example.haominwu.countriesinfoapplication.contract

import com.example.haominwu.countriesinfoapplication.model.Country
import com.example.haominwu.countriesinfoapplication.view.BaseView

interface CountriesDisplayContract {

    interface View: BaseView {
        fun showCountriesList(countriesList: ArrayList<Country>)
    }

    interface Presenter {
        fun attachView(view: View)
        fun fetchCountries()
        fun detachView()
    }

}
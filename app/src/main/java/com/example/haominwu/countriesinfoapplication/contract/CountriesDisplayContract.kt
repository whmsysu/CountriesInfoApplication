package com.example.haominwu.countriesinfoapplication.contract

import com.example.haominwu.countriesinfoapplication.model.Country

interface CountriesDisplayContract {

    interface View {
        fun showLoading()
        fun showCountriesList(countriesList: ArrayList<Country>)
        fun showError()
    }

    interface Presenter {
        fun attachView(view: View)
        fun fetchCountries()
        fun detachView()
    }

}
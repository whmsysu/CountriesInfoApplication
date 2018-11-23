package com.example.haominwu.countriesinfoapplication.presenter

import com.example.haominwu.countriesinfoapplication.api.HttpUtil
import com.example.haominwu.countriesinfoapplication.contract.CountriesDisplayContract
import com.example.haominwu.countriesinfoapplication.model.Country
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


class CountriesDisplayPresenter : CountriesDisplayContract.Presenter {
    override fun fetchCountries() {
        var observable: Observable<ArrayList<Country>>? = HttpUtil.getInstance().fetchCountriesApiCall()

        observable!!.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<ArrayList<Country>> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ArrayList<Country>) {
                view?.showCountriesList(t)
            }

            override fun onError(e: Throwable) {
                view?.showError()
            }

        })
    }

    private var view: CountriesDisplayContract.View? = null

    override fun attachView(view: CountriesDisplayContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}
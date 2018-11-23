package com.example.haominwu.countriesinfoapplication.presenter

import com.example.haominwu.countriesinfoapplication.api.HttpUtil
import com.example.haominwu.countriesinfoapplication.contract.SingleCountryDisplayContract
import com.example.haominwu.countriesinfoapplication.model.Country
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class CountryDisplayPresenter : SingleCountryDisplayContract.Presenter {

    private var view: SingleCountryDisplayContract.View? = null

    override fun attachView(view: SingleCountryDisplayContract.View) {
        this.view = view
    }

    override fun fetchSingleCountryByCode(code: String) {
        var observable: Observable<Country>? = HttpUtil.getInstance().fetchSingleCountryApiCall(code)
        observable!!.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Country> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Country) {
                view?.showCountryInfo(t)
            }

            override fun onError(e: Throwable) {

            }

        })
    }

    override fun detachView() {
        this.view = null
    }
}
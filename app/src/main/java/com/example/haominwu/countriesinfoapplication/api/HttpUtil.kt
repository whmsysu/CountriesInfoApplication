package com.example.haominwu.countriesinfoapplication.api

import CountriesQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.haominwu.countriesinfoapplication.model.Country
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import okhttp3.OkHttpClient


class HttpUtil {

    private var apolloClient: ApolloClient? = null
    private var countriesQuery = CountriesQuery.builder().build()

    private constructor()

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        apolloClient = ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }

    fun fetchCountriesApiCall(): Observable<ArrayList<Country>>? {

        return Observable.create {
            val countriesCall = apolloClient!!
                .query(countriesQuery)

            countriesCall.enqueue(object : ApolloCall.Callback<CountriesQuery.Data>() {
                override fun onResponse(response: Response<CountriesQuery.Data>) {
                    val data = response.data()
                    Logger.d("onResponse")

                    val resultList = ArrayList<Country>()
                    for (country: CountriesQuery.Country in data!!.countries()!!) {
                        resultList.add(Country(country.name(), country.emoji(), country.code()))
                    }
                    it.onNext(resultList)
                    it.onComplete()
                }

                override fun onFailure(e: ApolloException) {
                    Logger.d("onFailure")
                    it.onError(e)
                }
            })
        }
    }

    fun fetchSingleCountryApiCall(code: String): Observable<Country>? {

        return Observable.create {
            var singleCountryQuery = SingleCountryQuery.builder().code(code).build()
            val singleCountryCall = apolloClient!!
                .query(singleCountryQuery)

            singleCountryCall.enqueue(object : ApolloCall.Callback<SingleCountryQuery.Data>() {
                override fun onResponse(response: Response<SingleCountryQuery.Data>) {
                    Logger.d("onResponse")
                    val data = response.data()
                    Logger.d(data)
                    var country = Country(
                        name = data!!.country()!!.name(),
                        emoji = data.country()!!.emoji(),
                        code = data.country()!!.code(),
                        native = data.country()!!.native_(),
                        phone = data.country()!!.phone(),
                        currency = data.country()!!.currency()
                    )
                    it.onNext(country)
                    it.onComplete()
                }

                override fun onFailure(e: ApolloException) {
                    Logger.d("onFailure")
                    it.onError(e)
                }
            })
        }

    }

    companion object {
        private const val BASE_URL = "https://countries.trevorblades.com/"
        val instance: HttpUtil by lazy { HttpUtil() }
    }
}
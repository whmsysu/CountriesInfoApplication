package com.example.haominwu.countriesinfoapplication.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        initView()
    }

    open fun initView() {

    }

    abstract fun getLayoutID(): Int
}
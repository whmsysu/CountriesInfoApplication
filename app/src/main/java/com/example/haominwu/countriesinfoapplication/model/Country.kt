package com.example.haominwu.countriesinfoapplication.model

data class Country(
    val name: String?,
    val emoji: String?,
    val code: String?,
    val native: String? = null,
    val phone: String? = null,
    val currency: String? = null
)
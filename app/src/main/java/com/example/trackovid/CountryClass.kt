package com.example.trackovid

class CountryClass {
    companion object {
        var selectedCountry: String? = null
        fun setCountry(country: String) {
            selectedCountry = country
        }
    }
}
package com.example.trackovid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_shared_pref.*


class SharedPrefActivity : AppCompatActivity() {

    private lateinit var countryName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)

        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.splash_title))
        window.statusBarColor = ContextCompat.getColor(this, R.color.splash_status)

        btnNext.setOnClickListener {
            saveData()
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
    }

    private fun saveData() {
        countryName = ccp.selectedCountryName.toString()

        val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("COUNTRY", countryName)
        }.apply()
        CountryClass.selectedCountry = countryName
    }
}
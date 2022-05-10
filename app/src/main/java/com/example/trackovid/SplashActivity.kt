package com.example.trackovid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.splash_title))
        window.statusBarColor = ContextCompat.getColor(this, R.color.splash_status)

        loadData()

        Handler(Looper.getMainLooper()).postDelayed({
            if(CountryClass.selectedCountry == null) {
                startActivity(Intent(this, SharedPrefActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 1000)
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val country = sharedPreferences.getString("COUNTRY", null)
        if(country != null) {
            CountryClass.selectedCountry = country
        }
    }
}
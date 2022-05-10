package com.example.trackovid

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.row_layout.*
import kotlinx.android.synthetic.main.row_layout.view.*
import java.text.NumberFormat

class CountryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.main_back))
        window.statusBarColor = ContextCompat.getColor(this, R.color.main_back)

        val bundle = intent.extras
        if (bundle != null) {
            val flagPath = bundle.getString("flagLocation")
            Glide.with(this).load(flagPath)
                .into(imageViewCountryImage)
            tvCountryName.text = bundle.getString("country")
            tvPopulation.text = NumberFormat.getIntegerInstance().format(bundle.getString("population")
                ?.toInt())
            tvConfirmed.text = NumberFormat.getIntegerInstance().format(bundle.getString("cases")?.toInt())
            tvTodayCases.text = "+" + NumberFormat.getIntegerInstance().format(bundle.getString("todayCases")?.toInt())
            tvActive.text = NumberFormat.getIntegerInstance().format(bundle.getString("active")?.toInt())
            tvRecovered.text = NumberFormat.getIntegerInstance().format(bundle.getString("recovered")?.toInt())
            tvTodayRecovered.text = "+" + NumberFormat.getIntegerInstance().format(bundle.getString("todayRecovered")?.toInt())
            tvDeceased.text = NumberFormat.getIntegerInstance().format(bundle.getString("deceased")?.toInt())
            tvTodayDeaths.text = "+" + NumberFormat.getIntegerInstance().format(bundle.getString("todayDeceased")?.toInt())
            tvSamplesTested.text = NumberFormat.getIntegerInstance().format(bundle.getString("tests")?.toInt())
        }



    }
}
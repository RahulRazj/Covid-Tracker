package com.example.trackovid

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.trackovid.utils.Constants.Companion.defaultSelectedColor
import com.example.trackovid.utils.Constants.Companion.selectedColor
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryDataViewModel

    private var countrySelected: Boolean = true
    private var worldSelected: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.main_back))
        window.statusBarColor = ContextCompat.getColor(this, R.color.main_back)

        CountryDataSelected.setCardBackgroundColor(Color.parseColor(selectedColor))

        WorldData.setOnClickListener{

            if(!worldSelected) {
                worldSelected = true
                countrySelected = false

                WorldDataSelected.setCardBackgroundColor(Color.parseColor(selectedColor))
                CountryDataSelected.setCardBackgroundColor(Color.parseColor(defaultSelectedColor))

                val fragment = WorldFragment()

                with(supportFragmentManager.beginTransaction()) {
                    replace(R.id.fragmentContainerView,fragment)
                    //addToBackStack(null)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    commit()
                }
            }
        }

        CountryData.setOnClickListener{

            if(!countrySelected) {
                worldSelected = false
                countrySelected = true

                CountryDataSelected.setCardBackgroundColor(Color.parseColor(selectedColor))
                WorldDataSelected.setCardBackgroundColor(Color.parseColor(defaultSelectedColor))


                val fragment = NepalFragment()

                with(supportFragmentManager.beginTransaction()) {
                    replace(R.id.fragmentContainerView,fragment)
                    //addToBackStack(null)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    commit()
                }
            }
        }
    }
}
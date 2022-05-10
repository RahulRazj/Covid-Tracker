package com.example.trackovid

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.trackovid.repository.Repository
import kotlinx.android.synthetic.main.fragment_country.*
import java.text.NumberFormat


class NepalFragment : Fragment() {

    private lateinit var viewModel: CountryDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_country, container, false)

        val repository = Repository()
        val viewModelFactory = CountryDataViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(CountryDataViewModel::class.java)
        viewModel.getData(CountryClass.selectedCountry!!)
        viewModel.myResponse.observe(requireActivity(), Observer { response ->

            if(response.isSuccessful) {
                tvConfirmed.text = NumberFormat.getIntegerInstance().format(response.body()?.cases)
                tvActive.text = NumberFormat.getIntegerInstance().format(response.body()?.active)
                tvRecovered.text = NumberFormat.getIntegerInstance().format(response.body()?.recovered)
                tvDeceased.text = NumberFormat.getIntegerInstance().format(response.body()?.deaths)
                tvSamplesTested.text = NumberFormat.getIntegerInstance().format(response.body()?.tests)
                tvCountryName.text = response.body()?.country
                tvPopulation.text = NumberFormat.getIntegerInstance().format(response.body()?.population)
                tvTodayCases.text = "+" + NumberFormat.getIntegerInstance().format(response.body()?.todayCases)
                tvTodayDeaths.text = "+" + NumberFormat.getIntegerInstance().format(response.body()?.todayDeaths)
                tvTodayRecovered.text = "+" + NumberFormat.getIntegerInstance().format(response.body()?.todayRecovered)
                Glide.with(this).load(response.body()?.countryInfo?.flag).into(imageViewCountryImage)
            } else {
                Toast.makeText(requireContext(),"Network Error/Slow Internet", Toast.LENGTH_LONG).show()
            }
        })
        return view
    }

}
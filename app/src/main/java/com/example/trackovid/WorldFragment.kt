package com.example.trackovid

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackovid.repository.Repository
import com.example.trackovid.utils.hideKeyBoard
import kotlinx.android.synthetic.main.fragment_world.view.*
import java.util.*


class WorldFragment : Fragment() {


    private lateinit var mWorldDataViewModel: WorldDataViewModel
    var WorldDataList = emptyList<CountryDataItem>()
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_world, container, false)


        val repository = Repository()
        val viewModelFactory = WorldDataViewModelFactory(repository)


        // Setup Recycler View
        adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mWorldDataViewModel =
            ViewModelProvider(this, viewModelFactory).get(WorldDataViewModel::class.java)

        mWorldDataViewModel.getAllData()
        mWorldDataViewModel.myResponse.observe(requireActivity(), Observer { response ->
            if (response.isSuccessful) {
                WorldDataList = response.body()!!
                WorldDataList.let { adapter.setData(it) }
            }
        })


        hideKeyBoard(requireActivity())

        view.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterData(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        return view
    }

    private fun filterData(text: String) {
        val filteredList: MutableList<CountryDataItem> = mutableListOf<CountryDataItem>()
        for(items in WorldDataList) {

            if(items.country.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredList.add(items)
            }
        }
        adapter.setData(filteredList)

    }

}
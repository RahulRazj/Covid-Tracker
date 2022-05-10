package com.example.trackovid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_layout.view.*
import java.text.NumberFormat

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<CountryDataItem>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentList = userList[position]

        holder.itemView.tvCountry.text = currentList.country
        holder.itemView.tvTotalCases.text = NumberFormat.getIntegerInstance().format(currentList.cases)
        Glide.with(holder.itemView).load(currentList.countryInfo.flag)
            .into(holder.itemView.imageViewCountryFlag)

        holder.itemView.row_background.setOnClickListener {

            val intent = Intent(holder.itemView.context, CountryActivity::class.java).apply {
                putExtra("country", currentList.country)
                putExtra("population", currentList.population.toString())
                putExtra("cases", currentList.cases.toString())
                putExtra("todayCases", currentList.todayCases.toString())
                putExtra("active", currentList.active.toString())
                putExtra("recovered", currentList.recovered.toString())
                putExtra("todayRecovered", currentList.todayRecovered.toString())
                putExtra("deceased", currentList.deaths.toString())
                putExtra("todayDeceased", currentList.todayDeaths.toString())
                putExtra("tests", currentList.tests.toString())
                putExtra("flagLocation", currentList.countryInfo.flag)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<CountryDataItem>) {
        this.userList = user
        notifyDataSetChanged()
    }
}
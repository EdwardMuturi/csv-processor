package com.edward.csvprocessor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edward.csvprocessor.R
import com.edward.csvprocessor.model.Cities
import com.edward.csvprocessor.util.CitiesDiffItem
import kotlinx.android.synthetic.main.data_row.view.*

/**
 * Created by Edward Muturi on 20/09/2019.
 */
class CSVParserAdapter : ListAdapter<Cities, CSVParserAdapter.ViewHolder>(CitiesDiffItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.data_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city= getItem(position) as Cities

        holder.itemView.tvLatD.text= city.latD
        holder.itemView.tvLatM.text= city.latM
        holder.itemView.tvLatS.text= city.latS
        holder.itemView.tvNs.text= city.nS
        holder.itemView.tvLonD.text= city.lonD
        holder.itemView.tvLonM.text= city.lonM
        holder.itemView.tvLonS.text= city.lonS
        holder.itemView.tveW.text= city.eW
        holder.itemView.tvCity.text= city.city
        holder.itemView.tvState.text= city.state

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
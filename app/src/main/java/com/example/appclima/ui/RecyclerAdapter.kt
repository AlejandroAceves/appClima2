package com.example.appclima.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appclima.R
import com.example.appclima.data.model.Forecast
import org.w3c.dom.Text

class RecyclerAdapter (val items: ArrayList<Forecast>?): RecyclerView.Adapter<RecyclerAdapter.TimesViewHolder>(){
    private var onContractClickListener: OnContractClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.TimesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.forcast_item,parent,false)
        return TimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.TimesViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onContractClickListener?.onClick(position,item!!)
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun setOnClickListener (onContractClickListener: OnContractClickListener){
        this.onContractClickListener = onContractClickListener
    }

    class TimesViewHolder (view: View):RecyclerView.ViewHolder(view){
        val max : TextView = view.findViewById(R.id.forcast_max)
        val min : TextView = view.findViewById(R.id.forcast_min)

        fun bind(time:Forecast?){
            if (time != null){
                max.text=time.main?.temp_max.toString()
                min.text=time.main?.temp_min.toString()
            }
        }
    }

    interface OnContractClickListener{
        fun onClick(position: Int,forecast: Forecast)
    }

}
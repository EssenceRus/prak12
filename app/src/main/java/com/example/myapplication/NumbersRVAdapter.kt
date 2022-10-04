package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumbersRVAdapter(context: Context?, val data: MutableList<Numbers>): RecyclerView.Adapter<NumbersRVAdapter.NumberViewHolder?>(){
    private val layoutInflater:LayoutInflater=LayoutInflater.from((context))
    private var iClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view: View = layoutInflater.inflate(R.layout.item_numbers,parent,false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.name
        holder.surename.text = item.surename
        holder.number.text = item.number
    }




    override fun getItemCount(): Int = data.size
    inner class NumberViewHolder(item: View): RecyclerView.ViewHolder(item), View.OnClickListener {
        var name = item.findViewById<TextView>(R.id.Name)
        var surename = item.findViewById<TextView>(R.id.Surename)
        var number = item.findViewById<TextView>(R.id.Number)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iClickListener?.onItemClick(view, adapterPosition)
        }
    }

        fun setOnClickListener(itemClickListener: ItemClickListener?) {
            iClickListener = itemClickListener
        }

        interface ItemClickListener {
            fun onItemClick(view: View?, position: Int)
        }
    }



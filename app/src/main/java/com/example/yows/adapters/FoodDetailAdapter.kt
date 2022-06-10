package com.example.yows.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yows.R
import com.example.yows.models.FoodDetail
import com.squareup.picasso.Picasso

class FoodDetailAdapter(private var list: ArrayList<FoodDetail>) : RecyclerView.Adapter<FoodDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_food_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodRecord = list[position]
        holder.name.text = foodRecord.name
        holder.detail.text = foodRecord.detail
        holder.discountPrice.text = foodRecord.discountPrice
        holder.price.text = foodRecord.price
        Picasso.get().load(foodRecord.image).error(R.mipmap.ic_launcher).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val detail : TextView = itemView.findViewById(R.id.tvDetail)
        val discountPrice : TextView = itemView.findViewById(R.id.tvDiscountPrice)
        val price : TextView = itemView.findViewById(R.id.tvPrice)
        var image : ImageView = itemView.findViewById(R.id.img)
    }
}
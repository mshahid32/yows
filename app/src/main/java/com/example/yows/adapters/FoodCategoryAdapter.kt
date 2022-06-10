package com.example.yows.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yows.R
import com.example.yows.models.FoodCategory
import com.example.yows.models.FoodDetail

class FoodCategoryAdapter(private var list: ArrayList<FoodCategory>) : RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodCategoryAdapter.ViewHolder, position: Int) {
        val category = list[position]
        holder.categoryName.text = category.categoryName
        // Create layout manager with initial prefetch item count
        val layoutManager = LinearLayoutManager(
            holder.rvFoodDetail.getContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        holder.rvFoodDetail.layoutManager = layoutManager
        holder.rvFoodDetail.adapter = FoodDetailAdapter(category.foodList!!)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        val rvFoodDetail : RecyclerView = itemView.findViewById(R.id.rv_sub_item)
    }
}
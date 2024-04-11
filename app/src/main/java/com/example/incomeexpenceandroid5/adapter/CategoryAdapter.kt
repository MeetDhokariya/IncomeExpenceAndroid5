package com.example.incomeexpenceandroid5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.incomeexpenceandroid5.R
import com.example.incomeexpenceandroid5.model.CategoryModel

class CategoryAdapter(val context: Context,val categorylist:ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    class CategoryHolder(itemView: View) : ViewHolder(itemView){
        var categoryTile =  itemView.findViewById<TextView>(R.id.categoryTile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
       var view = LayoutInflater.from(context).inflate(R.layout.category_tile,parent,false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
            return categorylist.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
       holder.categoryTile.text = categorylist.get(position).name
    }
}
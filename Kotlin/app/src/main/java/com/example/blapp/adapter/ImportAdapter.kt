package com.example.blapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.blapp.R
import com.example.blapp.model.PgmItem

class ImportAdapter(internal var context: FragmentActivity?,internal var itemList:List<PgmItem>):
    RecyclerView.Adapter<ImportViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImportViewHolder {
        val itemView= LayoutInflater.from(context)
            .inflate(R.layout.fragment_import_list, parent, false)
        return ImportViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: ImportViewHolder, position: Int) {
        var importhold: String? = itemList[position].name
        holder.imporTitle.text = importhold.toString()
    }


}
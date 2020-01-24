package com.example.blapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_import_list.view.*

class ImportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imporTitle : TextView
    init {
        imporTitle = itemView.import_title
    }


}
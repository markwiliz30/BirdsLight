package com.example.blapp.programitem

import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.blapp.R
import kotlinx.android.synthetic.main.pgm_item.*

class PgmHolder(@NonNull itemView: View) :
    RecyclerView.ViewHolder(itemView){
    var exc = itemView.findViewById(R.id.img_pgm_del) as ImageView
    var v = exc.setOnClickListener{

    }
}

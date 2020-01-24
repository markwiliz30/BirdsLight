package com.example.blapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.blapp.R
import com.example.blapp.model.WifiItem

class WifiAdapter(internal var context: FragmentActivity?, internal var itemList:MutableList<WifiItem>):
RecyclerView.Adapter<WifiViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.wifi_item, parent, false)
        return WifiViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
        var wifiNameHold: String? = itemList[position].name
        holder.lblWifiName.text = wifiNameHold.toString()

        var wifiStatusHold: Int? = itemList[position].status
        when(wifiStatusHold){
            0 -> holder.lblWifiStatus.text = "Not Connected"
            1 -> holder.lblWifiStatus.text = "Connecting..."
            2 -> holder.lblWifiStatus.text = "Connected!"
            else -> holder.lblWifiStatus.text = "Not Connected"
        }

        var wifiLvlHold: Int? = itemList[position].level
        when(wifiLvlHold){
            0 -> holder.lblWifiLvl.setImageResource(R.drawable.ic_signal_wifi_0_bar_dark_blue_24dp)
            1 -> holder.lblWifiLvl.setImageResource(R.drawable.ic_signal_wifi_1_bar_dark_blue_24dp)
            2 -> holder.lblWifiLvl.setImageResource(R.drawable.ic_signal_wifi_2_bar_dark_blue_24dp)
            3 -> holder.lblWifiLvl.setImageResource(R.drawable.ic_signal_wifi_3_bar_dark_blue_24dp)
            4 -> holder.lblWifiLvl.setImageResource(R.drawable.ic_signal_wifi_4_bar_dark_blue_24dp)
            else -> holder.lblWifiLvl.setImageResource(R.drawable.ic_signal_wifi_0_bar_dark_blue_24dp)
        }
    }
}
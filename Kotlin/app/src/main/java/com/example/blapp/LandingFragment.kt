package com.example.blapp

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blapp.adapter.WifiAdapter
import com.example.blapp.common.DeviceProtocol
import com.example.blapp.common.Protocol
import com.example.blapp.model.WifiItem
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_landing.*

/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : Fragment() {
    lateinit var navController: NavController
    val deviceProtocol = DeviceProtocol()

    lateinit var layoutManager: LinearLayoutManager

    lateinit var adapter: WifiAdapter

    var wifiList = ArrayList<WifiItem>()

    lateinit var wifiManager: WifiManager
    private var results = ArrayList<ScanResult>()

    val wifiReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            results = wifiManager.scanResults as ArrayList<ScanResult>
//            activity!!.unregisterReceiver(this)
//
//            for (item in results!!)
//            {
//                val newWifiItem = WifiItem()
//                newWifiItem.name = item.SSID
//                newWifiItem.status = 0
//                newWifiItem.level = 4
//                wifiList.add(newWifiItem)
//                adapter.notifyDataSetChanged()
//            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Protocol.cDeviceProt = deviceProtocol
        wifiManager = this.activity!!.getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager

        if(!wifiManager!!.isWifiEnabled)
        {
            Toast.makeText(activity, "Wifi is disabled", Toast.LENGTH_SHORT).show()
            wifiManager!!.isWifiEnabled = true
        }
        activity!!.registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))

        adapter = WifiAdapter(activity, wifiList)
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    private fun scanWifi()
    {
        activity!!.registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager.startScan()
        Toast.makeText(activity, "Scanning Wifi...", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lst_wifi.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        lst_wifi.layoutManager = layoutManager

        navController = Navigation.findNavController(view)
        Protocol.cDeviceProt.startChannel()

        adapter = WifiAdapter(activity, wifiList)
        lst_wifi.adapter = adapter

        testbt.setOnClickListener{

            val dialog: AlertDialog = SpotsDialog(activity, R.style.Custom)
            dialog.show()
            var postDelayedSendToModule = Handler()
            var sendToModule = Runnable {
                generateItem()
                dialog.dismiss()
            }
            postDelayedSendToModule.postDelayed(sendToModule, 3000)
        }

        testbt2.setOnClickListener{
            scanWifi()
        }
    }

    private fun generateItem() {

        var item1 = WifiItem()
        item1.name = "waw"
        item1.status = 0
        item1.level = 3
        wifiList.add(item1)

        var item2 = WifiItem()
        item2.name = "wew"
        item2.status = 2
        item2.level = 4
        wifiList.add(item2)

        adapter = WifiAdapter(activity, wifiList)
        lst_wifi.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        Protocol.cDeviceProt.stopChannel()
    }
}

package com.example.blapp

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    var wifiList = ArrayList<WifiItem>()

    var resultList = ArrayList<ScanResult>()
    lateinit var wifiManager: WifiManager
    lateinit var adapter: WifiAdapter

    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            resultList = wifiManager.scanResults as ArrayList<ScanResult>
//            val handler = Handler()
//            handler.postDelayed({
//                stopScanning()
//            }, 5000)
            displayScannedWifi()
            Toast.makeText(activity, resultList.count().toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayScannedWifi() {
        wifiList.clear()
        var sameSSIDCount = 1
        for(item in resultList)
        {
            var newWifiItem = WifiItem()
            val found = wifiList.filter { it.name == item.SSID }
            if(found.count() != 0)
            {
                sameSSIDCount++
                newWifiItem.name = item.SSID + " " + sameSSIDCount
            }
            else
            {
                newWifiItem.name = item.SSID
            }
            newWifiItem.level = WifiManager.calculateSignalLevel(item.level, 5)
            newWifiItem.status = 2

            wifiList.add(newWifiItem)
        }

        wifiList.sortByDescending { it.level }
        adapter.notifyDataSetChanged()
    }

    fun startScanning() {
        activity!!.registerReceiver(broadcastReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager.startScan()
    }

    fun stopScanning() {
        activity!!.unregisterReceiver(broadcastReceiver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wifiManager = activity!!.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        Protocol.cDeviceProt = deviceProtocol

        if(!wifiManager!!.isWifiEnabled)
        {
            wifiManager!!.isWifiEnabled = true
            Toast.makeText(activity, "Wifi Enabled", Toast.LENGTH_SHORT).show()
        }

        return inflater.inflate(R.layout.fragment_landing, container, false)
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

            adapter = WifiAdapter(activity, wifiList)
            lst_wifi.adapter = adapter
        }

        testbt2.setOnClickListener{
            startScanning()
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
    }
}

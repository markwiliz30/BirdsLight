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
import com.example.blapp.common.SharedWifiDetails
import com.example.blapp.model.WifiItem
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_landing.*
import android.net.wifi.WifiConfiguration


/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class LandingFragment : Fragment() {
    lateinit var navController: NavController
    private val deviceProtocol = DeviceProtocol()

    lateinit var layoutManager: LinearLayoutManager
    //var wifiList = ArrayList<WifiItem>()

    var resultList = ArrayList<ScanResult>()
    lateinit var wifiManager: WifiManager
    lateinit var adapter: WifiAdapter
    private lateinit var dialog: AlertDialog

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            resultList = wifiManager.scanResults as ArrayList<ScanResult>
//            val handler = Handler()
//            handler.postDelayed({
//                stopScanning()
//            }, 5000)
            dialog.dismiss()

            displayScannedWifi()
            if(resultList.count() == 0)
            {
                Toast.makeText(activity, "No device(s) found" , Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(activity, resultList.count().toString() + "device(s) found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun delayedDialogDismiss(){
        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()
        }, 10000)
    }

    private fun displayScannedWifi() {
        SharedWifiDetails.wifiList.clear()
        var sameSSIDCount = 1
        for(item in resultList)
        {
            var newWifiItem = WifiItem()
            val found = SharedWifiDetails.wifiList.filter { it.name == item.SSID }
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

            SharedWifiDetails.wifiList.add(newWifiItem)
        }

        SharedWifiDetails.wifiList.sortByDescending { it.level }
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
        dialog = SpotsDialog(activity, R.style.Custom)
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

        adapter = WifiAdapter(activity, SharedWifiDetails.wifiList)
        SharedWifiDetails.sharedWifiAdapter = adapter
        lst_wifi.adapter = adapter

        startScanning()
        dialog.show()
        delayedDialogDismiss()

        btn_scan.setOnClickListener{
            startScanning()
            dialog.show()
            delayedDialogDismiss()

//            var postDelayedSendToModule = Handler()
//            var sendToModule = Runnable {
//                dialog.dismiss()
//            }
//            postDelayedSendToModule.postDelayed(sendToModule, 10000)
        }
    }

    fun connectWiFi(SSID: String, password: String, Security: String) {
        try {

            val conf = WifiConfiguration()
            conf.SSID =
                "\"" + SSID + "\""   // Please note the quotes. String should contain ssid in quotes
            conf.status = WifiConfiguration.Status.ENABLED
            conf.priority = 40
            // Check if security type is WEP
            if (Security.toUpperCase().contains("WEP")) {
                conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
                conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN)
                conf.allowedProtocols.set(WifiConfiguration.Protocol.WPA)
                conf.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)
                conf.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED)
                conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
                conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104)

                if (password.matches("^[0-9a-fA-F]+$".toRegex())) {
                    conf.wepKeys[0] = password
                } else {
                    conf.wepKeys[0] = "\"" + password + "\""
                }

                conf.wepTxKeyIndex = 0
                // Check if security type is WPA
            } else if (Security.toUpperCase().contains("WPA")) {

                conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN)
                conf.allowedProtocols.set(WifiConfiguration.Protocol.WPA)
                conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
                conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
                conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)

                conf.preSharedKey = "\"" + password + "\""
                // Check if network is open network
            } else {
                conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
                conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN)
                conf.allowedProtocols.set(WifiConfiguration.Protocol.WPA)
                conf.allowedAuthAlgorithms.clear()
                conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
                conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)
            }
            //Connect to the network
            val wifiManager =
                activity!!.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val networkId = wifiManager.addNetwork(conf)

            val list = wifiManager.configuredNetworks
            for (i in list) {
                if (i.SSID != null && i.SSID == "\"" + SSID + "\"") {

                    val isDisconnected = wifiManager.disconnect()

                    val isEnabled = wifiManager.enableNetwork(i.networkId, true)

                    val isReconnected = wifiManager.reconnect()

                    break
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

//    private fun generateItem() {
//
//        var item1 = WifiItem()
//        item1.name = "waw"
//        item1.status = 0
//        item1.level = 3
//        wifiList.add(item1)
//
//        var item2 = WifiItem()
//        item2.name = "wew"
//        item2.status = 2
//        item2.level = 4
//        wifiList.add(item2)
//    }
}

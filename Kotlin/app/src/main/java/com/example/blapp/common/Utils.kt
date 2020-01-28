package com.example.blapp.common

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Handler

class Utils {

    internal fun Wifi_Scan(
        context: Activity,
        broadcastReceiver: BroadcastReceiver,
        wifiManager: WifiManager
    ) {
        val handler = Handler()
        handler.postDelayed({
            // TODO Auto-generated method stub
            context.registerReceiver(
                broadcastReceiver, IntentFilter(
                    WifiManager.SCAN_RESULTS_AVAILABLE_ACTION
                )
            )
            wifiManager.startScan()
            //wifiLoad.show();
            //Toast.makeText(context, "Scanning...", Toast.LENGTH_SHORT).show();
        }, 20)
    }
}
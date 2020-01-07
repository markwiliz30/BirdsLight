package com.example.blapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.blapp.common.DeviceProtocol
import com.example.blapp.common.Protocol
import kotlinx.android.synthetic.main.fragment_landing.*

/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : Fragment() {

    lateinit var navController: NavController
    val deviceProtocol = DeviceProtocol()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Protocol.cDeviceProt = deviceProtocol
        return inflater.inflate(R.layout.fragment_landing, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        Protocol.cDeviceProt.startChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
        Protocol.cDeviceProt.stopChannel()
    }
}

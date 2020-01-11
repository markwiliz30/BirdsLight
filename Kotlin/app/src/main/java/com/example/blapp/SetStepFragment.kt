package com.example.blapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.FragmentController
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.blapp.common.Protocol
import kotlinx.android.synthetic.main.fragment_program.*
import kotlinx.android.synthetic.main.fragment_set_step.*

/**
 * A simple [Fragment] subclass.
 */
class SetStepFragment : Fragment() {

    lateinit var navController: NavController
    internal var pVal: Int = 0
    internal var tVal:Int = 0
    internal var bVal:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        var movData = ByteArray(3)
        val command: Byte = 0x02
        var data: ByteArray

        edit_pan_sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pVal = progress
                movData = Protocol.cDeviceProt.setData(pVal, tVal, bVal)
                data = byteArrayOf(
                    0x01.toByte(),
                    0x01.toByte(),
                    movData[0],
                    movData[1],
                    movData[2],
                    0x01.toByte()
                )
                Protocol.cDeviceProt.transferDataWithDelay(command, data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        edit_tilt_sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tVal = progress
                movData = Protocol.cDeviceProt.setData(pVal, tVal, bVal)
                data = byteArrayOf(
                    0x01.toByte(),
                    0x01.toByte(),
                    movData[0],
                    movData[1],
                    movData[2],
                    0x01.toByte()
                )
                Protocol.cDeviceProt.transferDataWithDelay(command, data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        edit_blink_sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bVal = progress
                movData = Protocol.cDeviceProt.setData(pVal, tVal, bVal)
                data = byteArrayOf(
                    0x01.toByte(),
                    0x01.toByte(),
                    movData[0],
                    movData[1],
                    movData[2],
                    0x01.toByte()
                )
                Protocol.cDeviceProt.transferDataWithDelay(command, data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }
}

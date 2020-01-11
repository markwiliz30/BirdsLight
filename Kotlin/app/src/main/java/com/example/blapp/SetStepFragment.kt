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
import com.bumptech.glide.load.model.ByteBufferEncoder
import com.example.blapp.common.Protocol
import com.example.blapp.model.PgmItem
import com.example.blapp.model.StepItem
import kotlinx.android.synthetic.main.fragment_program.*
import kotlinx.android.synthetic.main.fragment_set_step.*

/**
 * A simple [Fragment] subclass.
 */
class SetStepFragment : Fragment() {

    lateinit var navController: NavController
    var parentPgmIndex: Int = 0
    internal var pVal: Int = 0
    internal var tVal:Int = 0
    internal var bVal:Int = 0
    lateinit var tempStepList: MutableList<StepItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        parentPgmIndex = arguments!!.getInt("parentPgmIndex")
        return inflater.inflate(R.layout.fragment_set_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val command: Byte = 0x02
        var data: ByteArray

        step_parent_pgm.text = parentPgmIndex.toString()

        edit_pan_sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pVal = progress
                data = byteArrayOf(
                    0x01.toByte(),
                    0x01.toByte(),
                    pVal.toByte(),
                    tVal.toByte(),
                    bVal.toByte(),
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
                data = byteArrayOf(
                    0x01.toByte(),
                    0x01.toByte(),
                    pVal.toByte(),
                    tVal.toByte(),
                    bVal.toByte(),
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
                data = byteArrayOf(
                    0x01.toByte(),
                    0x01.toByte(),
                    pVal.toByte(),
                    tVal.toByte(),
                    bVal.toByte(),
                    0x01.toByte()
                )
                Protocol.cDeviceProt.transferDataWithDelay(command, data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        btn_step_save.setOnClickListener{
            var createdPgm = PgmItem()
            val createdPgmCommand = 0x03
            createdPgm.command = createdPgmCommand as Byte
            createdPgm.pgm = parentPgmIndex as Byte
        }
    }

    fun AddStep(pan: Byte, tilt: Byte, blink: Byte, time: Byte)
    {
        val command = 0x02
        val newStep = StepItem()
        var stepIndex = 0
        newStep.command = command as Byte
        newStep.pgm = parentPgmIndex as Byte

        if(tempStepList == null)
        {
            stepIndex = 1
        }
        else
        {
            stepIndex = tempStepList.count() + 1
        }

        newStep.step = stepIndex as Byte
        newStep.pan = pan
        newStep.tilt = tilt
        newStep.blink = blink
        newStep.time = time

        tempStepList.add(newStep)
    }
}

package com.example.blapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import androidx.fragment.app.FragmentController
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.load.model.ByteBufferEncoder
import com.example.blapp.collection.PgmCollection
import com.example.blapp.collection.StepCollection
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
    var stepIndex: Int = 0
    internal var pVal: Int = 0
    internal var tVal:Int = 0
    internal var bVal:Int = 0
    internal var tmVal: Int = 0
    var tempStepList: MutableList<StepItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        parentPgmIndex = arguments!!.getInt("parentPgmIndex")
        stepIndex = 1
        return inflater.inflate(R.layout.fragment_set_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val command: Byte = 0x02
        var data: ByteArray

        step_parent_pgm.text = parentPgmIndex.toString()
        txt_step_time.setText(tmVal.toString())
        txt_step_num.setText(stepIndex.toString())

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

        btn_inc_time.setOnClickListener{
            if(tmVal < 100)
            {
                tmVal++
                txt_step_time.setText(tmVal.toString())
            }
        }

        btn_dec_time.setOnClickListener{
            if(tmVal > 0)
            {
                tmVal--
                txt_step_time.setText(tmVal.toString())
            }
        }

        txt_step_time.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(txt_step_time.text.isEmpty())
                {
                   txt_step_time.setText("0")
                    tmVal = 0
                }
                else if(txt_step_time.text.toString().toInt() > 99)
                {
                    txt_step_time.setText("99")
                    tmVal = 99
                }
                else
                {
                    tmVal = txt_step_time.text.toString().toInt()
                }
            }
        })

        btn_inc_step.setOnClickListener{
            stepIndex++
            if((stepIndex-2) < tempStepList.count())
            {
                AddStep(stepIndex -1)
                SetCurrentStepValues(stepIndex)
                txt_step_num.setText(stepIndex.toString())
            }
            else
            {
                stepIndex--
            }
        }

        btn_dec_step.setOnClickListener{
            stepIndex--
            if(stepIndex == 0){
                stepIndex = 1
            }
            else
            {
                AddStep(stepIndex + 1)
                SetCurrentStepValues(stepIndex)
                txt_step_num.setText(stepIndex.toString())
            }
        }

        btn_add_step.setOnClickListener{
            AddStep(stepIndex)
            ResetCurrentStep()
            stepIndex = tempStepList.count() + 1
            txt_step_num.setText(stepIndex.toString())
        }

        btn_step_save.setOnClickListener{
            var createdPgm = PgmItem()
            val createdPgmCommand = 0x03
            createdPgm.command = createdPgmCommand.toByte()
            createdPgm.pgm = parentPgmIndex.toByte()
            AddStep(stepIndex)
            AddPgmToCollection(createdPgm, tempStepList)
            navController.navigate(R.id.action_setStepFragment_to_programFragment)
        }
    }

    private fun AddPgmToCollection(pgm: PgmItem, stepList: List<StepItem>)
    {
        for(item in stepList)
        {
            StepCollection.stepCollection!!.add(item)
        }

        PgmCollection.pgmCollection!!.add(pgm)
    }

    private fun SetCurrentStepValues(index: Int)
    {
        val newCurrentitem = tempStepList.find { it.step == index.toByte() }
        tempStepList.remove(newCurrentitem)
        pVal = newCurrentitem!!.pan!!.toUByte().toInt()
        tVal = newCurrentitem!!.tilt!!.toUByte().toInt()
        bVal = newCurrentitem!!.blink!!.toUByte().toInt()
        tmVal = newCurrentitem!!.time!!.toUByte().toInt()

        edit_pan_sb.progress = pVal
        edit_tilt_sb.progress = tVal
        edit_blink_sb.progress = bVal
        txt_step_time.setText(tmVal.toString())
    }

    private fun ResetCurrentStep()
    {
        pVal = 0
        tVal = 0
        bVal = 0
        tmVal = 0

        edit_pan_sb.progress = pVal
        edit_tilt_sb.progress = tVal
        edit_blink_sb.progress = bVal
        txt_step_time.setText(tmVal.toString())
    }

    private fun AddStep(index: Int)
    {
        val newItem = StepItem()
        newItem.command = 0x02
        newItem.pgm = parentPgmIndex.toByte()
        newItem.step = index.toByte()
        newItem.pan = pVal.toByte()
        newItem.tilt = tVal.toByte()
        newItem.blink = bVal.toByte()
        newItem.time = tmVal.toByte()
        tempStepList.add(newItem)
    }
}

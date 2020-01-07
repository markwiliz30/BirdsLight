package com.example.blapp

import android.R.attr.*
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_landing.*
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.etebarian.meowbottomnavigation.MeowBottomNavigationCell
import com.CurrentId.extensions.CurrentID
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import kotlin.math.log
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.view.isVisible
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


class Landing : AppCompatActivity() {

    companion object {
        private const val ID_HOME = 1
        private const val ID_TESTFRAGMENT = 2
        private const val ID_PROGRAMFRAGMENT = 3
        private const val ID_SCHEDULEFRAGMENT = 4
        private const val ID_SETTINGSFRAGMENT = 5
        private const val ID_STEPFRAGMENT = 6
        private const val ID_SEQUENCEFRAGMENT = 7
        private const val ID_DAYPICKERFRAGMENT = 8

        private var aax = true
    }

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_TESTFRAGMENT,
                R.drawable.ic_home_black_24dp
            )
        )
        bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_PROGRAMFRAGMENT,
                R.drawable.ic_home_black_24dp
            )
        )
        bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_SCHEDULEFRAGMENT,
                R.drawable.ic_home_black_24dp
            )
        )
        bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_SETTINGSFRAGMENT,
                R.drawable.ic_home_black_24dp
            )
        )

        //bottomNavigation.setCount(ID_NOTIFICATION, "69")
        bottomNavigation.show(ID_HOME)


        bottomNavigation.setOnClickMenuListener {

            when (it.id) {
                 ID_HOME -> if (CurrentID.getID() == ID_TESTFRAGMENT) {
                    navController.navigate(R.id.action_testFragment_to_landingFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_PROGRAMFRAGMENT) {
                    navController.navigate(R.id.action_programFragment_to_landingFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SCHEDULEFRAGMENT) {
                    navController.navigate(R.id.action_scheduleFragment_to_landingFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SETTINGSFRAGMENT) {
                    navController.navigate(R.id.action_settings_to_landingFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_STEPFRAGMENT) {
                     bottomNavigation.isVisible =false
                    ShowSaveAlert(clicked = it.id , current = CurrentID.getID())
                } else if (CurrentID.getID() == ID_SEQUENCEFRAGMENT) {
                     bottomNavigation.isVisible =false
                     ShowSaveAlert(clicked = it.id , current = CurrentID.getID())
                } else if (CurrentID.getID() == ID_DAYPICKERFRAGMENT) {
                     bottomNavigation.isVisible =false
                     ShowSaveAlert(clicked = it.id , current = CurrentID.getID())
                }

                ID_TESTFRAGMENT -> if (CurrentID.getID() == ID_HOME) {
                    navController.navigate(R.id.action_landingFragment_to_testFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_PROGRAMFRAGMENT) {
                    navController.navigate(R.id.action_programFragment_to_testFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SCHEDULEFRAGMENT) {
                    navController.navigate(R.id.action_scheduleFragment_to_testFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SETTINGSFRAGMENT) {
                    navController.navigate(R.id.action_settings_to_testFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_STEPFRAGMENT) {
                    bottomNavigation.isVisible =false
                    ShowSaveAlert(clicked = it.id , current = CurrentID.getID())
                } else if (CurrentID.getID() == ID_SEQUENCEFRAGMENT) {
                    bottomNavigation.isVisible =false
                    ShowSaveAlert(clicked = it.id , current = CurrentID.getID())

                } else if (CurrentID.getID() == ID_DAYPICKERFRAGMENT) {
                    bottomNavigation.isVisible =false
                    ShowSaveAlert(clicked = it.id , current = CurrentID.getID())
                    navController.navigate(R.id.action_dayPicker_to_testFragment)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                }

                ID_PROGRAMFRAGMENT -> if (CurrentID.getID() == ID_HOME) {
                    navController.navigate(R.id.action_landingFragment_to_programFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_TESTFRAGMENT) {
                    navController.navigate(R.id.action_testFragment_to_programFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SCHEDULEFRAGMENT) {
                    navController.navigate(R.id.action_scheduleFragment_to_programFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SETTINGSFRAGMENT) {
                    navController.navigate(R.id.action_settings_to_programFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SEQUENCEFRAGMENT) {
                    navController.navigate(R.id.action_sequence_to_programFragment)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                } else if (CurrentID.getID() == ID_DAYPICKERFRAGMENT) {
                    navController.navigate(R.id.action_dayPicker_to_programFragment)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                }

                ID_SCHEDULEFRAGMENT -> if (CurrentID.getID() == ID_HOME) {
                    navController.navigate(R.id.action_landingFragment_to_scheduleFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_TESTFRAGMENT) {
                    navController.navigate(R.id.action_testFragment_to_scheduleFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_PROGRAMFRAGMENT) {
                    navController.navigate(R.id.action_programFragment_to_scheduleFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SETTINGSFRAGMENT) {
                    navController.navigate(R.id.action_settings_to_scheduleFragment)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_STEPFRAGMENT) {
                    navController.navigate(R.id.action_setStepFragment_to_scheduleFragment)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                }

                ID_SETTINGSFRAGMENT -> if (CurrentID.getID() == ID_HOME) {
                    navController.navigate(R.id.action_landingFragment_to_settings)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_TESTFRAGMENT) {
                    navController.navigate(R.id.action_testFragment_to_settings)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_PROGRAMFRAGMENT) {
                    navController.navigate(R.id.action_programFragment_to_settings)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_SCHEDULEFRAGMENT) {
                    navController.navigate(R.id.action_scheduleFragment_to_settings)
                    CurrentID.UpdateID(num = it.id)
                } else if (CurrentID.getID() == ID_STEPFRAGMENT) {
                    navController.navigate(R.id.action_setStepFragment_to_settings)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                } else if (CurrentID.getID() == ID_SEQUENCEFRAGMENT) {
                    navController.navigate(R.id.action_sequence_to_settings)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                } else if (CurrentID.getID() == ID_DAYPICKERFRAGMENT) {
                    navController.navigate(R.id.action_dayPicker_to_settings)
                    CurrentID.UpdateID(num = it.id)
                    CurrentID.Updatebool(x = false)
                }

                else -> navController.navigate(R.id.action_landingFragment_to_testFragment)
            }
//          }  Toast.makeText(this, "$name is clicked", Toast.LENGTH_SHORT).show()
         }
    }

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (CurrentID.getStatus()) {
            if(CurrentID.getID() == ID_STEPFRAGMENT){
                navController.navigate(R.id.action_setStepFragment_to_programFragment)
                CurrentID.Updatebool(x = false)
                CurrentID.UpdateID(num = ID_PROGRAMFRAGMENT)
            }
            else if(CurrentID.getID() == ID_SEQUENCEFRAGMENT){
                navController.navigate(R.id.action_sequence_to_scheduleFragment)
                CurrentID.Updatebool(x = false)
                CurrentID.UpdateID(num = ID_SCHEDULEFRAGMENT)
            }
            else if(CurrentID.getID() == ID_DAYPICKERFRAGMENT){
                navController.navigate(R.id.action_dayPicker_to_sequence)
                CurrentID.UpdateID(num = ID_SEQUENCEFRAGMENT)
            }
        } else {
            if (doubleBackToExitPressedOnce) {
                CurrentID.UpdateID(num = ID_HOME)
                finish()
            }else{
                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        }
    }

    fun ShowSaveAlert(clicked: Int , current: Int){

        val mAlertDialog = AlertDialog.Builder(this)
        mAlertDialog.setIcon(R.mipmap.ic_launcher_round) //set alertdialog icon
        mAlertDialog.setTitle("Title!") //set alertdialog title
        mAlertDialog.setMessage("Your message here") //set alertdialog message
        mAlertDialog.setPositiveButton("Yes") { dialog, id ->
            if(clicked == ID_HOME && current == ID_STEPFRAGMENT){
                bottomNavigation.isVisible = true
                bottomNavigation.show(clicked)
                navController.navigate(R.id.action_setStepFragment_to_landingFragment)
                CurrentID.UpdateID(num = clicked)
                CurrentID.Updatebool(x = false)
            }else if(clicked == ID_HOME && current == ID_SEQUENCEFRAGMENT){
                bottomNavigation.isVisible =true
                bottomNavigation.show(clicked)
                navController.navigate(R.id.action_sequence_to_landingFragment)
                CurrentID.UpdateID(num = clicked)
                CurrentID.Updatebool(x = false)
            }else if(clicked == ID_HOME && current == ID_DAYPICKERFRAGMENT){
                bottomNavigation.isVisible =true
                bottomNavigation.show(clicked)
                navController.navigate(R.id.action_dayPicker_to_landingFragment)
                CurrentID.UpdateID(num = clicked)
                CurrentID.Updatebool(x = false)
            }else if(clicked == ID_TESTFRAGMENT && current == ID_STEPFRAGMENT){
                navController.navigate(R.id.action_setStepFragment_to_testFragment)
                CurrentID.UpdateID(num = clicked)
                CurrentID.Updatebool(x = false)
            }else if(clicked == ID_TESTFRAGMENT && current == ID_SEQUENCEFRAGMENT){
                navController.navigate(R.id.action_sequence_to_testFragment)
                CurrentID.UpdateID(num = clicked)
                CurrentID.Updatebool(x = false)
            }

            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.setNegativeButton("No") { dialog, id ->
            bottomNavigation.isVisible = true
            if(current == ID_STEPFRAGMENT){
                   bottomNavigation.show(ID_PROGRAMFRAGMENT)
            }
            else if(current == ID_SEQUENCEFRAGMENT || current == ID_DAYPICKERFRAGMENT){
                bottomNavigation.show(ID_SCHEDULEFRAGMENT)
            }

            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.show()
    }
}
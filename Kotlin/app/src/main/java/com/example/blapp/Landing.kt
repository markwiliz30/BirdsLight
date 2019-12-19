package com.example.blapp

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_landing.*
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class Landing : AppCompatActivity() {

    companion object {
        private const val ID_HOME = 1
        private const val ID_TESTFRAGMENT = 2
        private const val ID_PROGRAMFRAGMENT = 3
        private const val ID_SCHEDULEFRAGMENT = 4
        private const val ID_SETTINGSFRAGMENT = 5

    }
        var currentSelectedId: Int = 1
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_TESTFRAGMENT, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_PROGRAMFRAGMENT, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_SCHEDULEFRAGMENT, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_SETTINGSFRAGMENT, R.drawable.ic_home_black_24dp))

        //bottomNavigation.setCount(ID_NOTIFICATION, "69")

        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                ID_HOME -> if(currentSelectedId == 2){
                    navController.navigate(R.id.action_testFragment_to_landingFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 3){
                    navController.navigate(R.id.action_programFragment_to_landingFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 4){
                    navController.navigate(R.id.action_scheduleFragment_to_landingFragment)
                    currentSelectedId = it.id
                }
                else if(currentSelectedId == 5){
                    navController.navigate(R.id.action_settings_to_landingFragment)
                    currentSelectedId = it.id
                }

                ID_TESTFRAGMENT ->if(currentSelectedId == 1){
                    navController.navigate(R.id.action_landingFragment_to_testFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId== 3){
                    navController.navigate(R.id.action_programFragment_to_testFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 4){
                    navController.navigate(R.id.action_scheduleFragment_to_testFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 5){
                    navController.navigate(R.id.action_settings_to_testFragment)
                    currentSelectedId = it.id
                }
                ID_PROGRAMFRAGMENT -> if(currentSelectedId == 1){
                    navController.navigate(R.id.action_landingFragment_to_programFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 2){
                    navController.navigate(R.id.action_testFragment_to_programFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 4){
                    navController.navigate(R.id.action_scheduleFragment_to_programFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 5){
                    navController.navigate(R.id.action_settings_to_programFragment)
                    currentSelectedId = it.id
                }
                ID_SCHEDULEFRAGMENT -> if(currentSelectedId == 1){
                    navController.navigate(R.id.action_landingFragment_to_scheduleFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 2){
                    navController.navigate(R.id.action_testFragment_to_scheduleFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 3){
                    navController.navigate(R.id.action_programFragment_to_scheduleFragment)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 5){
                    navController.navigate(R.id.action_settings_to_scheduleFragment)
                    currentSelectedId = it.id
                }
                ID_SETTINGSFRAGMENT -> if(currentSelectedId == 1){
                    navController.navigate(R.id.action_landingFragment_to_settings)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 2){
                    navController.navigate(R.id.action_testFragment_to_settings)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 3){
                    navController.navigate(R.id.action_programFragment_to_settings)
                    currentSelectedId = it.id
                }else if(currentSelectedId == 4){
                    navController.navigate(R.id.action_scheduleFragment_to_settings)
                    currentSelectedId = it.id
                }
                else -> navController.navigate(R.id.action_landingFragment_to_testFragment)
            }
//            Toast.makeText(this, "$name is clicked", Toast.LENGTH_SHORT).show()
        }
    }

}

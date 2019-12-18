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
        private const val ID_EXPLORE = 2
        private const val ID_MESSAGE = 3
        private const val ID_NOTIFICATION = 4
        private const val ID_ACCOUNT = 5
    }

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_home_black_24dp))

        bottomNavigation.setCount(ID_NOTIFICATION, "69")

        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                ID_HOME -> navController.navigate(R.id.action_landingFragment_to_testFragment2)
                ID_EXPLORE -> navController.navigate(R.id.action_landingFragment_to_testFragment2)
                ID_MESSAGE -> navController.navigate(R.id.action_landingFragment_to_testFragment2)
                ID_NOTIFICATION -> navController.navigate(R.id.action_landingFragment_to_testFragment2)
                ID_ACCOUNT -> navController.navigate(R.id.action_landingFragment_to_testFragment2)
                else -> navController.navigate(R.id.action_landingFragment_to_testFragment2)
            }
//            Toast.makeText(this, "$name is clicked", Toast.LENGTH_SHORT).show()
        }
    }

}

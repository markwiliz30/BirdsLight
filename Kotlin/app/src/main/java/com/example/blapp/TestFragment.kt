package com.example.blapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        tglPgm1.setOnClickListener{
            Toast.makeText(context, "Testing Program 1 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm2.setOnClickListener{
            Toast.makeText(context, "Testing Program 2 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm3.setOnClickListener{
            Toast.makeText(context, "Testing Program 3 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm4.setOnClickListener{
            Toast.makeText(context, "Testing Program 4 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm5.setOnClickListener{
            Toast.makeText(context, "Testing Program 5 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm6.setOnClickListener{
            Toast.makeText(context, "Testing Program 6 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm7.setOnClickListener{
            Toast.makeText(context, "Testing Program 7 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm8.setOnClickListener{
            Toast.makeText(context, "Testing Program 8 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm9.setOnClickListener{
            Toast.makeText(context, "Testing Program 9 Running", Toast.LENGTH_SHORT).show()
        }

        tglPgm10.setOnClickListener{
            Toast.makeText(context, "Testing Program 10 Running", Toast.LENGTH_SHORT).show()
        }

        btnReset.setOnClickListener{
            Toast.makeText(context, "Resetting the device", Toast.LENGTH_SHORT).show()
        }
    }
}

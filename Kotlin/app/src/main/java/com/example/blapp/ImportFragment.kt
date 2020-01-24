package com.example.blapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blapp.adapter.ImportAdapter
import com.example.blapp.adapter.PgmAdapter
import com.example.blapp.databasehelper.DBmanager
import com.example.blapp.databasehelper.pgmmanager
import kotlinx.android.synthetic.main.fragment_day_picker.*
import kotlinx.android.synthetic.main.fragment_import.*


class ImportFragment : Fragment() {

    lateinit var layoutManager: LinearLayoutManager

    lateinit var adapter : ImportAdapter
    internal lateinit var dbPgm:DBmanager

    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_import, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dbPgm = DBmanager(activity!!)

        recycler_import.setHasFixedSize(true)
        recycler_import.setItemViewCacheSize(25)
        layoutManager = LinearLayoutManager(activity)
        recycler_import.layoutManager = layoutManager
        ShowImports()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }

    private fun ShowImports(){
        adapter = ImportAdapter(activity, dbPgm.allpgm)
        recycler_import.adapter = adapter
    }
}

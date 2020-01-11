package com.example.blapp


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.CurrentId.extensions.CurrentID
import com.example.blapp.adapter.PgmAdapter
import com.example.blapp.databasehelper.stepmanager
import com.example.blapp.helper.MyButton
import com.example.blapp.helper.MySwipeHelper
import com.example.blapp.listener.MyButtonClickListener
import com.example.blapp.model.PgmItem
import com.example.blapp.model.StepItem
import kotlinx.android.synthetic.main.fragment_program.*

class ProgramFragment : Fragment(){

    lateinit var navController: NavController
    lateinit var layoutManager: LinearLayoutManager

    lateinit var adapter: PgmAdapter

    internal lateinit var db_step:stepmanager
    internal var lststep: List<StepItem> = ArrayList<StepItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_program, container, false)
        db_step = stepmanager(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_pgm.setHasFixedSize(true)
        recycler_pgm.setItemViewCacheSize(25)
        layoutManager = LinearLayoutManager(activity)
        recycler_pgm.layoutManager = layoutManager

        //Add Swipe
        val swipe = object: MySwipeHelper(activity, recycler_pgm, 200)
        {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(activity,
                        "Delete",
                        30,
                        R.drawable.ic_delete_dark_blue_24dp,
                        Color.parseColor("#14BED1"),
                        object : MyButtonClickListener{
                            override fun onClick(pos: Int) {
                                Toast.makeText(activity, "DELETE ID"+pos, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                )

                buffer.add(
                    MyButton(activity,
                        "Update",
                        30,
                        R.drawable.ic_edit_dark_blue_24dp,
                        Color.parseColor("#14BED1"),
                        object : MyButtonClickListener{
                            override fun onClick(pos: Int) {
                                Toast.makeText(activity, "UPDATE ID"+pos, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                )

                buffer.add(
                    MyButton(activity,
                        "Save",
                        30,
                        R.drawable.ic_save_dark_blue_24dp,
                        Color.parseColor("#14BED1"),
                        object : MyButtonClickListener{
                            override fun onClick(pos: Int) {
                                val step = StepItem(
                                    
                                )


                            }
                        }
                    )
                )
            }
        }

        generateItem()
    }

    private fun generateItem() {
        val itemList = ArrayList<PgmItem>()
        var i =0
        while (i<50)
        {
            var pgmNum = ++i
          itemList.add(PgmItem())
            i++
        }
        adapter = PgmAdapter(activity, itemList)
        recycler_pgm.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnNewProgram.setOnClickListener{
            navController.navigate(R.id.action_programFragment_to_setStepFragment)
            CurrentID.UpdateID(num = 6)
            CurrentID.Updatebool(x = true)
        }
    }
}

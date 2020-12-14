package com.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mynotes.ui.main.MainViewModel
import com.mynotes.ui.main.Notes
import com.mynotes.ui.main.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.main_fragment.*


class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerAdapter: RecyclerAdapter
    private lateinit var viewModel: MainViewModel
    var myList: ArrayList<Notes> = ArrayList()
    private var strTitle: String? = null
    private var strContent: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)



        if (intent != null) {
            strTitle = intent.getStringExtra("title")
            if (strTitle != null) {
                myList = intent.getParcelableArrayListExtra<Notes>("myList") as ArrayList<Notes>
            }
            strContent = intent.getStringExtra("content")

        }

        viewModel.getNotes(myList).observe(this, Observer {
            mRecyclerAdapter =
                RecyclerAdapter(it) { viewModelDevice, position, isDeleteButtonClick ->
                    val index = viewModel.arrayListNotes.indexOf(viewModelDevice)
                    val intent = Intent(this, EditNotes::class.java)
                    intent.putExtra("on_click", "edit")
                    intent.putExtra("title", viewModel.arrayListNotes[index].notesData.Title)
                    intent.putExtra(
                        "content",
                        viewModel.arrayListNotes[index].notesData.description
                    )
                    intent.putExtra("myList", myList)
                    intent.putExtra("index", index)
                    startActivity(intent)

                }
            rv_notes.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            rv_notes.adapter = mRecyclerAdapter
            mRecyclerAdapter.notifyDataSetChanged()
            //   rv_notes.addItemDecoration(SimpleDividerItemDecoration(this))
        })

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                myList.removeAt(pos)
                refreshView()
                rv_notes.adapter = mRecyclerAdapter
                mRecyclerAdapter.notifyItemRemoved(pos)


            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(rv_notes)


        btn_add_notes.setOnClickListener {
            val intent = Intent(this, EditNotes::class.java)
            intent.putExtra("on_click", "add")
            intent.putExtra("myList", myList)
            startActivity(intent)

        }
    }

   private fun refreshView(){
       Toast.makeText(this, "Note Deleted Successfully", Toast.LENGTH_SHORT).show()
       viewModel.refreshNotes(myList).observe(this, Observer {
           mRecyclerAdapter =
               RecyclerAdapter(it) { viewModelDevice, position, isDeleteButtonClick ->
                   val index = viewModel.arrayListNotes.indexOf(viewModelDevice)
                   val intent = Intent(this, EditNotes::class.java)
                   intent.putExtra("on_click", "edit")
                   intent.putExtra("title", viewModel.arrayListNotes[index].notesData.Title)
                   intent.putExtra("content", viewModel.arrayListNotes[index].notesData.description)
                   intent.putExtra("myList", myList)
                   intent.putExtra("index", index)
                   startActivity(intent)

               }
           rv_notes.layoutManager = LinearLayoutManager(
               applicationContext,
               LinearLayoutManager.VERTICAL,
               false
           )
           rv_notes.adapter = mRecyclerAdapter
           mRecyclerAdapter.notifyDataSetChanged()

       })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
        finish()
    }

}


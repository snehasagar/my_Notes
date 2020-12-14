package com.mynotes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mynotes.ui.main.MainViewModel
import com.mynotes.ui.main.Notes
import kotlinx.android.synthetic.main.edit_note_activity.*


class EditNotes : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var strTitle: String? = null
    private var strContent: String? = null
    private var index: Int? = 0
    private var btnEditEnable: Boolean? = false
    private var myList: ArrayList<Notes> = ArrayList()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note_activity)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        btn_back.setOnClickListener {
            finish()
        }


        if (intent != null) {
            strTitle = intent.getStringExtra("title")
            strContent = intent.getStringExtra("content")
            index = intent.getIntExtra("index", 0)
            val isClicked = intent.getStringExtra("on_click")
            myList = intent.getParcelableArrayListExtra<Notes>("myList") as ArrayList<Notes>
            if (isClicked == "edit") {
                btnEditEnable = true
                btn_add_edit_notes.text = "Edit Note"

            } else {
                btnEditEnable = false
            }
            et_title.setText(strTitle)
            et_content.setText(strContent)

        }

        btn_add_edit_notes.setOnClickListener {
            strTitle = et_title.text.toString()
            if (strTitle.isNullOrEmpty()) {
                strTitle = "New Note"
            }
            if (btnEditEnable == true) {
                myList.set(index!!, (Notes(strTitle, et_content.text.toString())))
            } else {
                myList.addAll(listOf(Notes(strTitle, et_content.text.toString())))
            }

            finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("title", strTitle)
            intent.putExtra("content", et_content.text.toString())
            intent.putExtra("myList", myList)
            startActivity(intent)


        }

    }

}
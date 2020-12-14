package com.mynotes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mynotes.databinding.ItemNoteListBinding
import com.mynotes.ui.main.MainViewModel
import java.util.*


class RecyclerAdapter(
    private val arrayList: ArrayList<MainViewModel>,
    private val listener: (MainViewModel, Int,Boolean) -> Unit
)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNoteListBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_note_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(arrayList[position],position ,listener)
    }

    class ViewHolder(private val binding: ItemNoteListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(viewModel: MainViewModel, position: Int,listener: (MainViewModel,Int,Boolean) -> Unit) {
            this.binding.viewModel = viewModel
            binding.executePendingBindings()
            binding.tvTitles.text = viewModel.notesData.Title
           var isDeleteButtonClick = false
            binding.imageView.setOnClickListener { listener(viewModel,position,isDeleteButtonClick) }
           /* binding.buttonDelete.setOnClickListener {
                viewModel.arrayListNotes.removeAt(position)
            }
*/

          /*  binding.buttonDelete.setOnClickListener {
                viewModel.isDeleteButtonClick = true
                isDeleteButtonClick = true
                listener(viewModel,position,isDeleteButtonClick)
            }*/
        }
    }
}


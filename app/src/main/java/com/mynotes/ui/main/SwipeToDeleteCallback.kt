package com.mynotes.ui.main

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class SwipeToDeleteCallback : ItemTouchHelper.Callback() {

    override fun getMovementFlags(rv_notes: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlag)
    }

    override fun onMove(
        rv_notes: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}
package com.mynotes.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel {

    constructor() : super()

    lateinit var notesData: Notes

    constructor(notesData:Notes ) : super() {
        this.notesData = notesData
    }

    private var notesLiveData = MutableLiveData<ArrayList<MainViewModel>>()
    var arrayListNotes = ArrayList<MainViewModel>()

    fun getNotes(data: MutableList<Notes>): MutableLiveData<ArrayList<MainViewModel>> {
    for (i in 0 until data.size) {
        arrayListNotes.add(MainViewModel(Notes(
            data[i].Title,
            data[i].description)))
    }
        notesLiveData.value = arrayListNotes
        return notesLiveData
    }

    fun refreshNotes(data: MutableList<Notes>): MutableLiveData<ArrayList<MainViewModel>> {
        arrayListNotes.clear()
        for (i in 0 until data.size) {
            arrayListNotes.add(MainViewModel(Notes(
                data[i].Title,
                data[i].description)))
        }
        notesLiveData.value = arrayListNotes
        return notesLiveData
    }

}
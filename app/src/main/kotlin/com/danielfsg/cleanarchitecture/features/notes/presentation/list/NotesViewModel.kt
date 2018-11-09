package com.danielfsg.cleanarchitecture.features.notes.presentation.list

import android.arch.lifecycle.MutableLiveData
import com.danielfsg.cleanarchitecture.core.interactor.UseCase.None
import com.danielfsg.cleanarchitecture.core.platform.BaseViewModel
import com.danielfsg.cleanarchitecture.features.notes.domain.GetNotes
import com.danielfsg.cleanarchitecture.features.notes.domain.Note

class NotesViewModel(private val getNotes: GetNotes) : BaseViewModel() {

    var notes: MutableLiveData<List<Note>> = MutableLiveData()

    fun loadNotes() = getNotes.execute({ it.either(::handleFailure, ::handleNoteList) }, None())

    private fun handleNoteList(notes: List<Note>) {
        this.notes.value = notes
    }
}
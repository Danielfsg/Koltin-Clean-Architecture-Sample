package com.danielfsg.cleanarchitecture.features.notes.presentation.note

import android.arch.lifecycle.MutableLiveData
import com.danielfsg.cleanarchitecture.core.platform.BaseViewModel
import com.danielfsg.cleanarchitecture.features.notes.domain.*

class AddEditNoteViewModel(
    private val getNoteById: GetNoteById,
    private val addNote: AddNote,
    private val updateNote: UpdateNote,
    private val deleteNote: DeleteNote
) : BaseViewModel() {

    var note: MutableLiveData<Note> = MutableLiveData()

    fun getNoteById(noteID: String) =
        getNoteById.execute({ it.either(::handleFailure, ::handleNote) }, GetNoteById.Params(noteID))

    fun addNote(note: Note) = addNote.execute({ it.either(::handleFailure, ::handleNote) }, AddNote.Params(note))

    fun updateNote(noteID: String, note: Note) =
        updateNote.execute({ it.either(::handleFailure, ::handleNote) }, UpdateNote.Params(noteID, note))

    fun deleteNote(noteID: String) =
        deleteNote.execute({ it.either(::handleFailure, ::handleNote) }, DeleteNote.Params(noteID))

    private fun handleNote(note: Note) {
        this.note.value = note
    }
}
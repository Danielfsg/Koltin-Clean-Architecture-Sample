package com.danielfsg.cleanarchitecture.features.notes.domain

import com.danielfsg.cleanarchitecture.core.interactor.UseCase

class GetNotes(private val noteRepository: NoteRepository) : UseCase<List<Note>, UseCase.None>() {
    override suspend fun run(params: None) = noteRepository.getAllNotes()
}

class GetNoteById(private val noteRepository: NoteRepository) : UseCase<Note, GetNoteById.Params>() {
    override suspend fun run(params: Params) = noteRepository.getNoteByID(params.noteID)
    data class Params(val noteID: String)
}

class AddNote(private val noteRepository: NoteRepository) : UseCase<Note, AddNote.Params>() {
    override suspend fun run(params: Params) = noteRepository.addNote(params.note)
    data class Params(val note: Note)
}

class UpdateNote(private val noteRepository: NoteRepository) : UseCase<Note, UpdateNote.Params>() {
    override suspend fun run(params: Params) = noteRepository.updateNote(params.noteID, params.note)
    data class Params(val noteID: String, val note: Note)
}

class DeleteNote(private val noteRepository: NoteRepository) : UseCase<Note, DeleteNote.Params>() {
    override suspend fun run(params: Params) = noteRepository.deleteNote(params.noteID)
    data class Params(val noteID: String)
}

package com.danielfsg.cleanarchitecture.features.notes.data

import retrofit2.Retrofit

class NoteService(retrofit: Retrofit) : NoteApi {

    private val noteApi by lazy { retrofit.create(NoteApi::class.java) }

    override fun getNotes(bearer: String) = noteApi.getNotes(bearer)

    override fun getNoteById(bearer: String, noteID: String) = noteApi.getNoteById(bearer, noteID)

    override fun postNote(bearer: String, note: NoteEntity) = noteApi.postNote(bearer, note)

    override fun putNote(bearer: String, noteID: String, note: NoteEntity) = noteApi.putNote(bearer, noteID, note)

    override fun deleteNote(bearer: String, noteID: String) = noteApi.deleteNote(bearer, noteID)

}
package com.danielfsg.cleanarchitecture.features.notes.presentation.note

import android.os.Bundle
import android.view.View
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.extension.empty
import com.danielfsg.cleanarchitecture.core.extension.failure
import com.danielfsg.cleanarchitecture.core.extension.observe
import com.danielfsg.cleanarchitecture.core.platform.BaseFragment
import com.danielfsg.cleanarchitecture.features.notes.domain.Note
import org.koin.android.ext.android.inject

class AddEditNoteFragment : BaseFragment() {

    companion object {
        private const val PARAM_EDITING = "param_editing"
        private const val PARAM_NOTE = "param_note"
        fun editNote(noteID: String): AddEditNoteFragment {
            val addEditNoteFragment = AddEditNoteFragment()
            val arguments = Bundle()
            arguments.putBoolean(PARAM_EDITING, true)
            arguments.putString(PARAM_NOTE, noteID)

            return addEditNoteFragment
        }
    }

    private var editing = false
    private var noteID = String.empty()
    private val viewModel: AddEditNoteViewModel by inject()

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editing = arguments?.getBoolean(PARAM_EDITING) as Boolean
        noteID = arguments?.getString(PARAM_NOTE) as String

        with(viewModel) {
            observe(note, ::renderNote)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            if (editing) {
                viewModel.getNoteById(noteID)
            }
        }
    }


    private fun renderNote(note: Note?) {

    }

    private fun handleFailure(failure: Failure?) {

    }

}
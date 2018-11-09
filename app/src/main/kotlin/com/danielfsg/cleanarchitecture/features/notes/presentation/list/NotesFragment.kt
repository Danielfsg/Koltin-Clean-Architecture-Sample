package com.danielfsg.cleanarchitecture.features.notes.presentation.list

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import android.view.View
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.extension.failure
import com.danielfsg.cleanarchitecture.core.extension.invisible
import com.danielfsg.cleanarchitecture.core.extension.observe
import com.danielfsg.cleanarchitecture.core.extension.visible
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import com.danielfsg.cleanarchitecture.core.platform.BaseFragment
import com.danielfsg.cleanarchitecture.features.notes.domain.Note
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.android.ext.android.inject

class NotesFragment : BaseFragment() {

    private val navigator: Navigator by inject()
    private val notesAdapter: NotesAdapter by inject()
    private val notesViewModel: NotesViewModel by inject()

    override fun layoutId() = R.layout.fragment_notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        with(notesViewModel) {
            observe(notes, ::renderNoteList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadNoteList()
    }

    private fun initializeView() {
        noteList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        noteList.adapter = notesAdapter
        //notesAdapter.clickListener = { noteView -> navigator.showNote }
    }

    private fun loadNoteList() {
        emptyView.invisible()
        noteList.visible()
        showProgress()
        notesViewModel.loadNotes()
    }

    private fun renderNoteList(notes: List<Note>?) {
        notesAdapter.collection = notes.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> notify(failure.message)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        noteList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadNoteList)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add_new -> {
            // TODO:
            notify("ADD NOTES")
            true
        }
        R.id.action_settings -> {
            // TODO:
            notify("SETTINGS PAGE")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


}
package com.danielfsg.cleanarchitecture.features.notes.presentation.note

import com.danielfsg.cleanarchitecture.core.platform.BaseActivity
import com.danielfsg.cleanarchitecture.core.platform.BaseFragment

class AddEditNoteActivity : BaseActivity() {
    override fun fragment() = AddEditNoteFragment()
}
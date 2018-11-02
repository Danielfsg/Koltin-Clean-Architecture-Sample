package com.danielfsg.cleanarchitecture.core.platform;

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielfsg.cleanarchitecture.R.color
import com.danielfsg.cleanarchitecture.core.extension.appContext
import com.danielfsg.cleanarchitecture.core.extension.viewContainer
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutId(), container, false)

    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }

    internal fun notify(@StringRes message: Int) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notify(message: String) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackbar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(actionText) { _ -> action.invoke() }
        snackbar.setActionTextColor(ContextCompat.getColor(appContext, color.colorTextSecondary))
        snackbar.show()
    }
}

package com.mytaxi.task

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment

open class BaseNetworkFragment : Fragment(), NetworkView {
    private var mSnackBarNoConnection: Snackbar? = null

    override fun showNoConnection(retryAction: Action) {
        mSnackBarNoConnection = Snackbar.make(
            view!!,
            R.string.check_connection, Snackbar.LENGTH_INDEFINITE
        )
        mSnackBarNoConnection!!.setAction(R.string.retry) {
            retryAction.perform()
        }
        mSnackBarNoConnection!!.show()
    }


    override fun hideNoConnection() {
        if (mSnackBarNoConnection != null && mSnackBarNoConnection!!.isShown) {
            mSnackBarNoConnection!!.dismiss()
        }
    }

    override fun showServerError() {
        Snackbar.make(view!!, R.string.server_error, Snackbar.LENGTH_LONG).show()
    }

    override fun showTimeOut() {
        Snackbar.make(view!!, R.string.timeout, Snackbar.LENGTH_LONG)
            .show()
    }
}

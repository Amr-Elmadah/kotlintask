package com.mytaxi.task

interface NetworkView {

    fun showNoConnection(retryAction: Action)

    fun hideNoConnection()

    fun showServerError()

    fun showTimeOut()
}

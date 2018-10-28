package com.amr.kotlinteach.data.callbacks

/**
 * Created by Amr ElMadah on 12/24/17.
 */

interface BaseNetworkCallback {

    fun onResponseError(responseCode: Int)

    fun onNoConnection()

    fun onTimeOut()
}

package com.mytaxi.task.data.callbacks

import com.amr.kotlinteach.data.callbacks.BaseNetworkCallback
import com.mytaxi.task.data.models.Vehicle

interface LoadVehiclesCallback : BaseNetworkCallback {
    fun onVehiclesLoaded(vehicles: List<Vehicle>)
}
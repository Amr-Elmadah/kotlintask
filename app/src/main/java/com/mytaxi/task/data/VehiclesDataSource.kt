package com.mytaxi.task.data

import com.mytaxi.task.data.callbacks.LoadVehiclesCallback

interface VehiclesDataSource {
    fun loadVehicles(loadVehiclesCallback: LoadVehiclesCallback)
}
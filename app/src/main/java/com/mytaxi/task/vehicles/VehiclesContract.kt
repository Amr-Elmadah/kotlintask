package com.mytaxi.task.vehicles

import com.mytaxi.task.BasePresenter
import com.mytaxi.task.BaseView
import com.mytaxi.task.NetworkView
import com.mytaxi.task.data.models.Vehicle


interface VehiclesContract {

    interface View : BaseView<Presenter>, NetworkView {
        val isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun onVehiclesLoaded(vehicles: List<Vehicle>)
    }

    interface Presenter : BasePresenter {
        fun loadVehicles()
    }
}

package com.mytaxi.task.vehicles

import com.mytaxi.task.BasePresenter
import com.mytaxi.task.BaseView
import com.mytaxi.task.NetworkView
import com.mytaxi.task.data.models.Vehicle


interface VehiclesContract {

    interface View : BaseView<Presenter>, NetworkView {
        val isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun showVehicleList(vehicles: List<Vehicle>)
    }

    interface Presenter : BasePresenter {
        fun loadVehicles()

        fun loadVehiclesForTest()

        fun getPresenter(): VehiclesPresenter
    }
}

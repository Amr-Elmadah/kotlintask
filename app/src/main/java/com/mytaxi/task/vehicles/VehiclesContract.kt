package com.mytaxi.task.vehicles

import com.mytaxi.task.BasePresenter
import com.mytaxi.task.BaseView
import com.mytaxi.task.NetworkView


interface VehiclesContract {

    interface View : BaseView<Presenter>, NetworkView {
        val isActive: Boolean
    }

    interface Presenter : BasePresenter {
        fun getVehicles(): ArrayList<String>
    }
}

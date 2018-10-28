package com.mytaxi.task.vehicles.list

import com.mytaxi.task.BasePresenter
import com.mytaxi.task.BaseView
import com.mytaxi.task.NetworkView
import com.mytaxi.task.data.models.Vehicle

interface ArticlesContract {

    interface View : BaseView<Presenter>, NetworkView {

        val isActive: Boolean

        fun showVehicles(vehicles: MutableList<Vehicle>)
    }

    interface Presenter : BasePresenter
}

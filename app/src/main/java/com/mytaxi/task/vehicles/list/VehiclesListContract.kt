package com.mytaxi.task.vehicles.list

import com.mytaxi.task.BasePresenter
import com.mytaxi.task.BaseView
import com.mytaxi.task.NetworkView
import com.mytaxi.task.data.models.Vehicle
import com.mytaxi.task.vehicles.VehiclesPresenter

interface VehiclesListContract {

    interface View : BaseView<Presenter>, NetworkView {

        val isActive: Boolean

        fun observeListPresenter(presenter: VehiclesPresenter)
    }

    interface Presenter : BasePresenter
}

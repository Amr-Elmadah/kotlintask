package com.mytaxi.task.vehicles.map

import com.google.android.gms.maps.model.Marker
import com.mytaxi.task.BasePresenter
import com.mytaxi.task.BaseView
import com.mytaxi.task.NetworkView
import com.mytaxi.task.data.models.Vehicle

interface VehiclesMapsContract {

    interface View : BaseView<Presenter>, NetworkView {

        val isActive: Boolean

        fun showVehicles(vehicles: List<Vehicle>)

        fun selectVehicle(position: Int)

        fun centerlizeMarkers(markers : List<Marker>)
    }

    interface Presenter : BasePresenter
}

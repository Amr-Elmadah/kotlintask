package com.mytaxi.task.vehicles

import com.mytaxi.task.Action
import com.mytaxi.task.data.VehiclesRepository
import com.mytaxi.task.data.callbacks.LoadVehiclesCallback
import com.mytaxi.task.data.models.Vehicle


class VehiclesPresenter(private val vehiclesRepository: VehiclesRepository?, view: VehiclesContract.View) :
    VehiclesContract.Presenter {
    private val mView: VehiclesContract.View = checkNotNull(view)

    init {
        mView.setPresenter(this)
    }

    override fun start() {
        loadVehicles()
    }

    override fun loadVehicles() {
        mView.setLoadingIndicator(true)
        vehiclesRepository?.loadVehicles(object : LoadVehiclesCallback {
            override fun onVehiclesLoaded(vehicles: List<Vehicle>) {
                if (mView.isActive) {
                    mView.onVehiclesLoaded(vehicles)
                    mView.setLoadingIndicator(false)
                }
            }

            override fun onResponseError(responseCode: Int) {
                if (mView.isActive) {
                    mView.showServerError()
                    mView.setLoadingIndicator(false)
                }
            }

            override fun onNoConnection() {
                if (mView.isActive) {
                    mView.showNoConnection(object : Action {
                        override fun perform() {
                            loadVehicles()
                        }
                    })
                    mView.setLoadingIndicator(false)
                }
            }

            override fun onTimeOut() {
                if (mView.isActive) {
                    mView.setLoadingIndicator(false)
                    mView.showTimeOut()
                }
            }
        })
    }
}

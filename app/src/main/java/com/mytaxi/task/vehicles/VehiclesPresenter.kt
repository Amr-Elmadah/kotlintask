package com.mytaxi.task.vehicles


class VehiclesPresenter(view: VehiclesContract.View) :
    VehiclesContract.Presenter {
    private val mView: VehiclesContract.View = checkNotNull(view)


    init {
        mView.setPresenter(this)
    }

    override fun getVehicles(): ArrayList<String> {
        return ArrayList()
    }

    override fun start() {
    }
}

package com.mytaxi.task.vehicles.map


class VehiclesMapsPresenter(view: VehiclesMapsContract.View) :
    VehiclesMapsContract.Presenter {
    private val mView: VehiclesMapsContract.View = checkNotNull(view)

    init {
        mView.setPresenter(this)
    }

    override fun start() {
    }
}

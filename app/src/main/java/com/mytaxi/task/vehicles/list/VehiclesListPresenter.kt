package com.mytaxi.task.vehicles.list


class VehiclesListPresenter(view: VehiclesListContract.View) :
    VehiclesListContract.Presenter {
    private val mView: VehiclesListContract.View = checkNotNull(view)

    init {
        mView.setPresenter(this)
    }

    override fun start() {
    }
}

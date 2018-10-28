package com.mytaxi.task.vehicles

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mytaxi.task.BaseNetworkFragment
import com.mytaxi.task.R
import com.mytaxi.task.data.VehiclesRepository
import com.mytaxi.task.data.models.Vehicle
import kotlinx.android.synthetic.main.fragment_vehicles.*
import kotlinx.android.synthetic.main.fragment_vehicles.view.*


class VehiclesFragment : BaseNetworkFragment(), VehiclesContract.View {
    private var mPresenter: VehiclesContract.Presenter? = null
    private var mTabsPagerAdapter: TabsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vehicles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupSegmentedButton()

        if (mPresenter == null) {
            mPresenter = VehiclesPresenter(VehiclesRepository(), this)
        }

        if (savedInstanceState == null) {
            mPresenter!!.start()
        }
    }

    private fun setupViewPager() {
        mTabsPagerAdapter = TabsPagerAdapter(activity!!.supportFragmentManager)
        srlVehicles.viewpager.adapter = mTabsPagerAdapter
    }

    private fun setupSegmentedButton() {
        segmented.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rb_list)
                srlVehicles.viewpager.currentItem = 0
            else if (checkedId == R.id.rb_map) {
                srlVehicles.viewpager.currentItem = 1
            }
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        srlVehicles.post {
            srlVehicles!!.isRefreshing = active
            // Set refreshing to false on the empty view's SwipeRefreshLayout if it's active
            if (srlVehicles!!.isRefreshing && !active) {
                srlVehicles!!.isRefreshing = false
            }
        }
    }

    private inner class TabsPagerAdapter internal constructor(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return Fragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }

    override val isActive: Boolean
        get() = isAdded

    override fun onVehiclesLoaded(vehicles: List<Vehicle>) {

    }

    override fun setPresenter(presenter: VehiclesContract.Presenter) {
        mPresenter = presenter
    }
}
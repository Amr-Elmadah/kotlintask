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
import com.mytaxi.task.vehicles.list.VehiclesListFragment
import com.mytaxi.task.vehicles.map.VehiclesMapsFragment
import kotlinx.android.synthetic.main.fragment_vehicles.*
import kotlinx.android.synthetic.main.fragment_vehicles.view.*


class VehiclesFragment : BaseNetworkFragment(), VehiclesListFragment.OnVehicleItemClickedListener,
    VehiclesContract.View {

    private var mPresenter: VehiclesContract.Presenter? = null
    private var mTabsPagerAdapter: TabsPagerAdapter? = null
    private var vehiclesListFragment: VehiclesListFragment = VehiclesListFragment(this)
    private var vehiclesMapsFragment: VehiclesMapsFragment = VehiclesMapsFragment()


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
        srlVehicles.setOnRefreshListener {
            mPresenter!!.loadVehicles()
        }

        vehiclesListFragment.observeListPresenter(mPresenter!!.getPresenter())
        vehiclesMapsFragment.observeListPresenter(mPresenter!!.getPresenter())
    }

    private fun setupViewPager() {
        mTabsPagerAdapter = TabsPagerAdapter(activity!!.supportFragmentManager)
        srlVehicles.viewpager.adapter = mTabsPagerAdapter
        srlVehicles.viewpager.currentItem = 0
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

    override fun onVehicleItemClicked(position: Int) {
        segmented.check(R.id.rb_map)
        vehiclesMapsFragment.selectVehicle(position)
    }

    private inner class TabsPagerAdapter internal constructor(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return if (position == 0) vehiclesListFragment
            else vehiclesMapsFragment
        }

        override fun getCount(): Int {
            return 2
        }
    }

    override val isActive: Boolean
        get() = isAdded

    //Just for unit testing
    override fun showVehicleList(vehicles: List<Vehicle>) {
    }

    override fun setPresenter(presenter: VehiclesContract.Presenter) {
        mPresenter = presenter
    }
}
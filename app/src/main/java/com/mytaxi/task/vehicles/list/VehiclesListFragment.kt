package com.mytaxi.task.vehicles.list

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mytaxi.task.BaseNetworkFragment
import com.mytaxi.task.R
import com.mytaxi.task.data.models.Vehicle
import com.mytaxi.task.vehicles.VehiclesPresenter
import kotlinx.android.synthetic.main.fragment_vehicles_list.*
import kotlinx.android.synthetic.main.item_vehicle.view.*


@SuppressLint("ValidFragment")
class VehiclesListFragment(var onVehicleItemClickedListener: OnVehicleItemClickedListener?) :
    BaseNetworkFragment(), VehiclesListContract.View {
    private var mPresenter: VehiclesListContract.Presenter? = null
    private lateinit var mAdapter: VehiclesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        mAdapter = VehiclesAdapter(activity, ArrayList(), onVehicleItemClickedListener!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vehicles_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        if (mPresenter == null) {
            mPresenter = VehiclesListPresenter(this)
        }

        if (savedInstanceState == null) {
            mPresenter!!.start()
        }
    }

    private fun setupRecyclerView() {
        rv_vehicles.isNestedScrollingEnabled = false
        rv_vehicles.layoutManager = LinearLayoutManager(activity)
        rv_vehicles.adapter = mAdapter
    }

    override fun observeListPresenter(presenter: VehiclesPresenter) {
        presenter.mVehicles.observe(this, Observer {
            if (it!!.isEmpty()) {
                tvNoVehicles.visibility = View.VISIBLE
                rv_vehicles.visibility = View.GONE
            } else {
                tvNoVehicles.visibility = View.GONE
                rv_vehicles.visibility = View.VISIBLE
                mAdapter.replaceData(it!!)
            }
        })
    }

    override val isActive: Boolean
        get() = isAdded

    override fun setPresenter(presenter: VehiclesListContract.Presenter) {
        mPresenter = presenter
    }

    class VehiclesAdapter(
        private val context: FragmentActivity?,
        private var mVehicles: List<Vehicle?>,
        private val onVehicleItemClickedListener: OnVehicleItemClickedListener
    ) : RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(context).inflate(R.layout.item_vehicle, parent, false)
            return ViewHolder(v, onVehicleItemClickedListener)
        }

        override fun getItemCount(): Int {
            return mVehicles.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val vehicle = mVehicles[position]
            holder.tvFleetType.text = vehicle!!.fleetType.toString()
            holder.tvHeading.text = vehicle.heading.toString()
        }

        fun replaceData(vehicles: List<Vehicle>) {
            setList(vehicles)
            notifyDataSetChanged()
        }

        private fun setList(vehicles: List<Vehicle>) {
            this.mVehicles = checkNotNull(vehicles)
        }

        inner class ViewHolder(v: View, private val onVehicleItemClickedListener: OnVehicleItemClickedListener) :
            RecyclerView.ViewHolder(v) {
            val tvFleetType = v.tv_fleet_type!!
            val tvHeading = v.tv_heading!!

            init {
                v.setOnClickListener {
                    onVehicleItemClickedListener.onVehicleItemClicked(adapterPosition)
                }
            }
        }
    }

    interface OnVehicleItemClickedListener {
        fun onVehicleItemClicked(position: Int)
    }
}
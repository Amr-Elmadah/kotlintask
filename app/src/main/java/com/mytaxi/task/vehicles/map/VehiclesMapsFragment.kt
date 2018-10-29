package com.mytaxi.task.vehicles.map

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mytaxi.task.BaseNetworkFragment
import com.mytaxi.task.R
import com.mytaxi.task.data.models.FleetType
import com.mytaxi.task.data.models.Vehicle
import com.mytaxi.task.vehicles.VehiclesPresenter


class VehiclesMapsFragment : BaseNetworkFragment(), OnMapReadyCallback, VehiclesMapsContract.View {
    private var mPresenter: VehiclesMapsContract.Presenter? = null
    private lateinit var mMap: GoogleMap
    private lateinit var mVehicles: List<Vehicle>
    private var isMapInitialized: Boolean = false
    private var isVehiclesLoaded: Boolean = false
    private var markers: ArrayList<Marker> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicles_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        isMapInitialized = true
        if (isVehiclesLoaded) {
            showVehicles(mVehicles)
        }
    }

    override val isActive: Boolean
        get() = isAdded

    override fun observeListPresenter(presenter: VehiclesPresenter) {
        presenter.mVehicles.observe(this, Observer {
            showVehicles(it!!)
        })
    }

    override fun showVehicles(vehicles: List<Vehicle>) {
        isVehiclesLoaded = true
        mVehicles = vehicles
        if (isMapInitialized) {
            mMap.clear()
            if (!vehicles.isEmpty()) {
                markers = ArrayList()
                for (vehicle in vehicles) {
                    val markerColor: Float =
                        if (vehicle.fleetType === FleetType.POOLING) BitmapDescriptorFactory.HUE_AZURE else BitmapDescriptorFactory.HUE_YELLOW
                    putMarker(vehicle.id, vehicle.coordinate.latitude, vehicle.coordinate.longitude, markerColor)
                }
                centerlizeMarkers(markers)
            }
        }
    }

    override fun setPresenter(presenter: VehiclesMapsContract.Presenter) {
        mPresenter = presenter
    }

    private fun putMarker(id: Int, latitude: Double, longitude: Double, markerColor: Float) {
        val m = MarkerOptions()
        m.position(LatLng(latitude, longitude))
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        m.title(getString(R.string.id) + "\u200e" + id)
        m.icon(BitmapDescriptorFactory.defaultMarker(markerColor))
        mMap.setOnMarkerClickListener { false }
        markers.add(mMap.addMarker(m))
    }

    override fun centerlizeMarkers(markers: List<Marker>) {
        val builder = LatLngBounds.Builder()
        for (marker in markers) {
            builder.include(marker.position)
        }
        val bounds = builder.build()
        val padding = resources.getDimensionPixelOffset(R.dimen._50sdp)
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mMap.animateCamera(cameraUpdate)
    }

    override fun selectVehicle(position: Int) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
            LatLng(
                mVehicles[position].coordinate.latitude, mVehicles[position].coordinate.longitude
            ), 16.0f
        )
        mMap.animateCamera(cameraUpdate)
    }
}

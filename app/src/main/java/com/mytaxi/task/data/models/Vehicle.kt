package com.mytaxi.task.data.models

import com.google.gson.annotations.Expose
import org.parceler.Parcel

@Parcel
data class Vehicle(
    @Expose
    var id: Int,
    @Expose
    var coordinate: Coordinate,
    @Expose
    var fleetType: FleetType,
    @Expose
    var heading: Double
)
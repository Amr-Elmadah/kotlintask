package com.mytaxi.task.data.models

import com.google.gson.annotations.Expose
import org.parceler.Parcel

@Parcel
data class Coordinate(
    @Expose
    var latitude: Double,
    @Expose
    var longitude: Double
)
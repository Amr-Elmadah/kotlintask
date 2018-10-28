package com.mytaxi.task.network.interfaces

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VehiclesService {

    @GET("")
    fun getVehicles(
        @Query("p1Lat") p1Lat: Double, @Query("p1Lon") p1Lon: Double, @Query("p2Lat") p2Lat: Double, @Query(
            "p2Lon"
        ) p2Lon: Double
    ): Call<JsonElement>
}

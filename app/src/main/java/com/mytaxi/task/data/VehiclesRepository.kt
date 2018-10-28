package com.mytaxi.task.data

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.mytaxi.task.data.callbacks.LoadVehiclesCallback
import com.mytaxi.task.data.models.Vehicle
import com.mytaxi.task.network.ServiceGenerator
import com.mytaxi.task.network.interfaces.VehiclesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class VehiclesRepository : VehiclesDataSource {

    override fun loadVehicles(loadVehiclesCallback: LoadVehiclesCallback) {
        val vehiclesService = ServiceGenerator.createService(VehiclesService::class.java)
        val call = vehiclesService.getVehicles(
            53.694865, 9.757589, 53.394655, 10.099891
        )
        call.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
                val vehicles: List<Vehicle>
                if (response!!.isSuccessful && response.body()!!.asJsonObject.has("poiList")) {
                    val responseJsonArray = response.body()!!.asJsonObject.get("poiList").asJsonArray
                    vehicles = Gson().fromJson<List<Vehicle>>(
                        responseJsonArray,
                        object : TypeToken<List<Vehicle>>() {}.type
                    )
                    loadVehiclesCallback.onVehiclesLoaded(vehicles)
                } else {
                    loadVehiclesCallback.onResponseError(response.code())
                }
            }

            override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {
                if (t is IOException) {
                    loadVehiclesCallback.onNoConnection()
                } else {
                    loadVehiclesCallback.onTimeOut()
                }
            }
        })
    }

}
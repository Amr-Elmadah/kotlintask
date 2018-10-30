package com.mytaxi.task

import com.mytaxi.task.data.VehiclesRepository
import com.mytaxi.task.data.callbacks.LoadVehiclesCallback
import com.mytaxi.task.data.models.Coordinate
import com.mytaxi.task.data.models.FleetType
import com.mytaxi.task.data.models.Vehicle
import com.mytaxi.task.vehicles.VehiclesContract
import com.mytaxi.task.vehicles.VehiclesPresenter
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class LoadingVehiclesUnitTest {
    @Mock
    private lateinit var view: VehiclesContract.View

    @Mock
    private var vehiclesRepository: VehiclesRepository? = null

    private lateinit var presenter: VehiclesPresenter

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<LoadVehiclesCallback>

    @Captor
    lateinit var entityArgumentCaptor: ArgumentCaptor<List<Vehicle>>

    @Before
    fun setup() {
        presenter = VehiclesPresenter(vehiclesRepository, view)
    }

    @Test
    fun checkListWithData() {
        presenter.loadVehiclesForTest()
        verify(vehiclesRepository)!!.loadVehicles(com.nhaarman.mockitokotlin2.capture(argumentCaptor))
        argumentCaptor.value.onVehiclesLoaded(getList())
        verify(view).setLoadingIndicator(false)
        verify(view).showVehicleList(com.nhaarman.mockitokotlin2.capture(entityArgumentCaptor))
        assertTrue(entityArgumentCaptor.value.size == 10)
    }

    @Test
    fun checkEmptyNullList() {
        presenter.loadVehiclesForTest()
        verify(vehiclesRepository)!!.loadVehicles(com.nhaarman.mockitokotlin2.capture(argumentCaptor))
        argumentCaptor.value.onVehiclesLoaded(getList())
        verify(view).setLoadingIndicator(false)
        verify(view).showVehicleList(com.nhaarman.mockitokotlin2.capture(entityArgumentCaptor))
        assertTrue(entityArgumentCaptor.value != null && !entityArgumentCaptor.value.isEmpty())
    }

    private fun getList(): List<Vehicle> {
        val vehicles = ArrayList<Vehicle>()

        for (i in 0..9) {
            vehicles.add(
                Vehicle(
                    i, Coordinate(20.0, 20.0), FleetType.POOLING, 20.0
                )
            )
        }
        return vehicles
    }
}

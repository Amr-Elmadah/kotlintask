package com.mytaxi.task.vehicles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.mytaxi.task.R
import com.mytaxi.task.utils.ActivityUtils

class VehiclesActivity : AppCompatActivity() {

    private var vehiclesFragment: VehiclesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)
        vehiclesFragment = if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            VehiclesFragment()
        } else {
            supportFragmentManager.findFragmentById(R.id.container) as VehiclesFragment
        }
        ActivityUtils.addFragmentToActivity(supportFragmentManager, vehiclesFragment, R.id.container)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

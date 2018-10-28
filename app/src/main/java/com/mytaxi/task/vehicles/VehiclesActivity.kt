package com.mytaxi.task.vehicles

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.mytaxi.task.R
import kotlinx.android.synthetic.main.activity_vehicles.*

class VehiclesActivity : AppCompatActivity() {

    private var mTabsPagerAdapter: TabsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)
        setupViewPager()
        setupSegmentedButton()
    }

    private fun setupViewPager() {
        mTabsPagerAdapter = TabsPagerAdapter(supportFragmentManager)
        viewpager.adapter = mTabsPagerAdapter
        viewpager.currentItem = 0
    }

    private fun setupSegmentedButton() {
        segmented.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.rb_list)
                viewpager.currentItem = 0
            else if (checkedId == R.id.rb_map) {
                viewpager.currentItem = 1
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
}

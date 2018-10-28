package com.mytaxi.task.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.mytaxi.task.utils.Utils.checkNotNull

object ActivityUtils {
    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        //        transaction.setCustomAnimations(R.anim.activity_open_translate, 0);
        transaction.add(frameId, fragment)
        transaction.commit()
        //        fragmentManager.executePendingTransactions();
    }

    fun replaceFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int, tag: String
    ) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, tag)
        transaction.commit()
    }
}

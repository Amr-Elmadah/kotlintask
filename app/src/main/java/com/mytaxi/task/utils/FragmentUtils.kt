package com.mytaxi.task.utils

import android.support.v4.app.Fragment

object FragmentUtils {
    fun <T> getParent(fragment: Fragment, parentClass: Class<T>): T? {
        val parentFragment = fragment.parentFragment
        return when {
            parentClass.isInstance(parentFragment) -> //Checked by runtime methods
                parentFragment as T?
            parentClass.isInstance(fragment.activity) -> //Checked by runtime methods

                fragment.activity as T?
            else -> {
                val parent: String = if (parentFragment != null) parentFragment.javaClass.name else {
                    fragment.activity!!.javaClass.name
                }
                throw ClassCastException(parent + " must implement " + parentClass.name)
            }
        }
    }
}

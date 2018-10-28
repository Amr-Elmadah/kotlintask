package com.mytaxi.task.utils

object Utils {
    fun <T> checkNotNull(reference: T?): T {
        return reference!!
    }
}

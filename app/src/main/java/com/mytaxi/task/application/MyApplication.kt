package com.mytaxi.task.application

import android.content.Context
import android.support.multidex.MultiDex

class MyApplication : BaseApplication() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
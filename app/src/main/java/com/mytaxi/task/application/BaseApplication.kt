package com.mytaxi.task.application

import android.app.Application
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import com.crashlytics.android.Crashlytics
import com.mytaxi.task.R
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric

open class BaseApplication : Application() {

    private var enRegular = R.font.montserrat_regular
    private var enBold = R.font.montserrat_bold

    override fun onCreate() {
        super.onCreate()
        regularFont = ResourcesCompat.getFont(this, enRegular)
        boldFont = ResourcesCompat.getFont(this, enBold)
        Fabric.with(this, Crashlytics())
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    companion object {
        var regularFont: Typeface? = null
        var boldFont: Typeface? = null
    }
}

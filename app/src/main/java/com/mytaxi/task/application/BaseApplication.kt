package com.mytaxi.task.application

import android.app.Application
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import com.mytaxi.task.R

open class BaseApplication : Application() {

    private var enRegular = R.font.montserrat_regular
    private var enBold = R.font.montserrat_bold

    override fun onCreate() {
        super.onCreate()
        regularFont = ResourcesCompat.getFont(this, enRegular)
        boldFont = ResourcesCompat.getFont(this, enBold)
    }

    companion object {
        var regularFont: Typeface? = null
        var boldFont: Typeface? = null
    }
}

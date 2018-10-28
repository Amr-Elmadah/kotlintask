package com.mytaxi.task.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.mytaxi.task.R
import com.mytaxi.task.application.BaseApplication

internal object CFont {
    fun init(textView: TextView, context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TextFonts,
            0, 0
        )
        try {
            val mFontStyle = a.getInteger(R.styleable.TextFonts_textStyleFont, 0)
            when (mFontStyle) {
                0 -> textView.typeface = BaseApplication.regularFont
                1 -> textView.typeface = BaseApplication.boldFont
                else -> textView.typeface = BaseApplication.regularFont
            }
        } finally {
            a.recycle()
        }
    }
}

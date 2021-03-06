package com.mytaxi.task.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioButton


class CRadioButton : RadioButton {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        CFont.init(this, context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        CFont.init(this, context, attrs)
    }

    constructor(context: Context) : super(context) {
        CFont.init(this, context, null)
    }
}

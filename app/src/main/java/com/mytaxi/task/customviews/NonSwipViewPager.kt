package com.mytaxi.task.customviews

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class NonSwipViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return false
    }

}

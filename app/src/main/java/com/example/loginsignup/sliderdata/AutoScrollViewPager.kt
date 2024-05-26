package com.example.loginsignup.sliderdata

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2

class AutoScrollViewPager(
    private val viewPager: ViewPager2,
    private val interval: Long = 3000 // 3 seconds
) {
    private val handler = Handler(Looper.getMainLooper())
    private var currentItem = 0

    private val runnable = object : Runnable {
        override fun run() {
            if (viewPager.adapter != null) {
                currentItem = (currentItem + 1) % viewPager.adapter!!.itemCount
                viewPager.setCurrentItem(currentItem, true)
                handler.postDelayed(this, interval)
            }
        }
    }

    fun start() {
        handler.postDelayed(runnable, interval)
    }

    fun stop() {
        handler.removeCallbacks(runnable)
    }
}
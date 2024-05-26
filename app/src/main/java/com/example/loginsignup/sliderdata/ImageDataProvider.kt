package com.example.loginsignup.sliderdata

import android.content.Context
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.loginsignup.R
import com.example.loginsignup.activity_views.Activity1
import com.example.loginsignup.activity_views.Activity2
import com.example.loginsignup.activity_views.Activity3
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ImageDataProvider(
    private val context: Context,
    private val viewPager: ViewPager2,
    private val tabLayout: TabLayout
) {

    private val imageItems = listOf(
        ImageItem(R.drawable.image1, "Title 1"),
        ImageItem(R.drawable.image2, "Title 2"),
        ImageItem(R.drawable.image3, "Title 3")
    )

    private val imageSliderAdapter = ImageSliderAdapter(imageItems) { position ->
        onItemClick(position)
    }

    private lateinit var autoScrollViewPager: AutoScrollViewPager

    init {
        setupViewPager()
        setupAutoScrollViewPager()
        setupViewPagerClickListener()
        setupTabLayoutMediator()
    }

    private fun setupViewPager() {
        viewPager.adapter = imageSliderAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    viewPager.adapter?.notifyDataSetChanged()
                    viewPager.adapter?.notifyItemChanged(0)
                    viewPager.setOnClickListener {
                        val currentPosition = viewPager.currentItem
                        onItemClick(currentPosition)
                    }
                }
            }
        })
    }

    private fun setupAutoScrollViewPager() {
        autoScrollViewPager = AutoScrollViewPager(viewPager)
        autoScrollViewPager.start()
    }

    private fun setupViewPagerClickListener() {
        viewPager.setOnClickListener {
            val currentPosition = viewPager.currentItem
            onItemClick(currentPosition)
        }
    }

    // TabLayout aur ViewPager2 ke saath TabLayoutMediator ka upyog karke char alag-alag tab styles ko customize karein
    private fun setupTabLayoutMediator() {
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }

    private fun onItemClick(position: Int) {
        when (position) {
            0 -> startActivity(Activity1::class.java)
            1 -> startActivity(Activity2::class.java)
            2 -> startActivity(Activity3::class.java)
        }
    }

    private fun startActivity(activityClass: Class<*>) {
        val intent = Intent(context, activityClass)
        context.startActivity(intent)
    }

    fun stopAutoScrollViewPager() {
        autoScrollViewPager.stop()
    }
}
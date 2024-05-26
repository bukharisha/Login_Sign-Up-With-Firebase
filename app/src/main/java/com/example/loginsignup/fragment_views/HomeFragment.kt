package com.example.loginsignup.fragment_views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.loginsignup.databinding.FragmentHomeBinding
import com.example.loginsignup.sliderdata.ImageDataProvider
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    // Fragment binding
    private lateinit var binding: FragmentHomeBinding

    // Auto slider
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var imageDataProvider: ImageDataProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Auto slider
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        imageDataProvider = ImageDataProvider(requireContext(), viewPager, tabLayout)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        imageDataProvider.stopAutoScrollViewPager()
    }
}

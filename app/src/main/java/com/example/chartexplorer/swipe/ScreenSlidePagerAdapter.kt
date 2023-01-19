package com.example.chartexplorer.swipe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chartexplorer.swipe.fragment.SwipeFragment
import com.example.chartexplorer.ui.barChart.BarChartFragment
import com.example.chartexplorer.ui.home.HomeFragment
import com.example.chartexplorer.ui.pieChart.PieChartFragment
import com.github.mikephil.charting.charts.BarChart

class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return PieChartFragment()
            }
            2 -> {
                return BarChartFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }
}
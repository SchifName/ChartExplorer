package com.example.chartexplorer.swipe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.chartexplorer.ui.barChart.BarChartFragment
import com.example.chartexplorer.ui.pieChart.PieChartFragment

class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PieChartFragment()
            }
            1 -> {
                BarChartFragment()
            }
            else -> {
                PieChartFragment()
            }
        }
    }

    /*override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
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
    }*/
}
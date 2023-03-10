package com.example.chartexplorer.swipe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.chartexplorer.databinding.FragmentSwipeBinding
import com.example.chartexplorer.swipe.ScreenSlidePagerAdapter

class SwipeFragment : Fragment() {

    private var _binding: FragmentSwipeBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pager: ViewPager = binding.Pager
        pager.adapter = activity?.let { ScreenSlidePagerAdapter(it.supportFragmentManager) }
    }

}
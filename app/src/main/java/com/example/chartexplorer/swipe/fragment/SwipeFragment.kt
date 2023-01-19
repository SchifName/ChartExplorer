package com.example.chartexplorer.swipe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chartexplorer.R

class SwipeFragment : Fragment() {

    companion object {
        fun newInstance() = SwipeFragment()
    }

    private lateinit var viewModel: SwipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_swipe, container, false)

}
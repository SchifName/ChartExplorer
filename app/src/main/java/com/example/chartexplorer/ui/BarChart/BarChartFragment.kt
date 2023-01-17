package com.example.chartexplorer.ui.BarChart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chartexplorer.R

class BarChartFragment : Fragment() {

    companion object {
        fun newInstance() = BarChartFragment()
    }

    private lateinit var viewModel: BarChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bar_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BarChartViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
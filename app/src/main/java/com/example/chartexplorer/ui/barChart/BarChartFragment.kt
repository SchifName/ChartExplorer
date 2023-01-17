package com.example.chartexplorer.ui.barChart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chartexplorer.databinding.FragmentBarChartBinding
import com.example.chartexplorer.utils.retrieveRecordsAndPopulateBarChart

class BarChartFragment : Fragment() {

    private var _binding: FragmentBarChartBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: BarChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBarChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveRecordsAndPopulateBarChart(binding.ourBarChart2)
        binding.ourBarChart2.xAxis.textSize = 8f
        binding.ourBarChart2.axisLeft.textSize = 8f
        binding.ourBarChart2.data.setValueTextSize(7f)
    }

}
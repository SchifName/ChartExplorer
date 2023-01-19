package com.example.chartexplorer.ui.pieChart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chartexplorer.databinding.FragmentPieChartBinding
import com.example.chartexplorer.utils.retrieveRecordsAndPopulatePieChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.utils.Utils

class PieChartFragment : Fragment() {

    private var _binding: FragmentPieChartBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: PieChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPieChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveRecordsAndPopulatePieChart(binding.ourPieChart2)
        binding.ourPieChart2.setCenterTextSize(17f)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
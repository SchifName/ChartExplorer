package com.example.chartexplorer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chartexplorer.databinding.FragmentHomeBinding
import com.example.chartexplorer.utils.DataViewModel
import com.example.chartexplorer.utils.retrieveRecordsAndPopulateCharts
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val dataViewModel: DataViewModel by activityViewModels()

    private val binding get() = _binding!!

    private lateinit var ourPieChart: PieChart

    private lateinit var ourBarChart: BarChart

    private lateinit var ourLineChart: LineChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ourPieChart = binding.ourPieChart
        ourBarChart = binding.ourBarChart
        ourLineChart = binding.ourLineChart
        retrieveRecordsAndPopulateCharts(dataViewModel.animal.value,ourPieChart, ourBarChart, ourLineChart)

        ourPieChart.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPieChartFragment()
            this.findNavController().navigate(action)
        }

        ourBarChart.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToBarChartFragment()
            this.findNavController().navigate(action)
        }

        binding.cardPie.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPieChartFragment()
            this.findNavController().navigate(action)
        }

        binding.cardBar.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToBarChartFragment()
            this.findNavController().navigate(action)
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
package com.example.chartexplorer.ui.pieChart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.chartexplorer.databinding.FragmentPieChartBinding
import com.example.chartexplorer.utils.DataViewModel
import com.example.chartexplorer.utils.retrieveRecordsAndPopulatePieChart

class PieChartFragment : Fragment() {

    private var _binding: FragmentPieChartBinding? = null

    private val binding get() = _binding!!

    private val dataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPieChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ourPieChart2.setCenterTextSize(17f)
        dataViewModel.animal.observe(this.viewLifecycleOwner) {
            retrieveRecordsAndPopulatePieChart(binding.ourPieChart2, it)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
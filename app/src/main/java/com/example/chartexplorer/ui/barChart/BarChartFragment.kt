package com.example.chartexplorer.ui.barChart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.chartexplorer.databinding.FragmentBarChartBinding
import com.example.chartexplorer.utils.DataViewModel
import com.example.chartexplorer.utils.retrieveRecordsAndPopulateBarChart

class BarChartFragment : Fragment() {

    private var _binding: FragmentBarChartBinding? = null

    private val binding get() = _binding!!

    private val dataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBarChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataViewModel.animal.observe(this.viewLifecycleOwner) {
            retrieveRecordsAndPopulateBarChart(binding.ourBarChart2, it)
        }
    }

}
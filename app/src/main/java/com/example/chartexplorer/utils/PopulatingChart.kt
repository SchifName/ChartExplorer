package com.example.chartexplorer.utils

import android.graphics.Color
import com.example.chartexplorer.model.AnimalModel
import com.example.chartexplorer.network.AnimalsInfoFromInternet
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

val animal = listOf(
    AnimalModel(
        animalId = 0,
        animalName = "Lion",
        totNumber = 4,
        avgAge = 10,
        avgGrowth = 5
    ),
    AnimalModel(
        animalId = 1,
        animalName = "Zebra",
        totNumber = 8,
        avgAge = 16,
        avgGrowth = 8
    )
)

//creating the instance of DatabaseHandler class
//calling the retrieve Animals method of DatabaseHandler class to read the records
//create arrays for storing the values gotten
val animalArray = mutableListOf<AnimalModel>()

fun retrieveRecordsAndPopulatePieChart(
    ourPieChart: PieChart,
    animalsInfoFromInternet: AnimalsInfoFromInternet
) {
    populatePieChart(animalsInfoFromInternet, ourPieChart)
}

fun retrieveRecordsAndPopulateBarChart(
    ourBarChart: BarChart,
    animalsInfoFromInternet: AnimalsInfoFromInternet
) {
    populateBarChart(animalsInfoFromInternet, ourBarChart)
}

fun retrieveRecordsAndPopulateCharts(
    animalsInfoFromInternet: AnimalsInfoFromInternet?,
    ourPieChart: PieChart,
    ourBarChart: BarChart,
    ourLineChart: LineChart
) {
    if(animalsInfoFromInternet != null){
        populatePieChart(animalsInfoFromInternet ,ourPieChart)
        populateBarChart(animalsInfoFromInternet, ourBarChart)
        populateLineChart(animalsInfoFromInternet, ourLineChart)
    }
}

fun populatePieChart(animalsInfoFromInternet: AnimalsInfoFromInternet, ourPieChart: PieChart) {
    //an array to store the pie slices entry
    val ourPieEntry = ArrayList<PieEntry>()
    animalsInfoFromInternet.animals.forEach { animal ->
        ourPieEntry.add(PieEntry(animal.totNumber.toFloat(), animal.animalName))
    }

    //assigning color to each slices
    val pieShades: ArrayList<Int> = ArrayList()
    pieShades.add(Color.parseColor("#0E2DEC"))
    pieShades.add(Color.parseColor("#B7520E"))
    pieShades.add(Color.parseColor("#5E6D4E"))
    pieShades.add(Color.parseColor("#DA1F12"))

    //add values to the pie dataset and passing them to the constructor
    val ourSet = PieDataSet(ourPieEntry, "")
    val data = PieData(ourSet)

    //setting the slices divider width
    ourSet.sliceSpace = 1f

    //populating the colors and data
    ourSet.colors = pieShades
    ourPieChart.data = data
    //setting color and size of text
    data.setValueTextColor(Color.WHITE)
    data.setValueTextSize(10f)
    ourPieChart.setCenterTextSize(17f)

    //add an animation when rendering the pie chart
    ourPieChart.animateY(1400, Easing.EaseInOutQuad)
    //disabling center hole
    ourPieChart.isDrawHoleEnabled = false
    //do not show description text
    ourPieChart.description.isEnabled = false
    //legend enabled and its various appearance settings
    ourPieChart.legend.isEnabled = true
    ourPieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
    ourPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
    ourPieChart.legend.isWordWrapEnabled = true

    //dont show the text values on slices e.g Antelope, impala etc
    ourPieChart.setDrawEntryLabels(false)
    //refreshing the chart
    ourPieChart.invalidate()

}

fun populateBarChart(animalsInfoFromInternet: AnimalsInfoFromInternet, ourBarChart: BarChart) {
    //adding values
    val ourBarEntries: ArrayList<BarEntry> = ArrayList()

    animalsInfoFromInternet.animals.forEach { animal ->
        ourBarEntries.add(BarEntry(animal.animalId.toFloat(), animal.avgAge.toFloat()))
    }

    val barDataSet = BarDataSet(ourBarEntries, "")
    //set a template coloring
    barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
    val data = BarData(barDataSet)
    ourBarChart.data = data
    //setting the x-axis
    val xAxis: XAxis = ourBarChart.xAxis

    ourBarChart.xAxis.textSize = 8f
    ourBarChart.axisLeft.textSize = 8f
    ourBarChart.data.setValueTextSize(10f)

    //calling methods to hide x-axis gridlines
    ourBarChart.axisLeft.setDrawGridLines(false)
    xAxis.setDrawGridLines(false)
    xAxis.setDrawAxisLine(false)

    //remove legend
    ourBarChart.legend.isEnabled = false

    //remove description label
    ourBarChart.description.isEnabled = false

    //add animation
    ourBarChart.animateY(3000)
    //refresh the chart
    ourBarChart.invalidate()
}

fun populateLineChart(animalsInfoFromInternet: AnimalsInfoFromInternet, ourLineChart: LineChart) {
    val ourLineChartEntries: ArrayList<Entry> = ArrayList()
    for (animal in animalsInfoFromInternet.animals) {
        ourLineChartEntries.add(Entry(animal.animalId.toFloat(), animal.totNumber.toFloat()))
    }
    val lineDataSet = LineDataSet(ourLineChartEntries, "")
    lineDataSet.setColors(*ColorTemplate.PASTEL_COLORS)
    val data = LineData(lineDataSet)
    ourLineChart.axisLeft.setDrawGridLines(false)
    val xAxis: XAxis = ourLineChart.xAxis
    xAxis.setDrawGridLines(false)
    xAxis.setDrawAxisLine(false)
    ourLineChart.legend.isEnabled = false

    //remove description label
    ourLineChart.description.isEnabled = false

    //add animation
    ourLineChart.animateX(1000, Easing.EaseInSine)
    ourLineChart.data = data
    //refresh
    ourLineChart.invalidate()
}
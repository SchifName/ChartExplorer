package com.example.chartexplorer.utils

import android.graphics.Color
import com.example.chartexplorer.model.AnimalModel
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
        animalId = 0,
        animalName = "Zebra",
        totNumber = 8,
        avgAge = 16,
        avgGrowth = 8
    )
)

//creating the instance of DatabaseHandler class
//calling the retrieve Animals method of DatabaseHandler class to read the records
//create arrays for storing the values gotten
val animalIDArray = Array<Int>(animal.size) { 0 }
val animalNameArray = Array<String>(animal.size) { "natgeo" }
val animalNumberArray = Array<Int>(animal.size) { 0 }
val animalAgeArray = Array<Int>(animal.size) { 0 }
val animalGrowthArray = Array<Int>(animal.size) { 0 }


fun retrieveRecordsAndPopulatePieChart(ourPieChart: PieChart) {
    //add the records till done
    var index = 0
    for (a in animal) {
        animalIDArray[index] = a.animalId
        animalNameArray[index] = a.animalName
        animalNumberArray[index] = a.totNumber
        animalAgeArray[index] = a.avgAge
        animalGrowthArray[index] = a.avgGrowth
        index++
    }
    populatePieChart(animalNumberArray, animalNameArray, ourPieChart)
}

fun retrieveRecordsAndPopulateBarChart(ourBarChart: BarChart) {
    //add the records till done
    var index = 0
    for (a in animal) {
        animalIDArray[index] = a.animalId
        animalNameArray[index] = a.animalName
        animalNumberArray[index] = a.totNumber
        animalAgeArray[index] = a.avgAge
        animalGrowthArray[index] = a.avgGrowth
        index++
    }
    populateBarChart(animalAgeArray, ourBarChart)
}

fun retrieveRecordsAndPopulateCharts(
    ourPieChart: PieChart,
    ourBarChart: BarChart,
    ourLineChart: LineChart
) {
    //add the records till done
    var index = 0
    for (a in animal) {
        animalIDArray[index] = a.animalId
        animalNameArray[index] = a.animalName
        animalNumberArray[index] = a.totNumber
        animalAgeArray[index] = a.avgAge
        animalGrowthArray[index] = a.avgGrowth
        index++
    }
    //call the methods for populating the charts
    populatePieChart(animalNumberArray, animalNameArray, ourPieChart)
    populateBarChart(animalAgeArray, ourBarChart)
    populateLineChart(animalGrowthArray, ourLineChart)

}

fun populatePieChart(values: Array<Int>, labels: Array<String>, ourPieChart: PieChart) {
    //an array to store the pie slices entry
    val ourPieEntry = ArrayList<PieEntry>()
    var i = 0

    for (entry in values) {
        //converting to float
        var value = values[i].toFloat()
        var label = labels[i]
        //adding each value to the pieentry array
        ourPieEntry.add(PieEntry(value, label))
        i++
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

fun populateBarChart(values: Array<Int>, ourBarChart: BarChart) {
    //adding values
    val ourBarEntries: ArrayList<BarEntry> = ArrayList()
    var i = 0

    for (entry in values) {
        var value = values[i].toFloat()
        ourBarEntries.add(BarEntry(i.toFloat(), value))
        i++
    }


    val barDataSet = BarDataSet(ourBarEntries, "")
    //set a template coloring
    barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
    val data = BarData(barDataSet)
    ourBarChart.data = data
    //setting the x-axis
    val xAxis: XAxis = ourBarChart.xAxis
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

fun populateLineChart(values: Array<Int>, ourLineChart: LineChart) {
    val ourLineChartEntries: ArrayList<Entry> = ArrayList()

    var i = 0

    for (entry in values) {
        var value = values[i].toFloat()
        ourLineChartEntries.add(Entry(i.toFloat(), value))
        i++
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
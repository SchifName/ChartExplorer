package com.example.chartexplorer.network

import com.google.gson.annotations.SerializedName

data class AnimalsInfoFromInternet(
    @SerializedName("Animals") var animals: List<AnimalsInfo>,
)
data class AnimalsInfo(
    @SerializedName("animalId") val animalId: Int,
    @SerializedName("animalName") var animalName: String,
    @SerializedName("totNumber") var totNumber: Int,
    @SerializedName("avgAge") var avgAge: Int,
    @SerializedName("avgGrowth") var avgGrowth: Int
)
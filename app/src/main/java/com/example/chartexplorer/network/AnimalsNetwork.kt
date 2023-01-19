package com.example.chartexplorer.network

import com.example.chartexplorer.model.AnimalModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL =
    "https://raw.githubusercontent.com/SchifName/animals.json/main/"

var gsonPie: Gson = GsonBuilder()
    .setLenient()
    .create()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofitPie =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonPie))
        .build()


/**
 * A public interface that exposes the [getAnimalsInfo] method
 */
interface AnimalsNetwork {

    /**
     * Returns a [List] of [Animals] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "year" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("Content-Type: application/json")
    @GET("animals.json")
    suspend fun getAnimalsInfo(): AnimalsInfoFromInternet
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object AnimalsNet {
    val retrofitService: AnimalsNetwork by lazy { retrofitPie.create(AnimalsNetwork::class.java) }
}
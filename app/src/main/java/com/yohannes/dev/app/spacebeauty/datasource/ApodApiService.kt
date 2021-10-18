package com.yohannes.dev.app.spacebeauty.datasource

import com.yohannes.dev.app.spacebeauty.model.Apod
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiService {

    //https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&count=100
    @GET("/planetary/apod")
    fun getApod(@Query("api_key") api_key: String, @Query("count") count: Int): Call<List<Apod>>

    companion object {

        var BASE_URL = "https://api.nasa.gov"
        var apiInterface: ApodApiService? = null

        fun getInstance(): ApodApiService {

            if (apiInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiInterface = retrofit.create(ApodApiService::class.java)
            }
            return apiInterface!!
        }

    }
}
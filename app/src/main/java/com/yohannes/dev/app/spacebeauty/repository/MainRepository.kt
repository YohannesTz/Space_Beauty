package com.yohannes.dev.app.spacebeauty.repository

import com.yohannes.dev.app.spacebeauty.datasource.ApodApiService

class MainRepository (private val apodApiService: ApodApiService, val apiKey: String, val count: Int) {

    fun getApods() = apodApiService.getApod(apiKey, count)

}
package com.yohannes.dev.app.spacebeauty.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yohannes.dev.app.spacebeauty.model.Apod
import com.yohannes.dev.app.spacebeauty.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val mainRepository: MainRepository) : ViewModel() {

    val apodsList = MutableLiveData<List<Apod>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllApods() {

        val response = mainRepository.getApods()
        response.enqueue(object : Callback<List<Apod>> {
            override fun onResponse(call: Call<List<Apod>>, response: Response<List<Apod>>) {
                Log.e("SpaceBeauty", "onResponse: {$response}")
                apodsList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Apod>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getApods(): MutableLiveData<List<Apod>> {
        return apodsList
    }
}
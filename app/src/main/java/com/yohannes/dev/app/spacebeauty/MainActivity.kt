package com.yohannes.dev.app.spacebeauty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yohannes.dev.app.spacebeauty.databinding.ActivityMainBinding
import com.yohannes.dev.app.spacebeauty.datasource.ApodApiService
import com.yohannes.dev.app.spacebeauty.repository.MainRepository
import com.yohannes.dev.app.spacebeauty.ui.MainAdapter
import com.yohannes.dev.app.spacebeauty.viewmodel.MainViewModel
import com.yohannes.dev.app.spacebeauty.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val apiInterface = ApodApiService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var adapter = MainAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(apiInterface, "DEMO_KEY", 100))).get(MainViewModel::class.java)

        viewModel.apodsList.observe(this, {
            Log.e("AddedItems", "${it.size}")
            adapter.setApods(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.getAllApods()
    }
}
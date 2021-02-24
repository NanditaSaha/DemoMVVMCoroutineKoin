package com.example.demodatabindingwithcoroutine.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.demodatabindingwithcoroutine.R
import com.example.demodatabindingwithcoroutine.adapter.UserAdapter
import com.example.demodatabindingwithcoroutine.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val userViewModel by viewModel<HomeViewModel>()
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner=this
        binding.viewModel=userViewModel
        val userAdapter = UserAdapter()

        binding.adapters=userAdapter

        userViewModel.data.observe(this, Observer {
            Log.e("MainActivity", it.toString())
            it.let(userAdapter::submitList)
        })
    }
}
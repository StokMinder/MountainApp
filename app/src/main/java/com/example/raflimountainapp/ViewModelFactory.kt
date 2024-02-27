package com.example.raflimountainapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.raflimountainapp.data.MountainRepository
import com.example.raflimountainapp.screen.detail.DetailViewModel
import com.example.raflimountainapp.screen.home.RafliMountainAppViewModel

class ViewModelFactory(private val repository: MountainRepository) :
    ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RafliMountainAppViewModel::class.java)) {
                return RafliMountainAppViewModel(repository) as T
            }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
                return DetailViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

}
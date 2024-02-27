package com.example.raflimountainapp.screen.detail

import androidx.lifecycle.ViewModel
import com.example.raflimountainapp.data.MountainRepository
import com.example.raflimountainapp.model.Mountain

class DetailViewModel(
    private val repository: MountainRepository
): ViewModel() {
    fun getMountainId(id: Long): Mountain {
        return repository.geMountainId(id)
    }
}
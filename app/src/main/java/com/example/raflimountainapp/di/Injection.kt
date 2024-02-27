package com.example.raflimountainapp.di

import com.example.raflimountainapp.data.MountainRepository

object Injection {
    fun provideRepository(): MountainRepository {
        return MountainRepository.getInstance()
    }
}
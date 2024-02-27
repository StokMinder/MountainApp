package com.example.raflimountainapp.data

import com.example.raflimountainapp.model.Mountain
import com.example.raflimountainapp.model.MountainData

class MountainRepository {
    fun getMountain(): List<Mountain>{
        return MountainData.mountains
    }

    fun searchMount(query: String): List<Mountain>{
        return MountainData.mountains.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun geMountainId(id: Long): Mountain {
        return MountainData.mountains.first{
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: MountainRepository? = null

        fun getInstance(): MountainRepository =
            instance ?: synchronized(this) {
                MountainRepository().apply {
                    instance = this
                }
            }
    }
}
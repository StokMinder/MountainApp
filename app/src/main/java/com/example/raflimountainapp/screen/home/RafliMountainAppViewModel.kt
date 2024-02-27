package com.example.raflimountainapp.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.raflimountainapp.data.MountainRepository
import com.example.raflimountainapp.model.Mountain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RafliMountainAppViewModel (private val repository: MountainRepository): ViewModel(){
    private val _sortmountain = MutableStateFlow(
        repository.getMountain()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val sortedMountain: StateFlow<Map<Char, List<Mountain>>> get() = _sortmountain

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String){
        _query.value = newQuery
        _sortmountain.value = repository.searchMount(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

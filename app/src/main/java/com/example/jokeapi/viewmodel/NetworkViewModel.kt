package com.example.jokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapi.api.data.JokeResponse
import com.example.jokeapi.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(

    private val repository: JokeRepository,
) : ViewModel() {
    val jokeFlowData = MutableStateFlow<List<JokeResponse>>(emptyList())
    fun fetchInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            jokeFlowData.value = repository.fetchDataFromNetwork()
        }
    }

    init {
        fetchInitialData()
    }
}

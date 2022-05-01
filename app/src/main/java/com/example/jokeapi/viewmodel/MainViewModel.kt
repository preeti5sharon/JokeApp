package com.example.jokeapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import com.example.jokeapi.api.AppModule
import com.example.jokeapi.api.JokeDB
import com.example.jokeapi.api.JokeService
import com.example.jokeapi.api.data.JokeResponse
import com.example.jokeapi.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(

    private val repository: JokeRepository
) : ViewModel() {
    val jokeLiveData = MutableLiveData<List<JokeResponse>>()
    fun fetchInitialData() {
        viewModelScope.launch {

            jokeLiveData.value = repository.fetchDataFromDatabase()

        }
    }
}
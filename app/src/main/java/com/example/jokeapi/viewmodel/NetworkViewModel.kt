package com.example.jokeapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapi.api.data.JokeResponse
import com.example.jokeapi.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(

    private val repository: JokeRepository
) : ViewModel() {
    val jokeLiveData = MutableLiveData<List<JokeResponse>>()
    fun fetchInitialData() {
        viewModelScope.launch(Dispatchers.IO) {

            jokeLiveData.postValue(repository.fetchDataFromNetwork())

        }
    }
}
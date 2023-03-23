package com.example.jokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.jokeapi.api.data.JokeResponse
import com.example.jokeapi.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(

    private val repository: JokeRepository
) : ViewModel() {
    fun fetchInitialData(): Flow<PagingData<JokeResponse>> {
        return repository.fetchDataFromDatabase()
    }
}
package com.example.jokeapi.repository

import androidx.lifecycle.viewModelScope
import com.example.jokeapi.api.JokeDB
import com.example.jokeapi.api.JokeService
import com.example.jokeapi.api.data.JokeResponse
import kotlinx.coroutines.launch

class JokeRepository(
    private val database: JokeDB,
    private val api: JokeService
) {
    suspend fun fetchDataFromDatabase(): List<JokeResponse> {
        return database.getJokeDAO().getRandomJokes()
    }

    suspend fun fetchDataFromNetwork(): List<JokeResponse> {
        val list = mutableListOf<JokeResponse>()
        repeat(10) {

            val joke = api.getRandomJokes()
            database.getJokeDAO().insertJoke(joke)
            list.add(joke)
        }
        return list
    }
}
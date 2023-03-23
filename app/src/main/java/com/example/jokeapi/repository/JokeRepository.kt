package com.example.jokeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jokeapi.api.JokeDB
import com.example.jokeapi.api.JokeService
import com.example.jokeapi.api.data.JokeResponse

class JokeRepository(
    private val database: JokeDB,
    private val api: JokeService
) {
    private val pagingConfig = PagingConfig(10, 10, false, 20)
    fun fetchDataFromDatabase(): LiveData<PagingData<JokeResponse>> {
        return Pager(config = pagingConfig, 0) {
            database.getJokeDAO().getRandomJokes()
        }.flow.asLiveData()

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
package com.example.jokeapi.api

import androidx.paging.PagingSource
import androidx.room.*
import com.example.jokeapi.api.data.JokeResponse

@Dao
interface
JokeDAO  {
    @Query(
        "select * from Joke_DB"
    )
    fun getRandomJokes() : PagingSource<Int,JokeResponse>
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertJoke(joke: JokeResponse)
}
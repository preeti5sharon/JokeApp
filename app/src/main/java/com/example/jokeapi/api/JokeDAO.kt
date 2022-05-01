package com.example.jokeapi.api

import androidx.room.*
import com.example.jokeapi.api.data.JokeResponse

@Dao
interface JokeDAO  {
    @Query(
        "select * from Joke_DB"
    )
    suspend fun getRandomJokes() : List<JokeResponse>
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertJoke(joke: JokeResponse)
}
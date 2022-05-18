package com.example.jokeapi.api

import com.example.jokeapi.api.data.JokeResponse
import retrofit2.http.GET

interface JokeService {
    @GET(
        "/jokes/random"
    )
    suspend fun getRandomJokes(): JokeResponse
}
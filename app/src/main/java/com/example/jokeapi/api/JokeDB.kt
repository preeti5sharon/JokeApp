package com.example.jokeapi.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokeapi.api.data.JokeResponse

@Database(
    entities = [JokeResponse::class],
    version = 1
)
abstract class JokeDB : RoomDatabase() {
    abstract fun getJokeDAO(): JokeDAO
}
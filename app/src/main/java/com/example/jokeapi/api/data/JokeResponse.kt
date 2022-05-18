package com.example.jokeapi.api.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "Joke_DB")

@JsonClass(generateAdapter = true)
data class JokeResponse(
//    val categories: List<Any>? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "icon_url")
    val iconUrl: String? = null,
    @PrimaryKey
    val id: String = "",
    @Json(name = "updated_at")
    val updatedAt: String? = null,
    val url: String? = null,
    val value: String? = null
)
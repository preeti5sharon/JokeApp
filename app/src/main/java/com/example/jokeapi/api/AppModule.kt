package com.example.jokeapi.api

import android.content.Context
import androidx.room.Room
import com.example.jokeapi.repository.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideJokeAPI(retrofit: Retrofit) = retrofit.create(JokeService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, JokeDB::class.java, "app_db").build()

    @Singleton
    @Provides
    fun provideRepository(api: JokeService, database: JokeDB) = JokeRepository(
        database, api
    )
}

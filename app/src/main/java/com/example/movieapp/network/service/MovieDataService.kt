package com.example.movieapp.network.service

import com.example.movieapp.network.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface MovieDataService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey:String) : Response<Result>

}
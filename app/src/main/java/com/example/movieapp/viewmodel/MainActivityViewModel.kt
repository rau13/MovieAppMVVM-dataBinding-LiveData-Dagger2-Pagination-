package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movieapp.network.model.Movie
import com.example.movieapp.network.model.MovieRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository(application)

    //LiveData
    suspend fun getAllMovies() : LiveData<List<Movie>>{
        return movieRepository.getMutableLiveData()
    }
}
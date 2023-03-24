package com.example.movieapp.network.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.R
import com.example.movieapp.network.model.Result
import com.example.movieapp.network.service.MovieDataService
import com.example.movieapp.network.service.RetrofitInstance

class MovieRepository(var application: Application){
    private var movies = ArrayList<Movie>()
    private var mutableLiveData = MutableLiveData<List<Movie>>()

    suspend fun getMutableLiveData() :MutableLiveData<List<Movie>> {
        val movieDataService = RetrofitInstance().getService()
        val call = movieDataService.getMovies(application.applicationContext.getString(R.string.api_key))
        if(call.isSuccessful){
            val result = call.body()
            if(result?.results != null){
                movies = result.results as ArrayList<Movie>
                mutableLiveData.value = movies
            }
        }else{
            Log.d("MYLOG", "Someting wrong with call")
        }
        return mutableLiveData
    }
}
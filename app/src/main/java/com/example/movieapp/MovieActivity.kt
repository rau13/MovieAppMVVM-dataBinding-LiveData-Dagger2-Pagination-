package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.ActivityMovieBinding
import com.example.movieapp.network.model.Movie

class MovieActivity : AppCompatActivity() {
    private var movie: Movie? = null
    lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val i = intent
        if (i != null) {
            movie = i.getSerializableExtra("movie") as Movie?
            binding.setMovie(movie)
            supportActionBar!!.title = movie!!.title
        }
    }
}
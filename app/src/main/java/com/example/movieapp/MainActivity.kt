package com.example.movieapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.network.model.Movie
import com.example.movieapp.network.service.MovieDataService
import com.example.movieapp.viewmodel.MainActivityViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var movies: ArrayList<Movie?>? = null
    private var recyclerView: RecyclerView? = null
    private var movieAdapter: MovieAdapter? = null
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var mainActivityViewModel: MainActivityViewModel? = null
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Movie Pro App"
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launch {
            getPopularMovies()
        }

        swipeRefreshLayout = binding.swipeLayout
        swipeRefreshLayout!!.setOnRefreshListener {
            lifecycleScope.launch {
                getPopularMovies()
            }

        }
        swipeRefreshLayout!!.setColorSchemeResources(R.color.teal_200)

    }

    private suspend fun getPopularMovies() {
        mainActivityViewModel?.getAllMovies()?.observe(this,
            Observer<List<Any?>?> { moviesFromLiveData ->
                movies = moviesFromLiveData as ArrayList<Movie?>
                ShowOnRecyclerView()
            })
    }

    private fun ShowOnRecyclerView() {
        recyclerView = binding?.rvMovies
        movieAdapter = MovieAdapter(this, movies)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = movieAdapter
        movieAdapter!!.notifyDataSetChanged()
    }
}
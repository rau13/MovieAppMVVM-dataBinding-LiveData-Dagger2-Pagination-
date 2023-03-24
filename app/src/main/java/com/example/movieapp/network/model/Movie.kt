package com.example.movieapp.network.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import com.bumptech.glide.Glide


data class Movie(
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: List<Int>,
    var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
) : java.io.Serializable, BaseObservable(){

    companion object{
        @BindingAdapter("posterPath")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageURL: String) {

            // BASIC IMAGE URL
            // https://image.tmdb.org/t/p/original/[poster_path]
            val imagePath = "https://image.tmdb.org/t/p/w500$imageURL"
            Glide.with(imageView.context)
                .load(imagePath)
                .into(imageView)
        }
    }

    @Bindable
    fun getPosterPath(): String{
        return poster_path
    }

    fun setPosterPath(posterPath: String){
        this.poster_path = posterPath
        notifyPropertyChanged(BR.posterPath)
    }


}
package com.example.yuvirdhajetpacksubmission1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yuvirdhajetpacksubmission1.movie.MovieViewModel
import com.example.yuvirdhajetpacksubmission1.tvshow.TvShowViewModel
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.detail.DetailViewModel
import com.example.yuvirdhajetpacksubmission1.di.Injection
import com.example.yuvirdhajetpacksubmission1.favorite.movie.FavoriteMovieViewModel
import com.example.yuvirdhajetpacksubmission1.favorite.tvshow.FavoriteTvShowViewModel

class ViewModelFactory private constructor(private val mRepo: DataRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mRepo) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mRepo) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mRepo) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(mRepo) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(mRepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}
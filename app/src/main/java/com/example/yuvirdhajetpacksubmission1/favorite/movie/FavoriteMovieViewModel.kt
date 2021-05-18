package com.example.yuvirdhajetpacksubmission1.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity

class FavoriteMovieViewModel(private val repo: DataRepository) : ViewModel() {
    fun getFavMovie(): LiveData<PagedList<DataMovieEntity>> {
        return repo.getFavoriteMovie()
    }

    fun setMovieFavorited(movies: DataMovieEntity) {
        val newState = !movies.isFav
        repo.setMovieIsFav(movies, newState)
    }
}
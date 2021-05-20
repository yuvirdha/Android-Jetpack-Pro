package com.example.yuvirdhajetpacksubmission1.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.vo.Resource

class DetailViewModel(private val repository: DataRepository) : ViewModel() {

    val movieTitle = MutableLiveData<String>()
    val tvTitle = MutableLiveData<String>()

    fun setContentByMovieTitle(movieTitle: String) {
        this.movieTitle.value = movieTitle
    }

    fun setContentByTvShowTitle(tvTitle: String) {
        this.tvTitle.value = tvTitle
    }

    // Metode  Transformations.switchMap digunakan untuk mengambil data setiap kali courseId-nya berubah.

    var dataDetailEntityMovie: LiveData<Resource<DataMovieDetailEntity>> = Transformations.switchMap(movieTitle) { mTitleMovie ->
        repository.generateDetailMovies(mTitleMovie)
    }

    var dataDetailEntityTvShow: LiveData<Resource<DataTvShowDetailEntity>> = Transformations.switchMap(tvTitle) { mTitleTvShow ->
        repository.generateDetailTvShows(mTitleTvShow)
    }

    fun setMovieIsFav() {
        val dataResource = dataDetailEntityMovie.value
        if (dataResource != null) {
            val entity = dataResource.data
            if (entity != null) {
                val data = entity.mDataMovie
                val newState = !data.isFav
                repository.setMovieIsFav(data, newState)
            }
        }
    }

    fun setTvShowIsFav() {
        val dataResource = dataDetailEntityTvShow.value
        if (dataResource != null) {
            val entity = dataResource.data
            if (entity != null) {
                val data = entity.mDataTvShow
                val newState = !data.isFav
                repository.setTvShowIsFav(data, newState)
            }
        }
    }
}
package com.example.yuvirdhajetpacksubmission1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.vo.Resource

interface DataSource {

    fun generateDummyMovies(): LiveData<Resource<PagedList<DataMovieEntity>>>
    fun generateDummyTvShows(): LiveData<Resource<PagedList<DataTvShowEntity>>>

    fun generateDetailMovies(title: String): LiveData<Resource<DataMovieDetailEntity>>
    fun generateDetailTvShows(title: String): LiveData<Resource<DataTvShowDetailEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<DataMovieEntity>>
    fun getFavoriteTvShow(): LiveData<PagedList<DataTvShowEntity>>

    fun setMovieIsFav(movies: DataMovieEntity, state: Boolean)
    fun setTvShowIsFav(tvShows: DataTvShowEntity, state: Boolean)

}
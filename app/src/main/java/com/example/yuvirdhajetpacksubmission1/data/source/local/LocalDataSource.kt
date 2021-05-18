package com.example.yuvirdhajetpacksubmission1.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.room.DataDao

class LocalDataSource private constructor(private val mDataDao: DataDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dataDao: DataDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dataDao)
    }

    fun generateDummyMovies(): DataSource.Factory<Int, DataMovieEntity> = mDataDao.getMovies()

    fun generateDummyTvShows(): DataSource.Factory<Int, DataTvShowEntity> = mDataDao.getTvShows()

    fun getFavoriteMovie(): DataSource.Factory<Int, DataMovieEntity> = mDataDao.getFavoriteMovie()

    fun getFavoriteTvShow(): DataSource.Factory<Int, DataTvShowEntity> = mDataDao.getFavoriteTvShow()

    fun generateDetailMovie(movieId: String): LiveData<DataMovieDetailEntity> =
        mDataDao.getMoviesDetail(movieId)

    fun generateDetailTvShow(tvShowId: String): LiveData<DataTvShowDetailEntity> =
        mDataDao.getTvShowDetail(tvShowId)

    fun insertMovies(movie: List<DataMovieEntity>) = mDataDao.insertMovies(movie)

    fun insertTvShows(tvshow: List<DataTvShowEntity>) = mDataDao.insertTvShows(tvshow)

    fun setMovieIsFav(movie: DataMovieEntity, newState: Boolean) {
        movie.isFav = newState
        mDataDao.updateMovies(movie)
    }

    fun setTvShowIsFav(tvshow: DataTvShowEntity, newState: Boolean) {
        tvshow.isFav = newState
        mDataDao.updateTvShows(tvshow)
    }
}
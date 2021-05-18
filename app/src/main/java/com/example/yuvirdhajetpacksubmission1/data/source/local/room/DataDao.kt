package com.example.yuvirdhajetpacksubmission1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity

@Dao
interface DataDao {

    @Query("SELECT * FROM data_entities_movies")
    fun getMovies(): DataSource.Factory<Int, DataMovieEntity>

    @Query("SELECT * FROM data_entities_tv_show")
    fun getTvShows(): DataSource.Factory<Int, DataTvShowEntity>

    @Query("SELECT * FROM data_entities_movies where isFav = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, DataMovieEntity>

    @Query("SELECT * FROM data_entities_tv_show where isFav = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, DataTvShowEntity>

    @Transaction
    @Query("SELECT * FROM data_entities_movies WHERE title = :movieId")
    fun getMoviesDetail(movieId: String): LiveData<DataMovieDetailEntity>

    @Transaction
    @Query("SELECT * FROM data_entities_tv_show WHERE title = :tvShowId")
    fun getTvShowDetail(tvShowId: String): LiveData<DataTvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<DataMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShow: List<DataTvShowEntity>)

    @Update
    fun updateMovies(movie: DataMovieEntity)

    @Update
    fun updateTvShows(tvShow: DataTvShowEntity)
}
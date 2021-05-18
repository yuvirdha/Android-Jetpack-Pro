package com.example.yuvirdhajetpacksubmission1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.remote.RemoteDataSource
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataMovieEntityResponse

class FakeDataRepository (private val remoteDataSource: RemoteDataSource) : DataSource {

    override fun generateDummyMovies(): LiveData<List<DataMovieEntity>> {
        val movieResults = MutableLiveData<List<DataMovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(dataMovieEntityResponse: List<DataMovieEntityResponse>) {
                val movieList = ArrayList<DataMovieEntity>()
                for (response in dataMovieEntityResponse) {
                    val data = DataMovieEntity(response.title,
                            response.year,
                            response.genre,
                            response.detail,
                            response.poster,
                    false)
                    movieList.add(data)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun generateDummyTvShows(): LiveData<List<DataMovieEntity>> {
        val tvResults = MutableLiveData<List<DataMovieEntity>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(dataMovieEntityResponse: List<DataMovieEntityResponse>) {
                val tvList = ArrayList<DataMovieEntity>()
                for (response in dataMovieEntityResponse) {
                    val data = DataMovieEntity(response.title,
                            response.year,
                            response.genre,
                            response.detail,
                            response.poster,
                    false)
                    tvList.add(data)
                }
                tvResults.postValue(tvList)
            }
        })

        return tvResults
    }

    override fun generateDetailMovies(title: String): LiveData<DataMovieEntity> {
        val movieResults = MutableLiveData<DataMovieEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(dataMovieEntityResponse: List<DataMovieEntityResponse>) {

                lateinit var movieList: DataMovieEntity

                for (response in dataMovieEntityResponse) {
                    if (response.title == title) {
                        movieList = DataMovieEntity(response.title,
                                response.year,
                                response.genre,
                                response.detail,
                                response.poster,
                            false)
                    }
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun generateDetailTvShows(title: String): LiveData<DataMovieEntity> {
        val tvResults = MutableLiveData<DataMovieEntity>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(dataMovieEntityResponse: List<DataMovieEntityResponse>) {

                lateinit var tvList: DataMovieEntity

                for (response in dataMovieEntityResponse) {
                    if (response.title == title) {
                        tvList = DataMovieEntity(response.title,
                                response.year,
                                response.genre,
                                response.detail,
                                response.poster,
                            false)
                    }
                }
                tvResults.postValue(tvList)
            }
        })
        return tvResults
    }


    override fun getFavoriteMovie(): LiveData<List<DataMovieEntity>> {
        val favMovieResults = MutableLiveData<List<DataMovieEntity>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(dataMovieEntityResponse: List<DataMovieEntityResponse>) {

                val favMovieList = java.util.ArrayList<DataMovieEntity>()

                for (response in dataMovieEntityResponse) {
                    val favMovie = DataMovieEntity(response.title,
                        response.year,
                        response.genre,
                        response.detail,
                        response.poster,
                        false)
                    favMovieList.add(favMovie)
                }
                favMovieResults.postValue(favMovieList)
            }
        })
        return favMovieResults
    }


    override fun getFavoriteTvShow(): LiveData<List<DataMovieEntity>> {
        val favTvShowResults = MutableLiveData<List<DataMovieEntity>>()

        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(dataMovieEntityResponse: List<DataMovieEntityResponse>) {

                val favTvShowsList = java.util.ArrayList<DataMovieEntity>()

                for (response in dataMovieEntityResponse) {
                    val favTvShow = DataMovieEntity(response.title,
                        response.year,
                        response.genre,
                        response.detail,
                        response.poster,
                        false)
                    favTvShowsList.add(favTvShow)
                }
                favTvShowResults.postValue(favTvShowsList)
            }
        })
        return favTvShowResults
    }
}
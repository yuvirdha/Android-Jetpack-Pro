package com.example.yuvirdhajetpacksubmission1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.yuvirdhajetpacksubmission1.data.source.local.LocalDataSource
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.data.source.remote.RemoteDataSource
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.ApiResponse
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataMovieEntityResponse
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataTvShowEntityResponse
import com.example.yuvirdhajetpacksubmission1.utils.AppExecutors
import com.example.yuvirdhajetpacksubmission1.vo.Resource

class FakeDataRepository constructor(private val remoteDataSource: RemoteDataSource,
                                             private val localDataSource: LocalDataSource,
                                             private val appExecutors: AppExecutors) : DataSource {

    override fun generateDummyMovies(): LiveData<Resource<PagedList<DataMovieEntity>>> {
        return object : NetworkBoundResource<PagedList<DataMovieEntity>, List<DataMovieEntityResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<DataMovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.generateDummyMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<DataMovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DataMovieEntityResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<DataMovieEntityResponse>) {
                val movieList = ArrayList<DataMovieEntity>()
                for (response in data) {
                    val movie = DataMovieEntity(
                        response.title,
                        response.year,
                        response.genre,
                        response.detail,
                        response.poster,
                        false
                    )
                    println("ini judul" + movie.title)
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
                Log.e("DataRepository", "saveCallResult")
            }
        }.asLiveData()
    }

    override fun generateDummyTvShows(): LiveData<Resource<PagedList<DataTvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<DataTvShowEntity>, List<DataTvShowEntityResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<DataTvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.generateDummyTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<DataTvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DataTvShowEntityResponse>>> =
                remoteDataSource.getAllTvShows()

            public override fun saveCallResult(data: List<DataTvShowEntityResponse>) {
                val tvShowsList = ArrayList<DataTvShowEntity>()
                for (response in data) {
                    val tvShow = DataTvShowEntity(
                        response.title,
                        response.year,
                        response.genre,
                        response.detail,
                        response.poster,
                        false)
                    tvShowsList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowsList)
            }
        }.asLiveData()
    }

    override fun generateDetailMovies(title: String): LiveData<Resource<DataMovieDetailEntity>> {

        return object : NetworkBoundResource<DataMovieDetailEntity, List<DataMovieEntityResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<DataMovieDetailEntity> =
                localDataSource.generateDetailMovie(title)

            override fun shouldFetch(data: DataMovieDetailEntity?): Boolean =
                data?.mDataMovieDetail == null || data.mDataMovieDetail.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataMovieEntityResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<DataMovieEntityResponse>) {

                val detailMovieList = ArrayList<DataMovieEntity>()

                for (response in data) {
                    val movieList = DataMovieEntity(
                        response.title,
                        response.year,
                        response.genre,
                        response.detail,
                        response.poster,
                        false
                    )
                    detailMovieList.add(movieList)
                }
                localDataSource.insertMovies(detailMovieList)
            }
        }.asLiveData()
    }

    override fun generateDetailTvShows(title: String): LiveData<Resource<DataTvShowDetailEntity>> {

        return object : NetworkBoundResource<DataTvShowDetailEntity, List<DataTvShowEntityResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<DataTvShowDetailEntity> =
                localDataSource.generateDetailTvShow(title)

            override fun shouldFetch(data: DataTvShowDetailEntity?): Boolean =
                data?.mDataTvShowDetail == null || data.mDataTvShowDetail.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataTvShowEntityResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<DataTvShowEntityResponse>) {

                val detailTvShowList = ArrayList<DataTvShowEntity>()

                for (response in data) {
                    val tvShowList = DataTvShowEntity(
                        response.title,
                        response.year,
                        response.genre,
                        response.detail,
                        response.poster,
                        false
                    )
                    detailTvShowList.add(tvShowList)
                }
                localDataSource.insertTvShows(detailTvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<DataMovieEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<DataTvShowEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setMovieIsFav(movies: DataMovieEntity, state: Boolean) =
        appExecutors.diskIO().execute{
            localDataSource.setMovieIsFav(movies, state)
        }

    override fun setTvShowIsFav(tvShows: DataTvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute{
            localDataSource.setTvShowIsFav(tvShows, state)
        }
}
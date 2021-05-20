package com.example.yuvirdhajetpacksubmission1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.yuvirdhajetpacksubmission1.data.source.local.LocalDataSource
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.data.source.remote.RemoteDataSource
import com.example.yuvirdhajetpacksubmission1.utils.AppExecutors
import com.example.yuvirdhajetpacksubmission1.utils.LiveDataTestUtil
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import com.example.yuvirdhajetpacksubmission1.utils.PagedListUtil
import com.example.yuvirdhajetpacksubmission1.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val dataRepository = FakeDataRepository(remote, local, appExecutors)

    private val movieResponses = MyDummy.generateRemoteDummyMovies()
    private val movieTitle = movieResponses[0].title
    private val tvResponses = MyDummy.generateRemoteDummyTvShows()
    private val tvTitle = tvResponses[0].title

    @Test
    fun generateDummyMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataMovieEntity>
        Mockito.`when`(local.generateDummyMovies()).thenReturn(dataSourceFactory)
        dataRepository.generateDummyMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(MyDummy.generateDummyMovies()))
        verify(local).generateDummyMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun generateDummyTvShows() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataTvShowEntity>
        Mockito.`when`(local.generateDummyTvShows()).thenReturn(dataSourceFactory)
        dataRepository.generateDummyTvShows()

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(MyDummy.generateDummyTvShows()))
        verify(local).generateDummyTvShows()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun generateDetailMovies() {
        val dummyEntity = MutableLiveData<DataMovieDetailEntity>()
        dummyEntity.value = MyDummy.generateDetailFavMovies(MyDummy.generateDummyMovies()[0], false)
        Mockito.`when`<LiveData<DataMovieDetailEntity>>(local.generateDetailMovie(movieTitle)).thenReturn(dummyEntity)

        val movieEntities = LiveDataTestUtil.getValue(dataRepository.generateDetailMovies(movieTitle))
        verify(local).generateDetailMovie(movieTitle)
        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.mDataMovie?.title)
        assertEquals(movieResponses[0].title, movieEntities.data?.mDataMovie?.title)
    }

    @Test
    fun generateDetailTvShows() {
        val dummyEntity = MutableLiveData<DataTvShowDetailEntity>()
        dummyEntity.value = MyDummy.generateDetailFavTvShows(MyDummy.generateDummyTvShows()[0], false)
        Mockito.`when`<LiveData<DataTvShowDetailEntity>>(local.generateDetailTvShow(tvTitle)).thenReturn(dummyEntity)

        val tvEntities = LiveDataTestUtil.getValue(dataRepository.generateDetailTvShows(tvTitle))
        verify(local).generateDetailTvShow(tvTitle)
        assertNotNull(tvEntities.data)
        assertNotNull(tvEntities.data?.mDataTvShow?.title)
        assertEquals(tvResponses[0].title, tvEntities.data?.mDataTvShow?.title)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataMovieEntity>
        Mockito.`when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        dataRepository.getFavoriteMovie()

        val favMovieEntities = Resource.success(PagedListUtil.mockPagedList(MyDummy.generateDummyMovies()))
        verify(local).getFavoriteMovie()
        assertNotNull(favMovieEntities)
        assertEquals(movieResponses.size.toLong(), favMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataTvShowEntity>
        Mockito.`when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        dataRepository.getFavoriteTvShow()

        val favTvShowEntities = Resource.success(PagedListUtil.mockPagedList(MyDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShow()
        assertNotNull(favTvShowEntities)
        assertEquals(tvResponses.size.toLong(), favTvShowEntities.data?.size?.toLong())
    }
}
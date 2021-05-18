package com.example.yuvirdhajetpacksubmission1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yuvirdhajetpacksubmission1.data.source.remote.RemoteDataSource
import com.example.yuvirdhajetpacksubmission1.utils.LiveDataTestUtil
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
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
    private val repo = FakeDataRepository(remote)

    private val movieResponses = MyDummy.generateRemoteDummyMovies()
    private val movieTitle = movieResponses[0].title

    private val tvResponses = MyDummy.generateRemoteDummyTvShows()
    private val tvTitle = tvResponses[0].title

    @Test
    fun generateDummyMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())

        val dataEntity = LiveDataTestUtil.getValue(repo.generateDummyMovies())
        verify(remote).getAllMovies(any())

        assertNotNull(dataEntity)
        assertEquals(movieResponses.size.toLong(), dataEntity.size.toLong())
    }

    @Test
    fun generateDummyTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowsReceived(tvResponses)
            null
        }.`when`(remote).getAllTvShows(any())

        val dataEntity = LiveDataTestUtil.getValue(repo.generateDummyTvShows())
        verify(remote).getAllTvShows(any())

        assertNotNull(dataEntity)
        assertEquals(tvResponses.size.toLong(), dataEntity.size.toLong())
    }

    @Test
    fun generateDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())

        val dataEntity = LiveDataTestUtil.getValue(repo.generateDetailMovies(movieTitle))

        verify(remote).getAllMovies(any())

        assertNotNull(dataEntity)
        assertNotNull(dataEntity.title)
        assertEquals(movieResponses[0].title, dataEntity.title)
    }

    @Test
    fun generateDetailTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                    .onAllTvShowsReceived(tvResponses)
            null
        }.`when`(remote).getAllTvShows(any())

        val dataEntity = LiveDataTestUtil.getValue(repo.generateDetailTvShows(tvTitle))

        verify(remote).getAllTvShows(any())

        assertNotNull(dataEntity)
        assertNotNull(dataEntity.title)
        assertEquals(tvResponses[0].title, dataEntity.title)
    }
}
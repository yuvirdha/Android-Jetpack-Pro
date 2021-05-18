package com.example.yuvirdhajetpacksubmission1.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: DataRepository

    @Mock
    private lateinit var observer: Observer<List<DataMovieEntity>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(repo)
    }

    @Test
    fun getMovies() {
        val dummyMovie = MyDummy.generateDummyMovies()
        val movies = MutableLiveData<List<DataMovieEntity>>()
        movies.value = dummyMovie

        `when`(repo.generateDummyMovies()).thenReturn(movies)
        val dataEntity = viewModel.getMovieData().value
        Mockito.verify(repo).generateDummyMovies()
        assertNotNull(dataEntity)
        assertEquals(10, dataEntity?.size)

        viewModel.getMovieData().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}
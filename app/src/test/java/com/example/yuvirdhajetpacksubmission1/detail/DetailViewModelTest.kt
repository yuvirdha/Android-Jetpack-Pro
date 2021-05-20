package com.example.yuvirdhajetpacksubmission1.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieDetailEntity
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowDetailEntity
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import com.example.yuvirdhajetpacksubmission1.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //deklarasi model
    private lateinit var viewModel: DetailViewModel

    private val detailMovie = MyDummy.generateDummyMovies()[0]
    private val detailTvShow = MyDummy.generateDummyTvShows()[0]

    // inisialisasi title
    private val movieTitle = detailMovie.title
    private val tvTitle = detailTvShow.title

    @Mock
    private lateinit var repo: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<DataMovieDetailEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<DataTvShowDetailEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repo)
        viewModel.setContentByMovieTitle(movieTitle)
        viewModel.setContentByTvShowTitle(tvTitle)
    }

    @Test
    fun `setContentByMovieTitle should be success`() {
        val expected = MutableLiveData<Resource<DataMovieDetailEntity>>()
        expected.value = Resource.success(MyDummy.generateDetailFavMovies(detailMovie, true))

        Mockito.`when`(repo.generateDetailMovies(movieTitle)).thenReturn(expected)

        viewModel.dataDetailEntityMovie.observeForever(movieObserver)

        Mockito.verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.dataDetailEntityMovie.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setContentByTvShowTitle should be success`() {
        val expected = MutableLiveData<Resource<DataTvShowDetailEntity>>()
        expected.value = Resource.success(MyDummy.generateDetailFavTvShows(detailTvShow, true))

        Mockito.`when`(repo.generateDetailTvShows(tvTitle)).thenReturn(expected)

        viewModel.dataDetailEntityTvShow.observeForever(tvShowObserver)

        Mockito.verify(tvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.dataDetailEntityTvShow.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setMovieIsFav should be success trigger dataDetailEntityMovie observer`() {
        val expected = MutableLiveData<Resource<DataMovieDetailEntity>>()
        expected.value = Resource.success(MyDummy.generateDetailFavMovies(detailMovie, true))

        Mockito.`when`(repo.generateDetailMovies(movieTitle)).thenReturn(expected)

        viewModel.setMovieIsFav()
        viewModel.dataDetailEntityMovie.observeForever(movieObserver)

        Mockito.verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.dataDetailEntityMovie.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setTvShowIsFav should be success trigger dataDetailEntityTvShow observer`() {
        val expected = MutableLiveData<Resource<DataTvShowDetailEntity>>()
        expected.value = Resource.success(MyDummy.generateDetailFavTvShows(detailTvShow, true))

        Mockito.`when`(repo.generateDetailTvShows(tvTitle)).thenReturn(expected)

        viewModel.setTvShowIsFav()
        viewModel.dataDetailEntityTvShow.observeForever(tvShowObserver)

        Mockito.verify(tvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.dataDetailEntityTvShow.value

        assertEquals(expectedValue, actualValue)
    }

}
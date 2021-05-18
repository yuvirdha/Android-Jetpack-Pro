package com.example.yuvirdhajetpacksubmission1.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{

    //deklarasi model
    private lateinit var viewModel: DetailViewModel

    private val detailMovie = MyDummy.generateDummyMovies()[0]
    private val detailTvShow = MyDummy.generateDummyTvShows()[0]

    // inisialisasi title
    private val title = detailMovie.title + detailTvShow

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repo: DataRepository
    @Mock
    private lateinit var movieObserver: Observer<DataMovieEntity>
    @Mock
    private lateinit var tvShowObserver: Observer<DataMovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repo)
        viewModel.setContentByTitle(title)
    }

    @Test
    fun testMovieDetail() {
        val liveDataMovie = MutableLiveData<DataMovieEntity>()
        liveDataMovie.value = detailMovie

        Mockito.`when`(repo.generateDetailMovies(title)).thenReturn(liveDataMovie)
        val dataEntity = viewModel.getMoviesDetail().value as DataMovieEntity

        Mockito.verify(repo).generateDetailMovies(title)

        assertNotNull(dataEntity)
        assertEquals(detailMovie.title, dataEntity.title)
        assertEquals(detailMovie.year, dataEntity.year)
        assertEquals(detailMovie.genre, dataEntity.genre)
        assertEquals(detailMovie.detail, dataEntity.detail)
        assertEquals(detailMovie.poster, dataEntity.poster)

        viewModel.getMoviesDetail().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(detailMovie)
    }

    @Test
    fun testTvShowDetail() {
        val liveDataTvShow = MutableLiveData<DataMovieEntity>()
        liveDataTvShow.value = detailTvShow

        Mockito.`when`(repo.generateDetailTvShows(title)).thenReturn(liveDataTvShow)
        val dataEntity = viewModel.getTvShowsDetail().value as DataMovieEntity

        Mockito.verify(repo).generateDetailTvShows(title)

        assertNotNull(dataEntity)
        assertEquals(detailTvShow.title, dataEntity.title)
        assertEquals(detailTvShow.year, dataEntity.year)
        assertEquals(detailTvShow.genre, dataEntity.genre)
        assertEquals(detailTvShow.detail, dataEntity.detail)
        assertEquals(detailTvShow.poster, dataEntity.poster)

        viewModel.getTvShowsDetail().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(detailTvShow)
    }
}
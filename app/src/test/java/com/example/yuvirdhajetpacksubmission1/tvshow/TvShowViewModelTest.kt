package com.example.yuvirdhajetpacksubmission1.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
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
class TvShowViewModelTest{

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repo: DataRepository
    @Mock
    private lateinit var observer: Observer<List<DataMovieEntity>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(repo)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = MyDummy.generateDummyTvShows()
        val tvShows = MutableLiveData<List<DataMovieEntity>>()
        tvShows.value = dummyTvShow

        `when`(repo.generateDummyTvShows()).thenReturn(tvShows)
        val dataEntity = viewModel.getTvShowData().value
        verify(repo).generateDummyTvShows()
        assertNotNull(dataEntity)
        assertEquals(10, dataEntity?.size)

        viewModel.getTvShowData().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}
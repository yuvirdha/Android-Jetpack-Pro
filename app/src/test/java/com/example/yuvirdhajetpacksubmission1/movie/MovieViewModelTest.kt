package com.example.yuvirdhajetpacksubmission1.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import com.example.yuvirdhajetpacksubmission1.vo.Resource
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<DataMovieEntity>>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(repo)
    }

    @Test
    fun `getMovies should be success`() {
        val movies = PagedTestDataSources.snapshot(MyDummy.generateDummyMovies())
        val expected = MutableLiveData<Resource<PagedList<DataMovieEntity>>>()
        expected.value = Resource.success(movies)

        `when`(repo.generateDummyMovies()).thenReturn(expected)

        viewModel.getMovieData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovieData().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<DataMovieEntity>>>()
        expected.value = Resource.success(movies)

        `when`(repo.generateDummyMovies()).thenReturn(expected)

        viewModel.getMovieData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getMovieData().value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }


    @Test
    fun `getMovies should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<DataMovieEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(repo.generateDummyMovies()).thenReturn(expected)

        viewModel.getMovieData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getMovieData().value?.message
        assertEquals(expectedMessage, actualMessage)
    }


    class PagedTestDataSources private constructor(private val items: List<DataMovieEntity>) : PositionalDataSource<DataMovieEntity>() {
        companion object {
            fun snapshot(items: List<DataMovieEntity> = listOf()): PagedList<DataMovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<DataMovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<DataMovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}
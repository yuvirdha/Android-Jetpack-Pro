package com.example.yuvirdhajetpacksubmission1.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.yuvirdhajetpacksubmission1.data.DataRepository
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors


@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest{

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<DataMovieEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repo)
    }
    @Test
    fun `getFavMovie should be success`() {
        val expected = MutableLiveData<PagedList<DataMovieEntity>>()
        expected.value = PagedTestDataSources.snapshot(MyDummy.generateDummyMovies())

        Mockito.`when`(repo.getFavoriteMovie()).thenReturn(expected)

        viewModel.getFavMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavMovie().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavMovie should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<DataMovieEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(repo.getFavoriteMovie()).thenReturn(expected)

        viewModel.getFavMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavMovie().value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
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

    @Test
    fun setMovieFavorited(){
        val dummyFavMovie = MyDummy.generateDummyMovies()[0]
        viewModel.setMovieFavorited(dummyFavMovie)
        Mockito.verify(repo).setMovieIsFav(dummyFavMovie, !dummyFavMovie.isFav)
    }
}
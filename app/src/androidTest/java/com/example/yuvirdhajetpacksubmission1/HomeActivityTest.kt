package com.example.yuvirdhajetpacksubmission1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.yuvirdhajetpacksubmission1.utils.EspressoIdlingResource
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest{

    private val dummyMovies = MyDummy.generateDummyMovies()
    private val dummyTvShows = MyDummy.generateDummyTvShows()

//    @get:Rule
//    var myRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTabMovieTvShow() {

        //load tab movie
        Espresso.onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size)
            )

        //click tab tv show
        Espresso.onView(withText("TV SHOWS")).perform(ViewActions.click())

        //load tab tv show
        Espresso.onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size)
            )

        //back to tab movie
        Espresso.onView(withText("MOVIES")).perform(ViewActions.click())
    }

    @Test
    fun loadDetailMovie() {

        //click tab movie

        Espresso.onView(withText("MOVIES")).perform(ViewActions.click())

        //test scroll recyclerview movie

        Espresso.onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        Espresso.onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, ViewActions.click()))


        //load movie detail

        Espresso.onView(withId(R.id.tvTitle))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvYear))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvGenre))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvDetail))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvTitle))
            .check(matches(withText(dummyMovies[9].title)))

        Espresso.onView(withId(R.id.tvYear))
            .check(matches(withText(dummyMovies[9].year)))

        Espresso.onView(withId(R.id.tvGenre))
            .check(matches(withText(dummyMovies[9].genre)))

        Espresso.onView(withId(R.id.tvDetail))
            .check(matches(withText(dummyMovies[9].detail)))

        //back to home

        Espresso.pressBack()
    }

    @Test
    fun loadDetailTvShow() {

        //click tab tv show

        Espresso.onView(withText("TV SHOWS")).perform(ViewActions.click())

        //test scroll recyclerview tv show

        Espresso.onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        Espresso.onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    9,
                    ViewActions.click()
                )
            )

        //load detail tv show

        Espresso.onView(withId(R.id.tvTitle))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvYear))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvGenre))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvDetail))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvTitle))
            .check(matches(withText(dummyTvShows[9].title)))

        Espresso.onView(withId(R.id.tvYear))
            .check(matches(withText(dummyTvShows[9].year)))

        Espresso.onView(withId(R.id.tvGenre))
            .check(matches(withText(dummyTvShows[9].genre)))

        Espresso.onView(withId(R.id.tvDetail))
            .check(matches(withText(dummyTvShows[9].detail)))

        // back to home
        Espresso.pressBack()
    }
}
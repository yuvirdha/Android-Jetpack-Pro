package com.example.yuvirdhajetpacksubmission1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.yuvirdhajetpacksubmission1.utils.EspressoIdlingResource
import com.example.yuvirdhajetpacksubmission1.utils.MyDummy
import org.hamcrest.CoreMatchers.allOf
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
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size)
            )

        //click tab tv show
        onView(withText("TV SHOWS")).perform(click())

        //load tab tv show
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size)
            )

        //back to tab movie
        onView(withText("MOVIES")).perform(click())
    }

    @Test
    fun loadDetailMovie() {

        //click tab movie

        onView(withText("MOVIES")).perform(click())

        //test scroll recyclerview movie

        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))


        //load movie detail

        onView(withId(R.id.tvTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvYear))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvGenre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvDetail))
            .check(matches(isDisplayed()))

        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvTitle))
            .check(matches(withText(dummyMovies[3].title)))

        onView(withId(R.id.tvYear))
            .check(matches(withText(dummyMovies[3].year)))

        onView(withId(R.id.tvGenre))
            .check(matches(withText(dummyMovies[3].genre)))

        onView(withId(R.id.tvDetail))
            .check(matches(withText(dummyMovies[3].detail)))

        //back to home

        Espresso.pressBack()
    }

    @Test
    fun loadDetailTvShow() {

        //click tab tv show

        onView(withText("TV SHOWS")).perform(click())

        //test scroll recyclerview tv show

        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click()
                )
            )

        //load detail tv show

        onView(withId(R.id.tvTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvYear))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvGenre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvDetail))
            .check(matches(isDisplayed()))

        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvTitle))
            .check(matches(withText(dummyTvShows[3].title)))

        onView(withId(R.id.tvYear))
            .check(matches(withText(dummyTvShows[3].year)))

        onView(withId(R.id.tvGenre))
            .check(matches(withText(dummyTvShows[3].genre)))

        onView(withId(R.id.tvDetail))
            .check(matches(withText(dummyTvShows[3].detail)))

        // back to home
        Espresso.pressBack()
    }


    @Test
    fun loadFavMovies(){
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, doubleClick())
        )
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.favorite)).perform(click())
        onView(allOf(withId(R.id.rv_fav_movie), isDisplayed()))
        onView(allOf(withId(R.id.rv_fav_movie))).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, doubleClick()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadFavoriteTvShows(){
        onView(allOf(withId(R.id.rv_movie), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.rv_tv_shows), isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, doubleClick()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(allOf(withId(R.id.rv_fav_tv_show))).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
        onView(withId(R.id.rv_fav_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, doubleClick()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(pressBack())
    }
}
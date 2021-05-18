package com.example.yuvirdhajetpacksubmission1.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.yuvirdhajetpacksubmission1.R
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataTvShowEntity
import com.example.yuvirdhajetpacksubmission1.databinding.ActivityTvShowDetailBinding
import com.example.yuvirdhajetpacksubmission1.viewmodel.ViewModelFactory
import com.example.yuvirdhajetpacksubmission1.vo.Status

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // showing the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set viewmodel

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val title = extras.getString(EXTRA_TV_SHOW)
            if (title != null) {
                viewModel.setContentByTitle(title)
                viewModel.dataDetailEntityTvShow.observe(this, { tvShows ->
                    if (tvShows != null) {
                        when (tvShows.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (tvShows.data != null) {
                                binding.progressBar.visibility = View.GONE
                                getDummy(tvShows.data.mDataTvShow)
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "ERROR CAUGHT!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun getDummy(dataMovie: DataTvShowEntity) {

        //set title as title bar
        supportActionBar?.title = dataMovie.title

        //set binding
        binding.tvTitle.text = dataMovie.title
        binding.tvYear.text = dataMovie.year
        binding.tvDetail.text = dataMovie.detail
        binding.tvGenre.text = dataMovie.genre
        Glide.with(this)
                .load(dataMovie.poster)
                .apply(RequestOptions().override(100, 140))
                .into(binding.imgPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.dataDetailEntityTvShow.observe(this, { tvShows ->
            if (tvShows != null) {
                when (tvShows.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (tvShows.data != null) {
                        binding.progressBar.visibility = View.GONE
                        val state = tvShows.data.mDataTvShow.isFav
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "ERROR CAUGHT!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setTvShowIsFav()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }

    //set back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
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
import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.databinding.ActivityMovieDetailBinding
import com.example.yuvirdhajetpacksubmission1.viewmodel.ViewModelFactory
import com.example.yuvirdhajetpacksubmission1.vo.Status

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    companion object {
        const val EXTRA_TITLE = "extra_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // showing the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set viewmodel
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val title = extras.getString(EXTRA_TITLE)
            if (title != null) {
                viewModel.setContentByMovieTitle(title)
                viewModel.dataDetailEntityMovie.observe(this, { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (movies.data != null) {
                                binding.progressBar.visibility = View.GONE
                                getDummy(movies.data.mDataMovie)
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

    private fun getDummy(dataMovie: DataMovieEntity) {

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
        viewModel.dataDetailEntityMovie.observe(this, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movies.data != null) {
                        binding.progressBar.visibility = View.GONE
                        val state = movies.data.mDataMovie.isFav
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
            viewModel.setMovieIsFav()
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
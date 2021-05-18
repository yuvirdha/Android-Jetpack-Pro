package com.example.yuvirdhajetpacksubmission1.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yuvirdhajetpacksubmission1.R
import com.example.yuvirdhajetpacksubmission1.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPagerFav.adapter = adapter
        binding.tabsFav.setupWithViewPager(binding.viewPagerFav)

        // showing the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Favorites"
    }

    //set back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
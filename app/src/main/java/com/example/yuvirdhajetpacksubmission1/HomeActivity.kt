package com.example.yuvirdhajetpacksubmission1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.yuvirdhajetpacksubmission1.databinding.ActivityHomeBinding
import com.example.yuvirdhajetpacksubmission1.favorite.FavoriteActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)

        supportActionBar?.elevation = 0f
    }

    //create menu
    override fun onCreateOptionsMenu(main_menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, main_menu)
        return super.onCreateOptionsMenu(main_menu)
    }

    // create the options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite) {
            val intentFavorite = Intent(this, FavoriteActivity::class.java)
            startActivity(intentFavorite)
        }
        return super.onOptionsItemSelected(item)
    }
}
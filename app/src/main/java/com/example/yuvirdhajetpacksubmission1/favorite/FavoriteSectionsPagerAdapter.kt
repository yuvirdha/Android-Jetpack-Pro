package com.example.yuvirdhajetpacksubmission1.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.yuvirdhajetpacksubmission1.R
import com.example.yuvirdhajetpacksubmission1.favorite.movie.FavoriteMovieFragment
import com.example.yuvirdhajetpacksubmission1.favorite.tvshow.FavoriteTvShowFragment

class FavoriteSectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_fav_movie, R.string.tab_fav_tv_show)
    }

    override fun getItem(position: Int): Fragment {
        var fragments: Fragment? = null
        when (position) {
            0 -> fragments = FavoriteMovieFragment()
            1 -> fragments = FavoriteTvShowFragment()
        }
        return fragments as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}
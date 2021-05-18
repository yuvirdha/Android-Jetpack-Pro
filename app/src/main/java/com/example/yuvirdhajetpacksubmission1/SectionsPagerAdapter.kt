package com.example.yuvirdhajetpacksubmission1

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.yuvirdhajetpacksubmission1.movie.MovieFragment
import com.example.yuvirdhajetpacksubmission1.tvshow.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_movie, R.string.tab_tv_show)
    }

    override fun getItem(position: Int): Fragment {
        var fragments: Fragment? = null
        when (position) {
            0 -> fragments = MovieFragment()
            1 -> fragments = TvShowFragment()
        }
        return fragments as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}
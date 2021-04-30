package com.dicoding.azam.submission2_githubuser.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.azam.submission2_githubuser.R
import com.dicoding.azam.submission2_githubuser.fragment.FollowersFragment
import com.dicoding.azam.submission2_githubuser.fragment.FollowingFragment

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager, data: Bundle) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle = data

    companion object {
        @StringRes
        private val tabs_menu = intArrayOf(R.string.tab_1, R.string.tab_2)
    }

    override fun getCount(): Int {
        return tabs_menu.size
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabs_menu[position])
    }
}
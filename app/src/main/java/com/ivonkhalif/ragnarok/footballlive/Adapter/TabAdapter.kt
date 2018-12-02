package com.ivonkhalif.ragnarok.footballlive.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ivonkhalif.ragnarok.footballlive.FavoriteFragment
import com.ivonkhalif.ragnarok.footballlive.NextMatchFragment
import com.ivonkhalif.ragnarok.footballlive.PastMatchFragment

class TabAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val PAGE_COUNT = 3
    val tittleTab = arrayOf("Next Match", "Past Match", "Favorite Match")

    override fun getItem(position: Int): Fragment? {
        return when (position){
            0 -> {
                NextMatchFragment()
            }
            1 -> {
                PastMatchFragment()
            }
            2 ->{
                FavoriteFragment()
            } else ->{return null}
        }
    }

    override fun getCount(): Int {return PAGE_COUNT}

    override fun getPageTitle(position: Int): CharSequence? {
        return tittleTab[position]
    }
}
package org.ohmstheresistance.guessthelottery.adapters

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class NumbersViewPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()

    fun addFragments(fragment: Fragment, fragmentTitle: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(fragmentTitle)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }


    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList.get(position)
    }
}
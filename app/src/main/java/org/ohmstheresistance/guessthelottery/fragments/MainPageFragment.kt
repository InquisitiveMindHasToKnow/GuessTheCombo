package org.ohmstheresistance.guessthelottery.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import org.ohmstheresistance.guessthelottery.R
import org.ohmstheresistance.guessthelottery.adapters.GamesViewPagerAdapter
import org.ohmstheresistance.guessthelottery.databinding.MainPageFragmentBinding

class MainPageFragment : Fragment() {

    lateinit var mainPageFragmentBinding: MainPageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainPageFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_page_fragment, container, false)

        setUpViewPager(mainPageFragmentBinding.gamesViewPager)
        mainPageFragmentBinding.tabs.setupWithViewPager(mainPageFragmentBinding.gamesViewPager)

        return mainPageFragmentBinding.root
    }

    private fun setUpViewPager(viewPager: ViewPager?) {
        val gamesViewPagerAdapter = activity?.supportFragmentManager?.let {
            GamesViewPagerAdapter(
                it
            )
        }
        gamesViewPagerAdapter?.addFragments(NumbersFragment(), "Numbers")
        gamesViewPagerAdapter?.addFragments(Pick4Fragment(), "Pick 4")

        viewPager?.adapter = gamesViewPagerAdapter
    }

}
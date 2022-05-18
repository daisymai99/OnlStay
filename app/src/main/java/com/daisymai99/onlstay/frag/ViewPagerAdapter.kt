package com.daisymai99.onlstay.frag

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT

import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(val item :ArrayList<Fragment> ,fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return item.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FavorFragment()
            2 -> BookFragment()
            else -> AccountFragment()
        }

    }

}
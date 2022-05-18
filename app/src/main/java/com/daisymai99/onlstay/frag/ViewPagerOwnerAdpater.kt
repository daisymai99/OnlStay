package com.daisymai99.onlstay.frag

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerOwnerAdpater(val item :ArrayList<Fragment>, fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        return item.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            /*1 -> FavorFragment()*/
            1 -> OwnerFragment()
            else -> AccountFragment()
        }
    }
}
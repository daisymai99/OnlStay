package com.daisymai99.onlstay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.adapter.AdsAdapter
import com.daisymai99.onlstay.adapter.CardAdapter
import com.daisymai99.onlstay.adapter.Circle_Recycle_Adapter
import com.daisymai99.onlstay.adapter.RcmAdapter
import com.daisymai99.onlstay.databinding.ActivityOwnerMain2Binding
import com.daisymai99.onlstay.databinding.FragmentHomeBinding
import com.daisymai99.onlstay.frag.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class Owner_main : AppCompatActivity() {

    lateinit var binding: ActivityOwnerMain2Binding
    lateinit var bottomNav : BottomNavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var currentfragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_tag,
            HomeFragment()
        ).commit()



        binding.bottomNav.setOnItemSelectedListener  {

            when(it.itemId){
                R.id.ic_home -> {

                    currentfragment = HomeFragment()
                    binding.viewpager.currentItem=0
                }

                R.id.ic_favo -> {
                    currentfragment = FavorFragment()
                    binding.viewpager.currentItem=1
                }
                R.id.ic_acc -> {
                    currentfragment = AccountFragment()
                    binding.viewpager.currentItem=2
                }
            }


            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_tag,currentfragment).commit()
            true
        }

    }
}
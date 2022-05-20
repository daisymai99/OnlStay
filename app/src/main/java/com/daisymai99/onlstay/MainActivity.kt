package com.daisymai99.onlstay

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.adapter.AdsAdapter
import com.daisymai99.onlstay.adapter.CardAdapter
import com.daisymai99.onlstay.adapter.Circle_Recycle_Adapter
import com.daisymai99.onlstay.adapter.RcmAdapter
import com.daisymai99.onlstay.databinding.ActivityMainBinding
import com.daisymai99.onlstay.frag.*
import com.google.android.material.bottomnavigation.BottomNavigationView




class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding

    lateinit var bottomNav : BottomNavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var currentfragment : Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_tag,HomeFragment()).commit()



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
                R.id.ic_book -> {
                    currentfragment = BookFragment()
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



    override fun onBackPressed() {
        if(binding.viewpager.currentItem ==0){
            super.onBackPressed()
        }else{
            binding.viewpager.currentItem = binding.viewpager.currentItem -1
        }
    }
}
package com.daisymai99.onlstay.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.ActivityOwnerMain2Binding
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



        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container_view_tag,
            HomeFragment()
        ).commit()



        binding.bottomNav.setOnItemSelectedListener  {

            when(it.itemId){
                R.id.ic_home -> {

                    currentfragment = HomeFragment()
                    binding.viewpager.currentItem=0
                }

                R.id.ic_room -> {
                    currentfragment = OwnerFragment()
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
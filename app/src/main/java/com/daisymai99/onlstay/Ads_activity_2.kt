package com.daisymai99.onlstay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daisymai99.onlstay.databinding.ActivityAds2Binding

class Ads_activity_2 : AppCompatActivity() {

    lateinit var binding: ActivityAds2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAds2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
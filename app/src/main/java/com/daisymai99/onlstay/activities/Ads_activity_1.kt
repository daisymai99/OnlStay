package com.daisymai99.onlstay.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daisymai99.onlstay.databinding.ActivityAds1Binding

class Ads_activity_1 : AppCompatActivity() {

    lateinit var binding: ActivityAds1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAds1Binding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
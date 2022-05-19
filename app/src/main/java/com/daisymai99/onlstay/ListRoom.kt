    package com.daisymai99.onlstay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daisymai99.onlstay.databinding.ActivityListRoomBinding

    class ListRoom : AppCompatActivity() {
    lateinit var binding: ActivityListRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
package com.daisymai99.onlstay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daisymai99.onlstay.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
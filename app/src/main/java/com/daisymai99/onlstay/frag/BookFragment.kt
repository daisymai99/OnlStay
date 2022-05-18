package com.daisymai99.onlstay.frag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.FragmentBookBinding


class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookBinding.inflate(layoutInflater)
        return binding.root
    }


}
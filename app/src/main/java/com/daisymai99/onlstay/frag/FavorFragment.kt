package com.daisymai99.onlstay.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daisymai99.onlstay.databinding.FragmentFavorBinding

class FavorFragment : Fragment() {

    lateinit var binding: FragmentFavorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavorBinding.inflate(layoutInflater)
        return binding.root
    }


}
package com.daisymai99.onlstay.frag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.adapter.AdsAdapter
import com.daisymai99.onlstay.adapter.itemBook
import com.daisymai99.onlstay.databinding.FragmentBookBinding
import com.daisymai99.onlstay.model.Room


class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookBinding.inflate(layoutInflater)








        /*binding.rclBook.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rclBook.setHasFixedSize(true)
        binding.rclBook.adapter = itemboook*/
        return binding.root
    }


}
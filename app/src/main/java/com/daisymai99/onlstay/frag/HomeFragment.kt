package com.daisymai99.onlstay.frag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.activities.List_Place
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.adapter.AdsAdapter
import com.daisymai99.onlstay.adapter.CardAdapter
import com.daisymai99.onlstay.adapter.Circle_Recycle_Adapter
import com.daisymai99.onlstay.adapter.RcmAdapter
import com.daisymai99.onlstay.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.searchView.queryHint= "Thử tìm kiếm Hà Nội"

        binding.searchView.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.context, List_Place::class.java)
            startActivity(intent)
        })

        // list-of_city
        var adapter = Circle_Recycle_Adapter()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rcl.setHasFixedSize(true)
        binding.rcl.setLayoutManager(layoutManager)
        binding.rcl.adapter = adapter


        // cardView
        var card_adapter = CardAdapter()
        binding.card2Column.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.card2Column.setHasFixedSize(true)
        binding.card2Column.adapter = card_adapter

        // ads
        var adsAdapter = AdsAdapter()
        binding.rclAds.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rclAds.setHasFixedSize(true)
        binding.rclAds.adapter = adsAdapter

        // rcm
        val headerRCM = arrayListOf<String>(*resources.getStringArray(R.array.rcm_1))
        val txtRCM = arrayListOf<String>(*resources.getStringArray(R.array.rcm_2))
        var rcmAdapter = RcmAdapter(headerRCM,txtRCM)
        binding.rclRec.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rclRec.setHasFixedSize(true)
        binding.rclRec.adapter = rcmAdapter



        return  binding.root


    }



}
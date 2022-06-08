package com.daisymai99.onlstay.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.activities.List_Place
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.CardHomeTravelBinding



class CardAdapter() : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val image = intArrayOf(R.drawable.homestay_,R.drawable.quang_ninh)
    private val textheader = arrayOf("Homestay","Vé tham quan")
    private val txt = arrayOf("Căn hộ dịch vụ và biệt thự","Mua vé thật dễ dàng")

    inner class ViewHolder (val binding: CardHomeTravelBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardHomeTravelBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.img.setImageResource(image[position])
            binding.textHeader.text = textheader[position]
            binding.txt.text = txt[position]
            binding.img.setOnClickListener { v -> v.context.startActivity(Intent(v.context,
                List_Place::class.java)) }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
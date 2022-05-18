package com.daisymai99.onlstay.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.ItemAdsBinding

class AdsAdapter() :RecyclerView.Adapter<AdsAdapter.ViewHolder>() {

    var img = intArrayOf(R.drawable.ads,R.drawable.ads2)

    class ViewHolder(val binding: ItemAdsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAdsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.image.setImageResource(img[position])
        holder.binding.image.setOnClickListener {
            if (img[position] ==1){
                it.context.startActivity(Intent(it.context,))
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
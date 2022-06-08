package com.daisymai99.onlstay.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.activities.Ads_activity_1
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.activities.Ads_activity_2
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

            // id image_ADS1 = 2131230808
            if (img[position] ==2131230808){


                Log.d("------------------",img[position].toString())
                it.context.startActivity(Intent(it.context, Ads_activity_1::class.java))
            }


            else {
                it.context.startActivity(Intent(it.context, Ads_activity_2::class.java))
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
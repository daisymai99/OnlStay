package com.daisymai99.onlstay.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.activities.ListRoom
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.ItemRcmBinding

class RcmAdapter(var header : ArrayList<String>, var txt : ArrayList<String>) :RecyclerView.Adapter<RcmAdapter.ViewHolder>() {

    var image = intArrayOf(R.drawable.ngoai_thanh,R.drawable.pool,R.drawable.noi_thanh,R.drawable.sg_need,R.drawable.bbq,R.drawable.sale,R.drawable.sg_center)
    class ViewHolder (val binding: ItemRcmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcmBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.img.setImageResource(image[position])
            binding.textHeader.text = header[position]
            binding.txt.text = txt[position]

            binding.img.setOnClickListener {
                it.context.startActivity(Intent(it.context, ListRoom::class.java))
            }
        }
    }

    override fun getItemCount(): Int {
        return 7
    }
}
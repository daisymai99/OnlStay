package com.daisymai99.onlstay.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.ListRoom
import com.daisymai99.onlstay.List_Place
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.CircleListCityBinding

class Circle_Recycle_Adapter() : RecyclerView.Adapter<Circle_Recycle_Adapter.ViewHolder>() {

    class ViewHolder( val binding: CircleListCityBinding) :RecyclerView.ViewHolder(binding.root)


    private var txt = arrayOf("Hà nội","TP. Hồ Chí Minh","Hội An","Đà Lạt","Đà Nẵng","Nha Trang","Vũng Tàu","Quảng Ninh")

    private val img = intArrayOf(
        R.drawable.hanoi,
        R.drawable.hcm,
        R.drawable.hoi_an,
        R.drawable.da_lat,
        R.drawable.da_nang,
        R.drawable.nha_trang,
        R.drawable.vung_tau,
        R.drawable.quang_ninh
    )



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.txtCity.text = txt[position]
        holder.binding.profileImage.setImageResource(img[position])

        holder.binding.profileImage.setOnClickListener {
                v -> v.context.startActivity(Intent(v.context, ListRoom::class.java)) }


        }

    override fun getItemCount(): Int {
        return txt.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CircleListCityBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}






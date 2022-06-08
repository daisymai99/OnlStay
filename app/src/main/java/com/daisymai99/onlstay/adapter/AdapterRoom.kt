package com.daisymai99.onlstay.adapter

import android.content.Context

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import android.widget.TextView
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.model.Room

class AdapterRoom(var context: Context, var list: List<Room>) : RecyclerView.Adapter<AdapterRoom.Viewholider>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholider {
        val inflater = LayoutInflater.from(context)
        val heroView = inflater.inflate(R.layout.item_book, parent, false)
        return Viewholider(heroView)
    }

    override fun onBindViewHolder(holder: Viewholider, position: Int) {
        val room = list[position]
        Glide.with(context).load(room.img).error(R.mipmap.ic_launcher).into(holder.imgRoom)

        holder.txtNumberRoom.text = "Địa chỉ " + room.noiQuyPhong
        holder.txtTimeNhan.text = "Ngày bắt đầu " + room.timeNhanPhong
        holder.txtTimeTra.text = "Ngày hết " + room.timeTraPhong
        holder.txtPrice.text =""+room.price+"Đ"
        if (room.statusRoom == false) holder.txtStatus.text =
            "Trạng thái phòng: Trống" else holder.txtStatus.text =
            "Trạng thái phòng: Đã có người"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Viewholider(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgRoom: ImageView
        var txtNumberRoom: TextView
        var txtTimeNhan: TextView
        var txtTimeTra: TextView
        var txtStatus: TextView
        var txtPrice: TextView

        init {
            imgRoom = itemView.findViewById(R.id.image_room)
            txtNumberRoom = itemView.findViewById(R.id.txt_ID_Rooom)
            txtTimeNhan = itemView.findViewById(R.id.txtTimeNhan)
            txtTimeTra = itemView.findViewById(R.id.txtTimeTra)
            txtStatus = itemView.findViewById(R.id.txtStatus)
            txtPrice = itemView.findViewById(R.id.txtPrice)

        }
    }
}
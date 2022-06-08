package com.daisymai99.onlstay.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.model.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AdapterDatRoom (var context: Context, var list: List<Room>) : RecyclerView.Adapter<AdapterDatRoom.Viewholider>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholider {
        val inflater = LayoutInflater.from(context)
        val heroView = inflater.inflate(R.layout.item_book, parent, false)
        return Viewholider(heroView)
    }
    var databaseReference: DatabaseReference? = null

    override fun onBindViewHolder(holder: Viewholider, position: Int) {
        val room = list[position]
        Glide.with(context)
            .load(room.img)
            .error(R.mipmap.ic_launcher)
            .into(holder.imgRoom)
        holder.txtNumberRoom.text = "Địa chỉ " + room.noiQuyPhong
        holder.txtTimeNhan.text = "Ngày trống " + room.timeNhanPhong
        holder.txtTimeTra.text = "Đến " + room.timeTraPhong
        holder.txtPrice.text ="" + room.price+" Đ"
        if (room.statusRoom == false) holder.txtStatus.text =
            "Trạng thái phòng: Trống" else holder.txtStatus.text =
            "Trạng thái phòng: Đã có người"


        holder.itemView.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Đặt phòng")
                .setMessage("Bạn có muốn đặt phòng không?")
                .setPositiveButton("Đặt phòng",
                    DialogInterface.OnClickListener { dialog, which ->
                        databaseReference = FirebaseDatabase.getInstance().getReference("room")

                        databaseReference!!.child(room.id.toString()).child("statusRoom").setValue(true)
                            .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Thanh cong!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                  dialog.dismiss()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Loi !!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    })
                .setNegativeButton("hủy", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

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
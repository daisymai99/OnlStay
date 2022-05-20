package com.daisymai99.onlstay.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.ItemBookBinding
import com.daisymai99.onlstay.model.Hotel
import com.daisymai99.onlstay.model.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class itemBook( var item : ArrayList<Room>) : RecyclerView.Adapter<itemBook.Viewholder>() {
    class Viewholder (var binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    val db = Firebase.firestore

    var room = arrayListOf<Room>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return  Viewholder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        with(holder){
            Glide.with(this.itemView.context)
                .load("http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcR2Ti73tnFRgh3cENA6buMO4tjpxb_1aCpDDB5uxM_zQ_TlvnqSkTSHfT6DpqIGGCCzz_hS6oyqCcfBbb2p6mA") // image url
                .override(200, 200) // resizing
                .centerCrop()
                .into(binding.imageRoom);  // imageview object

           /* binding.txtIDRooom.text = item[position].id.toString()
            binding.txtTimeNhan.text = item[position].adddress
            binding.txtPrice.text = item[position].price.toString()

            binding.btnBook.setOnClickListener {

                Toast.makeText(it.context,"Đăng ký thành công", Toast.LENGTH_LONG).show()
                db.collection("users")
                    .add(item)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }

            }*/

        }
    }

    override fun getItemCount(): Int {
        return  item.size
    }


}
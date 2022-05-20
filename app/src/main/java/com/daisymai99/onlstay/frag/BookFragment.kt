package com.daisymai99.onlstay.frag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.adapter.AdapterRoom
import com.daisymai99.onlstay.adapter.AdsAdapter
import com.daisymai99.onlstay.adapter.itemBook
import com.daisymai99.onlstay.databinding.FragmentBookBinding
import com.daisymai99.onlstay.model.Room
import com.google.firebase.database.*


class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding

    var databaseReference: DatabaseReference? = null
    lateinit var recylerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookBinding.inflate(layoutInflater)



        recylerView = binding.rclBook
        var list = mutableListOf<Room>()
        databaseReference = FirebaseDatabase.getInstance().reference.child("ROOM")
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (ds in snapshot.children) {
                        val room = ds.getValue(Room::class.java)
                        room?.let { list.add(it) }
                    }

                    var  adapterRoom =
                        context?.let { AdapterRoom(it, list) }

                    recylerView.setAdapter(adapterRoom)
                    recylerView.setLayoutManager(LinearLayoutManager(context))
                    if (adapterRoom != null) {
                        adapterRoom.notifyDataSetChanged()
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {}
        });






        /*binding.rclBook.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rclBook.setHasFixedSize(true)
        binding.rclBook.adapter = itemboook*/
        return binding.root
    }


}
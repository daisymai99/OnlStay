package com.daisymai99.onlstay.frag

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.adapter.AdapterDatRoom
import com.daisymai99.onlstay.databinding.FragmentFavorBinding
import com.daisymai99.onlstay.model.Room
import com.google.firebase.database.*

class FavorFragment : Fragment() {

    lateinit var binding: FragmentFavorBinding
    var databaseReference: DatabaseReference? = null
    lateinit var recylerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavorBinding.inflate(layoutInflater)



        recylerView = binding.rclFavor


        var list = mutableListOf<Room>()
        databaseReference = FirebaseDatabase.getInstance().reference.child("room")
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (ds in snapshot.children) {
                        val room = ds.getValue(Room::class.java)
                        if (room != null) {
                            if (room.statusRoom == false)
                                room?.let { list.add(it) }
                        }
                    }

                    var adapterRoom =
                        context?.let { AdapterDatRoom(it, list) }

                    recylerView.setAdapter(adapterRoom)
                    recylerView.setLayoutManager(LinearLayoutManager(context))
                    if (adapterRoom != null) {
                        adapterRoom.notifyDataSetChanged()
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {}
        });
        return binding.root
    }


}
package com.daisymai99.onlstay.frag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.Owner_room
import com.daisymai99.onlstay.R

import com.daisymai99.onlstay.adapter.AdapterRoom

import com.daisymai99.onlstay.databinding.FragmentOwnerBinding
import com.daisymai99.onlstay.model.Room
import com.daisymai99.onlstay.model.SavedPreference
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class OwnerFragment :Fragment() {

    var databaseReference: DatabaseReference? = null
    lateinit var recylerView : RecyclerView

    lateinit var binding: FragmentOwnerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_owner,container,false)

        recylerView = view.findViewById(R.id.rcl_room)

        var txt :TextView = view.findViewById(R.id.txtUserName)
        txt.text = SavedPreference.getUsername(view.context)

        var txtEmail :TextView = view.findViewById(R.id.txtUserEmail)
        txtEmail.text = SavedPreference.getEmail(view.context)

        var floating : FloatingActionButton = view.findViewById(R.id.btn_floating)
        floating.setOnClickListener {
            startActivity(Intent(view.context,Owner_room::class.java))
        }

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


        return  view;
    }
}
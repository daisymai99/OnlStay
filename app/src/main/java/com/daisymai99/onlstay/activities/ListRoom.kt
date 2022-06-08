    package com.daisymai99.onlstay.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.adapter.AdapterDatRoom
import com.daisymai99.onlstay.adapter.AdapterRoom
import com.daisymai99.onlstay.databinding.ActivityListRoomBinding
import com.daisymai99.onlstay.model.Room
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class ListRoom : AppCompatActivity() {
    var databaseReference: DatabaseReference? = null
    lateinit var recylerView : RecyclerView
    lateinit var binding: ActivityListRoomBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnPickDate.setOnClickListener {
            showDateRangePicker()
        }

        //searchView

        binding.searchView.queryHint = "Tìm kiếm địa điểm"

        binding.searchView.setOnClickListener{
            startActivity(Intent(this@ListRoom, List_Place::class.java))
        }

        // recyclerView - adapter

        recylerView = binding.rclListRoom

        var list = mutableListOf<Room>()
        databaseReference = FirebaseDatabase.getInstance().reference.child("room")
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (ds in snapshot.children) {
                        val room = ds.getValue(Room::class.java)
                        room?.let { list.add(it) }
                    }

                    var  adapterRoom = this?.let { AdapterDatRoom(context = this@ListRoom, list) }

                    recylerView.setAdapter(adapterRoom)
                    recylerView.setLayoutManager(LinearLayoutManager(this@ListRoom))
                    if (adapterRoom != null) {
                        adapterRoom.notifyDataSetChanged()
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {}
        });



        //binding.rclListRoom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

    }

        private fun showDateRangePicker() {
            val dateRange = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Chọn ngày ").build()
            dateRange.show(supportFragmentManager,"date range")

            dateRange.addOnPositiveButtonClickListener { datePicked ->
                val start = datePicked.first
                val end = datePicked.second


                if (end != null && start != null){
                    binding.tvDate.text = convertLongDate(start)+"   "
                    binding.tvDateEnd.text = convertLongDate(end)+"   "
                }

            }
        }


        private fun convertLongDate(time :Long):String{
            val date = Date(time)
            val format = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
            return format.format(date)
        }

    }
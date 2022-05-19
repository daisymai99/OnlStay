    package com.daisymai99.onlstay

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.daisymai99.onlstay.databinding.ActivityListRoomBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.security.UnrecoverableKeyException
import java.text.SimpleDateFormat
import java.util.*

    class ListRoom : AppCompatActivity() {
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
            startActivity(Intent(this@ListRoom,List_Place::class.java))
        }

        // recyclerView - adapter

        binding.rclListRoom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

    }

        private fun showDateRangePicker() {
            val dateRange = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Chọn ngày ").build()
            dateRange.show(supportFragmentManager,"date range")

            dateRange.addOnPositiveButtonClickListener { datePicked ->
                val start = datePicked.first
                val end = datePicked.second


                if (end != null && start != null){
                    binding.tvDate.text = "From : "+convertLongDate(start)+"\n To: ${convertLongDate(end)}"
                }



            }
        }


        private fun convertLongDate(time :Long):String{
            val date = Date(time)
            val format = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
            return format.format(date)
        }

    }
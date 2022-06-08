package com.daisymai99.onlstay

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.daisymai99.onlstay.databinding.ActivityTestCreateRoomBinding
import com.daisymai99.onlstay.frag.OwnerFragment
import com.daisymai99.onlstay.model.Room
import com.daisymai99.onlstay.model.SavedPreference
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class Test_Create_Room : AppCompatActivity() {

    lateinit var binding: ActivityTestCreateRoomBinding

    private val reference = FirebaseStorage.getInstance().reference

    val start : String =""
    val end : String =""

    var linkStorage =""

    var status: Boolean = false
    val original = listOf("Trống", "Có khách")

    var selectedImageUri :Uri ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCreateRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectedImage.setOnClickListener {
            selectedImageStore()
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.simple_spinner_item, original)

        binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if (selectedItem == "Trống") {
                    status = false
                } else {
                    status = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.spinner.adapter = adapter

        binding.btnPickDate.setOnClickListener {
            showDateRangePicker()
        }

        binding.btnAddroom.setOnClickListener {
            saveRoomToDatabase()

        }

    }

    private fun selectedImageStore() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==100 && resultCode == RESULT_OK && data != null){
             selectedImageUri = data?.data!!
            //binding.imageStore.setImageURI(IMGURI)

            var filename = UUID.randomUUID().toString()
            var ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            ref.putFile(selectedImageUri!!).addOnSuccessListener {


                        ref.downloadUrl.addOnSuccessListener { uri ->
                            Glide.with(this).load(uri.toString()).into(binding.imageStore)
                            linkStorage = uri.toString();
                        }

                        Log.d("Storage", "success ${it}")
            }

        }
    }

    private fun saveRoomToDatabase() {
        var ref = FirebaseDatabase.getInstance().getReference("/room/")

        if (binding.edtID.text?.isEmpty() == true || binding.edtNoiQuy.text?.isEmpty() == true || binding.tvDate.text?.isEmpty() == true
            || binding.tvDateEnd.text?.isEmpty() == true || binding.edtPrice.text?.isEmpty() == true){
            Toast.makeText(this,"Vui lòng không để trống các thông tin của phòng !!!", Toast.LENGTH_SHORT).show()
            return
        }

        var room = Room(Integer.parseInt(binding.edtID.text.toString()),binding.edtNoiQuy.text.toString(),linkStorage,binding.tvDate.text.toString(),binding.tvDateEnd.text.toString(),
            status,binding.edtPrice.text.toString(),SavedPreference.getUserID(this).toString(),"")




        println("------------------------"+binding.edtID.toString())

        ref.child(binding.edtID.text.toString()).setValue(room)
            .addOnSuccessListener {



                Log.d("database", room.toString())
                Toast.makeText(this,"SUCCESS save to database ", Toast.LENGTH_SHORT).show()
                finish()

        }
    }

    private fun upImageToStorage(){

    }

    // chọn ngày trống
    private fun showDateRangePicker() {
        val dateRange = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Chọn ngày ").build()
        dateRange.show(supportFragmentManager,"date range")

        dateRange.addOnPositiveButtonClickListener { datePicked ->
            val start = datePicked.first
            val end = datePicked.second


            if (end != null && start != null){
                binding.tvDate.text = convertLongDate(start)
                binding.tvDateEnd.text = convertLongDate(end)

            }



        }
    }


    private fun convertLongDate(time :Long):String{
        val date = Date(time)
        val format = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        return format.format(date)
    }
}
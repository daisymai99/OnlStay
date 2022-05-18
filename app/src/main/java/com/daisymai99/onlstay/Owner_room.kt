package com.daisymai99.onlstay

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.daisymai99.onlstay.databinding.ActivityOwnerRoomBinding
import com.daisymai99.onlstay.model.Room
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Owner_room : AppCompatActivity() {

    lateinit var binding: ActivityOwnerRoomBinding
    lateinit var IMGURI : Uri
    var id =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectedImage.setOnClickListener {
            selectedImageStore()
        }

        binding.btnUpLoadRoom.setOnClickListener {
            upLoadRoom()
        }
    }

    private fun upLoadRoom() {


        val formatter = SimpleDateFormat("yyyy_MM_dd_ss", Locale.getDefault())
        val  now = Date()
        val filename = formatter.format(now)
        val storeReference = FirebaseStorage.getInstance().getReference("images/$filename")

        storeReference.putFile(IMGURI)
            .addOnSuccessListener {


                /*storeReference.downloadUrl.addOnSuccessListener {

                    FirebaseDatabase.getInstance().getReference("owner");
                    lateinit var map : HashMap<String,String>
                    map.put("imageurl", IMGURI.toString() )


                }*/
                Toast.makeText(this.applicationContext,"Đã tạo thành công", Toast.LENGTH_LONG).show()
                finish()

            }

            .addOnFailureListener{
                Toast.makeText(this.applicationContext,"Tạo thất bại",Toast.LENGTH_LONG).show()

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

        if (requestCode==100 && resultCode == RESULT_OK){
            IMGURI = data?.data!!
            binding.imageStore.setImageURI(IMGURI)
        }
    }
}
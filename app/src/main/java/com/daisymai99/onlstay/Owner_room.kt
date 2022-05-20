package com.daisymai99.onlstay

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.daisymai99.onlstay.databinding.ActivityOwnerRoomBinding
import com.daisymai99.onlstay.model.Room
import com.google.common.io.Files.getFileExtension
import com.google.firebase.database.DatabaseReference
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
    var urlString: String = ""

    lateinit var database: DatabaseReference

    private val reference = FirebaseStorage.getInstance().reference

    lateinit var id_room: String
    var status: Boolean = false
    val original = listOf("Trống", "Có khách")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("ROOM")

        binding.btnSelectedImage.setOnClickListener {
            selectedImageStore()
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.simple_spinner_item, original
        )

        binding.spinner.adapter = adapter

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

        binding.btnUpLoadRoom.setOnClickListener {

            val room = Room(
                Integer.parseInt(binding.edtID.text.toString()),
                binding.edtNoiQuy.text.toString(),
                urlString,
                binding.edtTimeNhanPhong.text.toString(),
                binding.edtTimeTraPhong.text.toString(),
                status, binding.edtPrice.text.toString()

            )

            database.child(binding.edtID.text.toString())
                .setValue(room).addOnSuccessListener{
                    Toast.makeText(applicationContext,"Thêm phòng thành công !!!",Toast.LENGTH_LONG)
                    startActivity(Intent(this,ListRoom::class.java))

                }.addOnFailureListener{
                    Toast.makeText(applicationContext,"Thêm phòng thất bại !!!",Toast.LENGTH_LONG)

                }
        }
    }

    private fun upLoadRoom() {


        val formatter = SimpleDateFormat("yyyy_MM_dd_ss", Locale.getDefault())
        val  now = Date()
        val filename = formatter.format(now)
        val storeReference = FirebaseStorage.getInstance().getReference("images/$filename")

        storeReference.putFile(IMGURI)
            .addOnSuccessListener {

                storeReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(this).load(uri.toString())
                        .into(binding.imageStore)
                    urlString = uri.toString();
                }

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
            //binding.imageStore.setImageURI(IMGURI)

            val fileRef =
                reference.child(
                    System.currentTimeMillis().toString() + "." + getFileExtension(
                        IMGURI
                    )
                )
            fileRef.putFile(IMGURI).addOnSuccessListener {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(this).load(uri.toString())
                        .into(binding.imageStore)
                    urlString = uri.toString();
                }
            }.addOnProgressListener { }.addOnFailureListener { }
        }
    }
    private fun getFileExtension(mUri: Uri): String? {
        val cr = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(mUri))
    }
}
package com.daisymai99.onlstay.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.daisymai99.onlstay.databinding.ActivitySignUpBinding
import com.daisymai99.onlstay.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    var TAG = "Sign_In"

    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth
        binding.btnSignUp.setOnClickListener {

            var email = binding.edtEmail?.text.toString()
            var password = binding.edtPass?.text.toString()

            auth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        println("createUserWithEmail:success")
                        val user = auth?.currentUser
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                        saveUserToDatabase()
                        startActivity(Intent(this,Login::class.java))

                    } else {


                        Toast.makeText(this, "Vui lòng kiểm tra kết nối mạng", Toast.LENGTH_SHORT).show()

                    }
                }


        }

        binding.txtAlreadyHaveAccount.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

    }


    private fun saveUserToDatabase() {
        var uid = FirebaseAuth.getInstance().uid ?:""
        val ref = FirebaseDatabase.getInstance().getReference("/account/$uid")

        var user = User(uid, email = binding.edtEmail?.text.toString(), name = binding.edtName?.text.toString(), password = binding.edtPass?.text.toString())
        ref.setValue(user)
            .addOnCompleteListener {
                println(user)
            Log.d("database", "success save to database $user") }

            .addOnFailureListener {
                println(user)
                Log.d("database", "fail save to database ")
            }
    }




}
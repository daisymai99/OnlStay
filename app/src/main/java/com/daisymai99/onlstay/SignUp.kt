package com.daisymai99.onlstay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.daisymai99.onlstay.databinding.ActivitySignUpBinding
import com.daisymai99.onlstay.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    var TAG = "Sign_In"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.edtEmail.text.toString()
        val password = binding.edtPass.text.toString()

        if (email.isEmpty() || password.isEmpty() ) return

        binding.btnSignUp.setOnClickListener {
            signUpFirebase(email,password)
            saveUserToDatabase()

        }

        binding.txtAlreadyHaveAccount.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }

    }

    private fun saveUserToDatabase() {
        var uid = FirebaseAuth.getInstance().uid ?:""
        val ref = FirebaseDatabase.getInstance().getReference("/account/$uid")

        var user = User(uid, email = binding.edtEmail.text.toString(), name = binding.edtName.text.toString(), password = binding.edtPass.text.toString())
        ref.setValue(user)
            .addOnCompleteListener {
            Log.d("database", "success save to database ")
        }
            .addOnFailureListener {
                Log.d("database", "fail save to database ")
            }
    }

    private fun signUpFirebase(email : String, password :String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success ${task.result.user?.uid}")
                    Toast.makeText(baseContext, "Authentication susscess.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }
}
package com.daisymai99.onlstay

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.daisymai99.onlstay.databinding.ActivityLoginBinding
import com.daisymai99.onlstay.frag.AccountFragment
import com.daisymai99.onlstay.model.SavedPreference
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception



class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code:Int=123
    val firebaseAuth= FirebaseAuth.getInstance()
    val TAG ="GOOGLE_SIGN_IN_TAG"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)


        binding.btnSIgnInEmail.setOnClickListener {
            val signInIntent =mGoogleSignInClient.signInIntent

            startActivityForResult(signInIntent,Req_Code)
        }

        binding.btnLogIn.setOnClickListener {

        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        /*binding.txtNameUser.text = SavedPreference.getUsername(this)*/

        // chưa đc

        /*binding.btnSignOut.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                SavedPreference.setEmail(this,"")
                SavedPreference.setUsername(this,"")
                Toast.makeText(this,"Logging Out",Toast.LENGTH_SHORT).show()
                finish()
            }
        }*/

       /* binding.btnClose.setOnClickListener{
            finish()
        }*/





    }

   /* override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        *//*if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*//*

    }*/


    // onActivityResult() function : this is where we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Req_Code){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                handleResult(task)
            }
            catch (e : Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
            }

        }
    }
    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                val credential= GoogleAuthProvider.getCredential(account.idToken,null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {task->
                    if(task.isSuccessful) {

                        val firebaseUser = firebaseAuth.currentUser
                        val uname = account.displayName.toString()
                        val email = firebaseUser!!.email
                        Log.d(TAG,"Account : $uname")
                        Log.d(TAG,"Account : $email")

                        SavedPreference.setEmail(this,account.email.toString())
                        SavedPreference.setUsername(this,account.displayName.toString())

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }


                }
                    .addOnFailureListener {
                        Log.d(TAG,"LOGIN FAIL ${it.message}")
                        Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                    }
            }
        } catch (e: ApiException){

        }
    }




}
package com.daisymai99.onlstay.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.daisymai99.onlstay.MainActivity
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.databinding.ActivityLoginBinding
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
        installSplashScreen()
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
            if (binding.edtName.text?.isEmpty() == true ||binding.edtPass.text?.isEmpty() == true ){
                Toast.makeText(this,"Vui lòng không để trống email và mật khẩu",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            logInFirebase(binding.edtName.text.toString(),binding.edtPass.text.toString())
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }









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


                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }


                }
                    .addOnFailureListener {
                        Log.d("LOGIN","LOGIN FAIL ${it.message}")
                        Toast.makeText(this,"Vui lòng kiểm tra tài khoản",Toast.LENGTH_SHORT).show()
                    }
            }
        } catch (e: ApiException){

        }
    }

    private fun logInFirebase(name : String, password :String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(name,password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.d("LOGIN","LOGIN FAIL ")
                    Toast.makeText(this,"Tài khoản này không tồn tại, vui lòng kiểm tra lại thông tin",Toast.LENGTH_SHORT).show()
                    return@addOnCompleteListener
                }
                else{
                    SavedPreference.setEmail(this,name)
                    //SavedPreference.setUsername(this,name)


                    Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, MainActivity::class.java))
                }

            }
            .addOnFailureListener {
                Toast.makeText(this,"Vui lòng kiểm tra lại thông tin",Toast.LENGTH_SHORT).show()
                return@addOnFailureListener
            }
    }



}
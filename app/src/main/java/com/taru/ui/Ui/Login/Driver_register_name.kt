package com.taru.ui.Ui.Login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.taru.R
import com.taru.ui.MainActivity

class Driver_register_name : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var signinbtn:TextView
    private lateinit var google_signin :ImageView
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_register_name)

        auth = FirebaseAuth.getInstance()

        //check if auth is not null
        if (auth.currentUser != null) {
            // User is already authenticated, navigate to MapsActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        google_signin=findViewById(R.id.google_icon)

        val usermail: TextView = findViewById(R.id.editTextEmail)
        val userpass: TextView = findViewById(R.id.editTextPassword)
        val next: Button = findViewById(R.id.btnOtp)
        signinbtn=findViewById(R.id.tvLogin)
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signinbtn.setOnClickListener()
        {

            val intent:Intent=Intent(this, Sign_in_user::class.java)
            startActivity(intent)
        }
        next.setOnClickListener {


//                if(userpass.text.toString().length >6 && usermail.text.toString().length>5)
//                {
                    signInWithEmailAndPassword(usermail.text.toString(),userpass.text.toString())
//                }
//            else
//                {
//
//                    usermail.error="Invalid Username"
//                    userpass.setError("Invalid password")
//                }
        }

        google_signin.setOnClickListener()
        {
            Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }
    }
    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    // Sign-in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    // You can redirect the user to the main activity or do any other action here
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this,"Sucessfully signed in ", Toast.LENGTH_SHORT).show()
                    val intent:Intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign-in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this,"signed in failed", Toast.LENGTH_SHORT).show()
                    // Show an error message to the user or handle the error accordingly.
                }
            })
    }


    //google signin
    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    // onActivityResult() function : this is where
    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    // this is where we update the UI after Google signin takes place
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                SavedPreference.setEmail(this, account.email.toString())
//                SavedPreference.setUsername(this, account.displayName.toString())
//                val intent = Intent(this, MapsActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
//            startActivity(
//                Intent(
//                    this,MapsActivity
//                    ::class.java
//                )
//            )
//            finish()
        }
    }

}

package com.taru.ui.Ui.Login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.taru.R

class Sign_in_user : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_user)
        auth=FirebaseAuth.getInstance()
        val usermail:TextInputEditText=findViewById(R.id.editTextEmail)
        val userpass:TextInputEditText=findViewById(R.id.userpassword)
        val userconfirmpass:TextInputEditText=findViewById(R.id.editTextTextPassword1)
        val Create_acc_btn : Button=findViewById(R.id.create_btn)
        Create_acc_btn.setOnClickListener(){
            if(userpass.text.toString()==userconfirmpass.text.toString())
            {
                createAccountWithEmailAndPassword(usermail.text.toString(),userpass.text.toString())
            }
            else
            {
                Toast.makeText(this,"Password doesnt match",Toast.LENGTH_SHORT).show()
            }
        }


    }



    private fun createAccountWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    // Account creation success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    // You can redirect the user to the main activity or do any other action here
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(this,"Authentication Successful ! ",Toast.LENGTH_SHORT).show()
                    val intent:Intent= Intent(this, Register_details::class.java)
                    startActivity(intent)
                } else {
                    // If account creation fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this,"Authentication Failure ",Toast.LENGTH_SHORT).show()
                    // Show an error message to the user or handle the error accordingly.
                }
            })
    }
}
package com.taru.ui.Ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arima.plantbuddy.network.createTwilioApiService
import com.taru.R
import com.taru.ui.MainActivity
import com.taru.ui.Ui.user_login.Homescreen
import retrofit2.Call

class Register_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_details)

        val mobileno: TextView = findViewById(R.id.mobilno)

        val otp: String = generateOTP()
        val sendbutton: Button = findViewById(R.id.send_otp)

        val messagestring = "Your OTP is: $otp\nFor your Mobile No ${mobileno.text.toString()}"

        sendbutton.setOnClickListener {
            Toast.makeText(this,"Pressed",Toast.LENGTH_SHORT).show()
            sendSMS(mobileno.text.toString(), messagestring) // Use mobileno.text.toString()
        }
    }

    fun sendSMS(phoneNumber: String, message: String) {
        val twilioService = createTwilioApiService()

        val accountSid = "ACadae241539c5a28b2ba06ef66b5db658"
        val twilioPhoneNumber = "+15075853015"

        val call = twilioService.sendSMS(accountSid,"+919368896091",twilioPhoneNumber,message)
        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                if (response.isSuccessful) {
                    println("SMS sent successfully!")
                    Toast.makeText(this@Register_details,"Successfully sent otp", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@Register_details, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    println(phoneNumber)
                    Toast.makeText(this@Register_details,"Failed to send SMS: ${response.code()}",Toast.LENGTH_SHORT).show()
                    println("Failed to send SMS: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to send SMS: ${t.message}")
                println(phoneNumber)
                Toast.makeText(this@Register_details,"failure Failed to send SMS: ${t.message}",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun generateOTP(): String {
        // Generate a random 6-digit number for OTP
        val otp = (100000..999999).random()
        return otp.toString()
    }
}

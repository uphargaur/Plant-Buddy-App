package com.taru.ui.Ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.taru.R

class SplashScreen : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 3000 // 3 seconds
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    // on below line we are creating variable for view pager,
    // viewpager adapter and the image list.
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapterr
    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        // initializing variables
        // of below line with their id.
        viewPager = findViewById(R.id.idViewPager)

        // on below line we are initializing
        // our image list and adding data to it.
        imageList = listOf(R.drawable.viewpager_img_0,R.drawable.viewpager_img_3, R.drawable.viewpager_img1,R.drawable.viewpager_img2)

        // on below line we are initializing our view
        // pager adapter and adding image list to it.
        //...
        viewPagerAdapter = ViewPagerAdapterr(this@SplashScreen, viewPager, imageList)



        // on below line we are setting
        // adapter to our view pager.
        viewPager.adapter = viewPagerAdapter

        // Auto-slide feature if you have implemented it in ViewPagerAdapter
        viewPagerAdapter.startAutoSlide()


        val  nextbt: ImageButton =findViewById(R.id.Nextbtn)
        nextbt.setOnClickListener()
        {
            val intent :Intent= Intent(this, Driver_register_name::class.java)
            startActivity(intent)
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop auto-slide when the activity is destroyed to avoid memory leaks
        viewPagerAdapter.stopAutoSlide()
    }



}


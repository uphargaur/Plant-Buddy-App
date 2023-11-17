package com.taru.ui.Ui.Login

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.taru.R

class ViewPagerAdapterr(
    private val context: Context,
    private val viewPager: ViewPager,
    private val imageList: List<Int>
) : PagerAdapter() {
    private val handler = Handler()
    private var currentIndex = 0

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.image_slider_item, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.idIVImage)
        imageView.setImageResource(imageList[position])
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun startAutoSlide() {
        handler.postDelayed(slideRunnable, 3000)
    }

    fun stopAutoSlide() {
        handler.removeCallbacks(slideRunnable)
    }

    private val slideRunnable: Runnable = object : Runnable {
        override fun run() {
            if (currentIndex + 1 >= imageList.size) {
                currentIndex = 0
            } else {
                currentIndex++
            }
            viewPager.setCurrentItem(currentIndex, true)
            handler.postDelayed(this, 3000)
        }
    }
}
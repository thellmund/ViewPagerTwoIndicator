package com.hellmund.viewpager2indicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_vertical_view_pager.*

class VerticalViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_view_pager)

        viewPager.apply {
            adapter = ViewPagerAdapter()
            orientation = ViewPager2.ORIENTATION_VERTICAL
        }
        indicator.attachTo(viewPager)
    }

}

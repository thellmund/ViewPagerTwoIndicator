package com.hellmund.viewpager2indicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_horizontal_view_pager.*

class HorizontalViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_view_pager)

        viewPager.adapter = ViewPagerAdapter()
        indicator.attachTo(viewPager)
    }

}

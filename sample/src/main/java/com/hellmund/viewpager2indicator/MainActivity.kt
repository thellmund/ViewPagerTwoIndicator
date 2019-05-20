package com.hellmund.viewpager2indicator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonHorizontalViewPager.setOnClickListener {
            startActivity(Intent(this, HorizontalViewPagerActivity::class.java))
        }

        buttonVerticalViewPager.setOnClickListener {
            startActivity(Intent(this, VerticalViewPagerActivity::class.java))
        }
    }

}

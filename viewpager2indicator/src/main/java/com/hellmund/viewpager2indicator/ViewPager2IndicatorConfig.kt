package com.hellmund.viewpager2indicator

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.res.use

internal class ViewPager2IndicatorConfig(
    context: Context,
    attrs: AttributeSet? = null
) {

    var selectedFillColor: Int = 0
    var unselectedFillColor: Int = 0
    var radius: Float = 0f
    var spacing: Float = 0f

    val diameter: Float
        get() = radius * 2

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ViewPager2Indicator, 0, 0)
        a.use {
            selectedFillColor = a.getColor(R.styleable.ViewPager2Indicator_selectedFillColor, Color.WHITE)
            unselectedFillColor = a.getColor(R.styleable.ViewPager2Indicator_unselectedFillColor, Color.GRAY)
            radius = a.getDimension(R.styleable.ViewPager2Indicator_radius, 4f)
            spacing = a.getDimension(R.styleable.ViewPager2Indicator_spacing, diameter)
        }
    }

}

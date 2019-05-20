package com.hellmund.viewpager2indicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.min
import kotlin.math.roundToInt


class ViewPager2Indicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val config: ViewPager2IndicatorConfig by lazy {
        ViewPager2IndicatorConfig(context, attrs)
    }

    private var _viewPager: ViewPager2? = null
    private var currentPosition = 0

    private var selectedPaint = Paint().apply {
        color = config.selectedFillColor
    }

    private var unselectedPaint = Paint().apply {
        color = config.unselectedFillColor
    }

    private val onPageChange = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            Log.d("ViewPager2Indicator", "onPageSelected")
            super.onPageSelected(position)
            currentPosition = position
            invalidate()
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            Log.d("ViewPager2Indicator", "onPageScrollStateChanged")
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            Log.d("ViewPager2Indicator", "onPageScrolled (position $position)")
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val viewPager = _viewPager ?: return
        val count = viewPager.adapter?.itemCount ?: return

        if (count <= 1) {
            return
        }

        if (currentPosition >= count) {
            currentPosition = count - 1
            return
        }

        drawIndicators(viewPager.isHorizontal, count, canvas)
    }

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        val viewPager = _viewPager ?: return super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (viewPager.isHorizontal) {
            setMeasuredDimension(measureLong(widthMeasureSpec), measureShort(heightMeasureSpec))
        } else {
            setMeasuredDimension(measureShort(widthMeasureSpec), measureLong(heightMeasureSpec))
        }
    }

    private fun measureLong(
            measureSpec: Int
    ): Int {
        val viewPager = checkNotNull(_viewPager) { "No ViewPager2 attached to ViewPager2Indicator" }
        val adapter = checkNotNull(viewPager.adapter) { "No adapter attached to ViewPager2" }
        val count = adapter.itemCount

        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return if (specMode == MeasureSpec.EXACTLY) {
            specSize
        } else {
            val size = (paddingLeft + calculateLengthOfIndicators(count) + paddingRight).roundToInt()
            if (specMode == MeasureSpec.AT_MOST) min(size, specSize) else size
        }
    }

    private fun measureShort(
            measureSpec: Int
    ): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return if (specMode == MeasureSpec.EXACTLY) {
            specSize
        } else {
            val size = (paddingTop + 2 * config.radius + paddingBottom).roundToInt()
            if (specMode == MeasureSpec.AT_MOST) min(size, specSize) else size
        }
    }

    private fun calculateLengthOfIndicators(
            count: Int
    ): Float = count * config.diameter + config.spacing * (count - 1)

    private fun drawIndicators(
            isHorizontal: Boolean,
            count: Int,
            canvas: Canvas?
    ) {
        val usedLength = calculateLengthOfIndicators(count)
        val availableLength = if (isHorizontal) width else height
        var start = (availableLength - usedLength) / 2

        for (position in 0 until count) {
            val paint = if (position == currentPosition) selectedPaint else unselectedPaint
            val x = if (isHorizontal) start else width / 2f
            val y = if (isHorizontal) height / 2f else start
            canvas?.drawCircle(x, y, config.radius, paint)
            start += config.diameter + config.spacing
        }
    }

    @JvmOverloads
    fun attachTo(
            viewPager: ViewPager2,
            currentItem: Int = 0
    ) {
        if (_viewPager == viewPager) {
            return
        }

        _viewPager?.unregisterOnPageChangeCallback(onPageChange)

        _viewPager = viewPager
        _viewPager?.registerOnPageChangeCallback(onPageChange)
        _viewPager?.currentItem = currentItem
        invalidate()
    }

    private val ViewPager2.isHorizontal: Boolean
        get() = orientation == ViewPager2.ORIENTATION_HORIZONTAL

}

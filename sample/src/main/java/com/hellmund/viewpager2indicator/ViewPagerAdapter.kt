package com.hellmund.viewpager2indicator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pager_item.view.*

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = 3

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(index: Int) = with(itemView) {
            textView.text = "Fragment ${index + 1}"
            setBackgroundResource(colors[index])
        }

    }

    companion object {

        private val colors = arrayOf(
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark
        )

    }

}

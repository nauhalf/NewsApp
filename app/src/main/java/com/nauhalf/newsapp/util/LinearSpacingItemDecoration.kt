package com.nauhalf.newsapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearSpacingItemDecoration(
    private val spacing: Int,
    private val orientation: Int = LinearLayoutManager.VERTICAL,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view) // item position
        if (position > 0) {
            when(orientation){
                LinearLayoutManager.VERTICAL -> {
                    outRect.top = spacing
                }
                LinearLayoutManager.HORIZONTAL -> {
                    outRect.left = spacing
                }
            }
        }
    }
}


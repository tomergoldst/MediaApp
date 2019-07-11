package com.tomergoldst.mediaapp.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleItemDecoration(
    private val mVerSpace: Int,
    private val mHrzSpace: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = mHrzSpace
        outRect.right = mHrzSpace
        outRect.bottom = mVerSpace
        outRect.top = mVerSpace
    }

}

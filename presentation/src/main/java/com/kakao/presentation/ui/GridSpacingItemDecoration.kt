package com.kakao.presentation.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeTopEdge: Boolean = false,
    private val includeBottomEdge: Boolean = false,
    private val includeLeftEdge: Boolean = false,
    private val includeRightEdge: Boolean = false,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val itemSize = parent.adapter?.itemCount ?: return
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeTopEdge) {
            if (position < spanCount) {
                outRect.top = spacing
            }
            if ((itemSize - 1) - position >= spanCount) {
                outRect.bottom = spacing
            }
        } else {
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }

        if (includeBottomEdge) {
            if ((itemSize - 1) - position < spanCount) {
                outRect.bottom = spacing
            }
        }

        if (includeLeftEdge) {
            outRect.left = spacing - column * spacing / spanCount
        } else {
            outRect.left = column * spacing / spanCount
        }
        if (includeRightEdge) {
            outRect.right = (column + 1) * spacing / spanCount
        } else {
            outRect.right = spacing - (column + 1) * spacing / spanCount
        }

    }

}
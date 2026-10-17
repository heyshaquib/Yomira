package org.koitharu.kotatsu.core.ui.list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FitHeightGridLayoutManager : GridLayoutManager {

	constructor(context: Context?, spanCount: Int) : super(context, spanCount)

	constructor(
		context: Context?,
		attrs: AttributeSet?,
		defStyleAttr: Int,
		defStyleRes: Int,
	) : super(context, attrs, defStyleAttr, defStyleRes)

	constructor(
		context: Context?,
		spanCount: Int,
		orientation: Int,
		reverseLayout: Boolean,
	) : super(context, spanCount, orientation, reverseLayout)


	// Lay out half a screen past each edge, so covers there are decoded before they scroll into view.
	override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
		super.calculateExtraLayoutSpace(state, extraLayoutSpace)
		val extra = (if (orientation == RecyclerView.VERTICAL) height else width) / 2
		extraLayoutSpace[0] = maxOf(extraLayoutSpace[0], extra)
		extraLayoutSpace[1] = maxOf(extraLayoutSpace[1], extra)
	}

	override fun layoutDecoratedWithMargins(child: View, left: Int, top: Int, right: Int, bottom: Int) {
		if (orientation == RecyclerView.VERTICAL && child.layoutParams.height == LayoutParams.MATCH_PARENT) {
			val parentBottom = height - paddingBottom
			val offset = parentBottom - bottom
			super.layoutDecoratedWithMargins(child, left, top, right, bottom + offset)
		} else {
			super.layoutDecoratedWithMargins(child, left, top, right, bottom)
		}
	}
}

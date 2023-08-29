package com.example.storeapp.shared

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Attach a [SwipeDeleteListener] to this [RecyclerView].
 *
 * @param onSwipe action to take when an item is swiped.
 */
fun RecyclerView.addSwipeDeleteListener(
    onSwipe: (Int) -> Unit
) {
    val listener = SwipeDeleteListener(onSwipe)
    ItemTouchHelper(listener).attachToRecyclerView(this)
}
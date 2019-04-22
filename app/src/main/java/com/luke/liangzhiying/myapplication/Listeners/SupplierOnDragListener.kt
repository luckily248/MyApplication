package com.luke.liangzhiying.myapplication.Listeners

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class SupplierOnDragListener() : OnDragListener{
    override fun onDrag(v: View, event: DragEvent): Boolean {
        // Handles each of the expected events
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                // Determines if this View can accept the dragged data
 //               if (event.clipData) {
                    // As an example of what your application might do,
                    // applies a blue color tint to the View to indicate that it can accept
                    // data.
                    Log.d(this.toString(),"dargstart")
                    (v as? LinearLayout)?.setBackgroundColor(Color.BLUE)

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate()

                    // returns true to indicate that the View can accept the dragged data.
                    true
//                } else {
//                    // Returns false. During the current drag and drop operation, this View will
//                    // not receive events again until ACTION_DRAG_ENDED is sent.
//                    false
//                }
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                // Applies a green tint to the View. Return true; the return value is ignored.
                (v as? LinearLayout)?.setBackgroundColor(Color.GREEN)

                // Invalidate the view to force a redraw in the new tint
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                // Ignore the event
                true
            DragEvent.ACTION_DRAG_EXITED -> {
                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                (v as? LinearLayout)?.setBackgroundColor(Color.BLUE)

                // Invalidate the view to force a redraw in the new tint
                v.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                // Gets the item containing the dragged data
                val item: ClipData.Item = event.clipData.getItemAt(0)

                // Gets the text data from the item.
                val dragData = item.text

                // Displays a message containing the dragged data.
                Toast.makeText(v.context, "Dragged data is " + dragData, Toast.LENGTH_LONG).show()

                // Turns off any color tints
                (v as? LinearLayout)?.setBackgroundColor(Color.WHITE)

                // Invalidates the view to force a redraw
                v.invalidate()

                // Returns true. DragEvent.getResult() will return true.
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                // Turns off any color tinting
                (v as? LinearLayout)?.setBackgroundColor(Color.WHITE)

                // Invalidates the view to force a redraw
                v.invalidate()

                // Does a getResult(), and displays what happened.
                when (event.result) {
                    true ->
                        Toast.makeText(v.context, "The drop was handled.", Toast.LENGTH_LONG)
                    else ->
                        Toast.makeText(v.context, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()

                // returns true; the value is ignored.
                true
            }
            else -> {
                // An unknown action type was received.
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
                false
            }
        }
        return false
    }
}
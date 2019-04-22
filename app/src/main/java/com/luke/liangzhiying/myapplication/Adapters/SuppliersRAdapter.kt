package com.luke.liangzhiying.myapplication.Adapters

import android.content.ClipData
import android.content.ClipDescription
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.luke.liangzhiying.myapplication.Builders.SupplierDragShadowBuilder
import com.luke.liangzhiying.myapplication.Listeners.SupplierOnDragListener
import com.luke.liangzhiying.myapplication.Model.Suppler
import com.luke.liangzhiying.myapplication.R

class SuppliersRAdapter(private val list: List<Suppler>) :
        RecyclerView.Adapter<SuppliersRAdapter.SuppliersRViewHolder>() {
    class SuppliersRViewHolder(val itemview: LinearLayout) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuppliersRViewHolder {
        val itemview = LayoutInflater.from(parent.context)
                .inflate(R.layout.supplieritem, parent, false) as LinearLayout

        return SuppliersRViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: SuppliersRViewHolder, position: Int) {
        var name = holder.itemview.findViewById<TextView>(R.id.suppliername)
        var type = holder.itemview.findViewById<TextView>(R.id.suppliertype)
        name.setText(list[position].name)
        var typename = "none"
        /**
        0
        manu.setText("Brand")
        break
        1
        manu.setText("Distributor")
        break
        else
        manu.setText("others")
        **/
        when{
            list[position].type==0 -> typename = "Brand"
            list[position].type==1 -> typename = "Distributor"
            else -> typename = "others"
        }
        type.setText(typename)

        holder.itemView.apply {
            tag = "Supplier"
            setOnLongClickListener { v: View ->
                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                val data = ClipData.newPlainText(v.tag as CharSequence,list[position].id)

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                /*val dragData = ClipData(
                        v.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        item)
                */
                // Instantiates the drag shadow builder.
                val myShadow = SupplierDragShadowBuilder(this);

                // Starts the drag
                v.startDrag(
                        data,   // the data to be dragged
                        myShadow,   // the drag shadow builder
                        null,       // no need to use local data
                        0           // flags (not currently used, set to 0)
                )
            }
        }
    }

    override fun getItemCount(): Int = list.size
}
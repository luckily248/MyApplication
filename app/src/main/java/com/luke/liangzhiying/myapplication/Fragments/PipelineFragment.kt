package com.luke.liangzhiying.myapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.luke.liangzhiying.myapplication.Adapters.PipelineAdapter
import com.luke.liangzhiying.myapplication.Adapters.SuppliersRAdapter
import com.luke.liangzhiying.myapplication.Listeners.SupplierOnDragListener
import com.luke.liangzhiying.myapplication.Model.Suppler
import com.luke.liangzhiying.myapplication.R
import com.luke.liangzhiying.myapplication.Tasks.GetSupplers
import com.luke.liangzhiying.myapplication.Tasks.GetSuppliers
import java.util.ArrayList

class PipelineFragment() : Fragment() {
    var supplerList = ArrayList<Suppler>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.pipelinerecyleview, container, false)
        if (view is ListView) {
            var adapter = PipelineAdapter(activity, supplerList);
            view.adapter = adapter
            GetSupplers(activity, supplerList, adapter).execute("https://vendorcrm.mybluemix.net/suppliers")
        }else if(view is LinearLayout){

            var tocn = view.findViewById<TextView>(R.id.tocn)
            var scn = view.findViewById<TextView>(R.id.scn)
            var srn = view.findViewById<TextView>(R.id.srn)
            var arn = view.findViewById<TextView>(R.id.arn)
            var asn = view.findViewById<TextView>(R.id.asn)

            var toclist = view.findViewById<RecyclerView>(R.id.toclist)
            var sclist = view.findViewById<RecyclerView>(R.id.sclist)
            var srlist = view.findViewById<RecyclerView>(R.id.srlist)
            var arlist = view.findViewById<RecyclerView>(R.id.arlist)
            var aslist = view.findViewById<RecyclerView>(R.id.aslist)

            //toc sc sr ar as
            var textviewlist = arrayOf(tocn,scn,srn,arn,asn)
            var recyclerviewlist = arrayOf(toclist,sclist,srlist,arlist,aslist)
            var grouplist = Array(5,{i->ArrayList<Suppler>()})
            var adapterlist = Array(5,{i->SuppliersRAdapter(grouplist[i])})
            var managerlist = Array(5,{i->LinearLayoutManager(activity)})
            for(i in 0..4){
                setupRecyleview(recyclerviewlist[i],managerlist[i],adapterlist[i])
            }
            GetSuppliers(textviewlist,grouplist,adapterlist).execute("https://vendorcrm.mybluemix.net/suppliers")
        }
        return view
    }
    fun setupRecyleview(recyclerView: RecyclerView,layoutManager: LinearLayoutManager,supplieradapter:SuppliersRAdapter):RecyclerView{
        return recyclerView.apply {
            setHasFixedSize(true)
            layoutManager.orientation = RecyclerView.HORIZONTAL
            this.layoutManager = layoutManager
            this.adapter = supplieradapter
            setOnDragListener(SupplierOnDragListener())
        }

    }
}
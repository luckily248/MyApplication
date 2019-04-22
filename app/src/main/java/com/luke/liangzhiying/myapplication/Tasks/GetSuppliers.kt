package com.luke.liangzhiying.myapplication.Tasks

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luke.liangzhiying.myapplication.Adapters.SuppliersRAdapter
import com.luke.liangzhiying.myapplication.Model.Suppler
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class GetSuppliers(val textviewlist:Array<TextView>,val grouplist:Array<ArrayList<Suppler>>,val adapterlist:Array<SuppliersRAdapter>) : AsyncTask<String, Int, ArrayList<Suppler>>() {

    override fun doInBackground(vararg params: String?): ArrayList<Suppler>? {
        var connection: HttpURLConnection? = null
        var reader: BufferedReader? = null


        try {
            val url = URL(params[0])
            connection = url.openConnection() as HttpURLConnection
            connection.connect()

            val `is` = connection.inputStream
            reader = BufferedReader(InputStreamReader(`is`))

            /*val sb = StringBuffer()
            var line = ""

            while (line = reader.readLine()) {
                sb.append(line + "\n")
                Log.d("Response:", ">$line")
            }
            */
            var responsecontent = reader.use { it.readText() }
            Log.d("Response:", responsecontent)
            val listType = object : TypeToken<ArrayList<Suppler>>() {}.type
            return Gson().fromJson<ArrayList<Suppler>>(responsecontent, listType)

        } catch (e:IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
        // ...
    }

    override fun onPostExecute(list: ArrayList<Suppler>?) {
        if (list != null) {
            //toc sc sr ar as
            var mapsupplier = list.groupBy { it.pstate }
            for(i in 0..4) {
                textviewlist[i].setText(mapsupplier[i]?.size.toString())
                grouplist[i].clear()
                grouplist[i].addAll(mapsupplier[i] as List<Suppler>)
                adapterlist[i].notifyDataSetChanged()
                Log.d("model", "count:" + adapterlist[i].itemCount)
            }
            //Log.d("model","name"+supplerList.get(0).getName());
            //pa.notifyDataSetChanged()
        }
    }
}
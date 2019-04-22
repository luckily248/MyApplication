package com.luke.liangzhiying.myapplication.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luke.liangzhiying.myapplication.Adapters.PipelineAdapter;
import com.luke.liangzhiying.myapplication.Model.Suppler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetSupplers extends AsyncTask<String,Integer, List<Suppler>> {
    private Context context;
    private List<Suppler> supplerList;
    private ArrayAdapter pa;

    public GetSupplers(Context context, List<Suppler> supplerList, ArrayAdapter pa) {
        this.context=context;
        this.supplerList = supplerList;
        this.pa = pa;
    }

    @Override
    protected List<Suppler> doInBackground(String... urls) {
        HttpURLConnection connection=null;
        BufferedReader reader=null;


        try {
            URL url=new URL(urls[0]);
            connection= (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream is=connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));

            StringBuffer sb=new StringBuffer();
            String line="";

            while ((line=reader.readLine())!=null) {
                sb.append(line + "\n");
                Log.d("Response:", ">" + line);
            }

            Type listType=new TypeToken<ArrayList<Suppler>>(){}.getType();
            List<Suppler> lists=new Gson().fromJson(sb.toString(),listType);
            return lists;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Suppler> list) {
        if(list!=null){
            supplerList.clear();
            supplerList.addAll(list);
            //Log.d("model","name"+supplerList.get(0).getName());
            pa.notifyDataSetChanged();
            Log.d("model","count"+pa.getCount());
        }
    }
}

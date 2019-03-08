package com.example.liangzhiying.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.liangzhiying.myapplication.Model.Suppler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mIndex;
    private LinearLayout mHome;
    private ListView mPipeline;
    private PipelineAdapter pa;
    private List<Suppler> supplerList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mIndex.removeAllViews();
                    mIndex.addView(mHome);
                    return true;
                case R.id.navigation_dashboard:
                    mIndex.removeAllViews();
                    mIndex.addView(mPipeline);
                    new GetSupplers(getApplicationContext()).execute("https://vendorcrm.mybluemix.net/suppliers");
                    return true;
                case R.id.navigation_notifications:
                    mIndex.removeAllViews();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIndex = (RelativeLayout) findViewById(R.id.index);
        mHome= (LinearLayout) getLayoutInflater().inflate(R.layout.home,null);
        mPipeline=(ListView) getLayoutInflater().inflate(R.layout.pipeline,null);
        supplerList=new ArrayList<Suppler>();
        supplerList.add(new Suppler());
        pa=new PipelineAdapter(getApplicationContext(),supplerList);
        mPipeline.setAdapter(pa);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private class PipelineAdapter extends ArrayAdapter<Suppler> {
        private List<Suppler> supplerList;
        private Context context;
        public PipelineAdapter(Context context,List<Suppler> supplerList) {
            super(context, R.layout.supplierview, supplerList);
            this.context = context;
            this.supplerList = supplerList;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Suppler item=supplerList.get(i);
            if (view == null){
                view = (LinearLayout) getLayoutInflater().inflate(R.layout.supplierview, null);
            }
            TextView name=(TextView)view.findViewById(R.id.name);
            TextView manu=(TextView)view.findViewById(R.id.manu);
            TextView phone=(TextView)view.findViewById(R.id.phone);
            TextView email=(TextView)view.findViewById(R.id.email);
            name.setText(item.getName());
            switch(item.getType()){
                case 0:
                    manu.setText("Brand");
                    break;
                case 1:
                    manu.setText("Distributor");
                    break;
                default:
                    manu.setText("others");
            }
            phone.setText(item.getPhone());
            email.setText(item.getEmail());
            return view;
        }
    };

    private class GetSupplers extends AsyncTask<String,Integer, List<Suppler>>{
        Context context;

        public GetSupplers(Context context) {
            this.context=context;
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
                supplerList.remove(0);
                supplerList.addAll(list);
               // Log.d("model","name"+supplerList.get(0).getName());

                pa.notifyDataSetChanged();
                Log.d("model","count"+pa.getCount());
            }
        }
    }
    private class pipelineDateObserver extends DataSetObserver{
        public pipelineDateObserver() {

        }
    }

}

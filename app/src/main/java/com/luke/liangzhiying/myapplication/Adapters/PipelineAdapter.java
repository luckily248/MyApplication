package com.luke.liangzhiying.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luke.liangzhiying.myapplication.Model.Suppler;
import com.luke.liangzhiying.myapplication.R;

import java.util.List;

public class PipelineAdapter extends ArrayAdapter<Suppler> {
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.supplierview, null);
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

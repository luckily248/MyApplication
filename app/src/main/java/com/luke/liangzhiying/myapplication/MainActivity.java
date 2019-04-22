package com.luke.liangzhiying.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.luke.liangzhiying.myapplication.Fragments.HomeFrament;
import com.luke.liangzhiying.myapplication.Fragments.PipelineFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectFragment = new HomeFrament();
                    break;
                case R.id.navigation_dashboard:
                    selectFragment = new PipelineFragment();
                    break;
                case R.id.navigation_notifications:
                    break;
            }
            if(selectFragment!=null) {
                Log.d("Fragment","getFragment:"+selectFragment.getClass().getSimpleName());
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.index, selectFragment);
                fragmentTransaction.commit();
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.index, new HomeFrament());
        fragmentTransaction.commit();
    }


}

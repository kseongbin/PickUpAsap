package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments = new Fragment[3];

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments[0] = new Home_Fragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction tran = fragmentManager.beginTransaction();
                tran.hide(fragments[0]);
                if (fragments[1]!=null) tran.hide(fragments[1]);
                if (fragments[2]!=null) tran.hide(fragments[2]);

                tran.hide(fragments[0]);
                if (fragments[1]!=null) tran.hide(fragments[1]);
                if (fragments[2]!=null) tran.hide(fragments[2]);

                switch (item.getItemId()){
                    case R.id.bnv_home:
                        tran.show(fragments[0]);
                        break;

                    case R.id.bnv_receipt:
                        if (fragments[1]==null){
                            fragments[1]= new Receipt_Fragment();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        break;


                    case R.id.bnv_login:
                        if (fragments[2]==null){
                            fragments[2]= new Login_Fragment();
                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        break;
                }

                tran.commit();

                return true;
            }
        });

    }
}
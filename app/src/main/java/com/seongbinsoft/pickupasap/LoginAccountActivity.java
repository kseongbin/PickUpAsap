package com.seongbinsoft.pickupasap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);
    }

    public void ClickLogin(View view) {
        Intent intent = new Intent(this, Home_Fragment.class);
        finish();
    }
}
package com.seongbinsoft.pickupasap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginAccountActivity extends AppCompatActivity {

    TextView tv;
    CircleImageView civ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);

        tv = findViewById(R.id.logaccount_nickname);
        civ = findViewById(R.id.logaccount_civ);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        String profileUrl = intent.getStringExtra("profileUrl");
        tv.setText(nickname);
        Glide.with(this).load(profileUrl).into(civ);
    }

    public void ClickLogin(View view) {
        //Intent intent = new Intent(this, Home_Fragment.class);
        //startActivity(intent);
        finish();
    }
}
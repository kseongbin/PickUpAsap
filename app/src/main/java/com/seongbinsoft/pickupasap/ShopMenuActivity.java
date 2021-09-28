package com.seongbinsoft.pickupasap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ShopMenuActivity extends AppCompatActivity {

    RadioGroup rg;
    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);

        rg = findViewById(R.id.rg);
        iv = findViewById(R.id.menu_iv);
        tv = findViewById(R.id.tv_menu);

        Intent intent = getIntent();
        String name = getIntent().getStringExtra("name");
        String img = getIntent().getStringExtra("img");
        tv.setText(name);
        Glide.with(this).load(img).into(iv);
    }



    public void clickBuy(View view) {

        int id = rg.getCheckedRadioButtonId();
        RadioButton rb = findViewById(id);
        Toast.makeText(this, rb.getText().toString()+"을 구매하셨습니다.", Toast.LENGTH_SHORT).show();

    }

    public void clickBack(View view) {
        onBackPressed();
    }
}
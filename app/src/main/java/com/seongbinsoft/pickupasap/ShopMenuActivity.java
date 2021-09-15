package com.seongbinsoft.pickupasap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ShopMenuActivity extends AppCompatActivity {

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);

        rg = findViewById(R.id.rg);
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
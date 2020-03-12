package com.example.kheetemate;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Selectportal extends AppCompatActivity implements View.OnClickListener {
    ImageButton farmerpo,buyerpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectportal);
        farmerpo=(ImageButton) findViewById(R.id.farmerpo);
        buyerpo=(ImageButton) findViewById(R.id.buyerpo);
        farmerpo.setOnClickListener(this);
        buyerpo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.farmerpo:
                Intent intent=new Intent(Selectportal.this,farmerotp.class);
                startActivity(intent);
                break;
            case R.id.buyerpo:
                Intent intent1=new Intent(Selectportal.this,buyerotp.class);
                startActivity(intent1);
                break;
        }
    }

}
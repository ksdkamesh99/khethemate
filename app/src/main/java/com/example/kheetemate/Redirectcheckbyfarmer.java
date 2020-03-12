package com.example.kheetemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Redirectcheckbyfarmer extends AppCompatActivity {
        TextView homeclick;
        Button doe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirectcheckbyfarmer);
        homeclick=(TextView) findViewById(R.id.homeclick);
        doe=(Button) findViewById(R.id.doe);
        doe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Redirectcheckbyfarmer.this,Paymentcheckout.class);
                startActivity(ii);
            }
        });

        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Redirectcheckbyfarmer.this,CostumerActivity.class);
                startActivity(ii);
            }
        });
    }
}

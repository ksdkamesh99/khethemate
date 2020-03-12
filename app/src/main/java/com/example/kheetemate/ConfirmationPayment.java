package com.example.kheetemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationPayment extends AppCompatActivity {
    TextView status,transaction,payment;
    ImageView imagecheck;
    String a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_payment);
        Intent ii=getIntent();
        a=ii.getStringExtra("status");
         b=ii.getStringExtra("transactionid");
         c=ii.getStringExtra("referenceid");
         imagecheck=(ImageView) findViewById(R.id.imagecheck) ;

        status=(TextView) findViewById(R.id.status);
        transaction=(TextView) findViewById(R.id.transaction);
        payment=(TextView) findViewById(R.id.payment);
        Log.e("UPI",a);
        if(a.equals("SUCCESS")){
            Toast.makeText(ConfirmationPayment.this, "Transaction successful.", Toast.LENGTH_LONG).show();
            status.setText(a);
            transaction.setText(b);
            payment.setText(c);
            imagecheck.setVisibility(View.VISIBLE);
           imagecheck.setImageResource(R.drawable.save);




        }
        else{
            Toast.makeText(ConfirmationPayment.this, "Transaction Failed.", Toast.LENGTH_LONG).show();
            status.setText(a);
            transaction.setText(b);
            payment.setText(c);
            imagecheck.setVisibility(View.VISIBLE);
           imagecheck.setImageResource(R.drawable.lose);


        }
    }
}

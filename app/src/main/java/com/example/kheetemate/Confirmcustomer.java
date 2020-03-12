package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Confirmcustomer extends AppCompatActivity {
    EditText quanti,pric;
    Button proceed1,back1;
   String quantity,pricefixed;
    private final int MY_PERMISSIONS_REQUEST_CODE = 1;
    String imageUrl,CateGory,ProDuct,DesCription,price,AddRess,NaMe,Phonenum;
    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSIONS_REQUEST_CODE) {
            return;
        }
        boolean isGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }
        if (isGranted) {

                Intent intent=new Intent(getApplicationContext(),Redirectcheckbyfarmer.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

//Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(Phonenum,null,"hiiiiii",pi,null);
            Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                    Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();

        }
    }

    private void setPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_CODE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmcustomer);
        quanti=(EditText) findViewById(R.id.quanti);
        pric=(EditText) findViewById(R.id.pric);
        proceed1=(Button) findViewById(R.id.proceed1);
        back1=(Button) findViewById(R.id.back1);
        quantity=quanti.getText().toString();
        pricefixed=pric.getText().toString();
        proceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissions()) {

                    if(getIntent().hasExtra("image_url")){
                        imageUrl=getIntent().getStringExtra("image_url");

                        NaMe=getIntent().getStringExtra("Name");
                        Phonenum=getIntent().getStringExtra("phone");}


                        Intent intent=new Intent(getApplicationContext(),Redirectcheckbyfarmer.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

//Get the SmsManager instance and call the sendTextMessage method to send message
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(Phonenum,null,"hiii",pi,null);
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                            Toast.LENGTH_LONG).show();

                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Confirmcustomer.this);
                    builder.setMessage("msg");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setPermissions();
                        }
                    });
                    builder.show();
                }


            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Confirmcustomer.this,CostumerActivity.class);
                startActivity(ii);
            }
        });



    }

}

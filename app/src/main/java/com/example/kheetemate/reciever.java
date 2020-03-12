package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reciever extends AppCompatActivity {

    TextView xyz;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);
        xyz = findViewById(R.id.msg_txt);
        ref= FirebaseDatabase.getInstance().getReference().child("Human");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS)
                == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},4777);
        }
        Intent get_msg = getIntent();
        String msg_found = get_msg.getStringExtra("MESSAGE");
        if(msg_found != null){
            String[] msg_break = msg_found.split("\n");
            if(msg_break[0].equals("farmer_help")){
                for(int i=1;i<msg_break.length;i++){
                    String[] arr = msg_break[i].split(":");
                    ref.child(arr[0]).setValue(arr[1]);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 4777){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"PERMISSION GRANTED!!",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"PERMISSION DENIED!!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

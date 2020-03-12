package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Buyers1 extends AppCompatActivity {
    ImageButton imageButton,imageButton1,imageButton2;
    TextView Rice,Paddy,Wheat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyers1);
        imageButton=findViewById(R.id.RiceButt);
        imageButton1=findViewById(R.id.PaddyButt);
        imageButton2=findViewById(R.id.WheatButt);
        Rice=findViewById(R.id.RiceText);
        Paddy=findViewById(R.id.PaddyText);
        Wheat=findViewById(R.id.WheatText);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Buyers1.this,FarmersListActivity.class);
                intent.putExtra("Yo","rice");
                startActivity(intent);
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Buyers1.this,FarmersListActivity.class);
                intent.putExtra("Yo","paddy");
                startActivity(intent);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Buyers1.this,FarmersListActivity.class);
                intent.putExtra("Yo","wheat");
                startActivity(intent);
            }
        });

    }

}

package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.List;

public class Myordercostumer extends AppCompatActivity {
    ArrayList<farmer> mapping = new ArrayList<farmer>();
    RecyclerView recyclerView;
    //AdapterMyorderpro mAdapter;
    NoteAdapter noteAdapter;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myordercostumer);
        recyclerView = (RecyclerView) findViewById(R.id.recy);
       // mAdapter = new AdapterMyorderpro(this,mapping);

       // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
       // recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference().child("Farmers").child("rice");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               /* String Name=valueOf(dataSnapshot.child("Farmers").child("rice").child("naMe").getValue());
                String ProductDet=valueOf(dataSnapshot.child("Farmers").child("rice").child("proDuct").getValue());
                String Quan=valueOf(dataSnapshot.child("Farmers").child("rice").child("descripTion").getValue());
                String Price=valueOf(dataSnapshot.child("Farmers").child("rice").child("priCe").getValue());
                String Lat=valueOf(dataSnapshot.child("Farmers").child("rice").child("laT").getValue());
                String Lng=valueOf(dataSnapshot.child("Farmers").child("rice").child("lnG").getValue());*/
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    mapping.add(dataSnapshot1.getValue(farmer.class));

                }
                noteAdapter=new NoteAdapter(Myordercostumer.this,mapping);
                recyclerView.setAdapter(noteAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

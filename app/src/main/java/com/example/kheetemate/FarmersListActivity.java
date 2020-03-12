package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static java.lang.String.valueOf;
import static java.util.Locale.getDefault;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FarmersListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref,ref1,ref2;
    ImageButton imageButton;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter1;
    ArrayList<far> farmerArrayList,farmerArrayList1,farmerArrayList2;
    NoteAdapter1 noteAdapter1;
    static double currlat,currlong;
    far farm;
    String s;
    LocationManager lm;
    LocationListener ls;
    String Name,Product,Price,lat,lng,category,address,phone,image,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers_list);
        farm = new far();
        Intent intent = getIntent();
        s = intent.getStringExtra("Yo");
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        ls = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currlat = location.getLatitude();
                currlong = location.getLongitude();


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            } else {
                configb();
            }
        }

        farmerArrayList = new ArrayList<far>();
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref1 = FirebaseDatabase.getInstance().getReference().child("Farmers").child(s);
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    //String Name=valueOf(dataSnapshot1.getValue());

                    String Name = valueOf(dataSnapshot1.child("naMe").getValue());
                    String Product = valueOf(dataSnapshot1.child("proDuct").getValue());
                    String Price = valueOf(dataSnapshot1.child("priCe").getValue());
                    String lat = valueOf(dataSnapshot1.child("laT").getValue());
                    String lng = valueOf(dataSnapshot1.child("lnG").getValue());
                    String category = valueOf(dataSnapshot1.child("cateGory").getValue());
                    String address = valueOf(dataSnapshot1.child("addRess").getValue());
                    String phone = valueOf(dataSnapshot1.child("phoneNuM").getValue());
                    String image = valueOf(dataSnapshot1.child("imaGe").getValue());
                    String description = valueOf(dataSnapshot1.child("descripTion").getValue());
                    farmerArrayList.add(new far(Name, Product, Price, lat, lng, String.valueOf(currlat), String.valueOf(currlong), category, address, phone, image, description));
                }
                noteAdapter1 = new NoteAdapter1(FarmersListActivity.this, farmerArrayList);
                recyclerView.setAdapter(noteAdapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configb();
                }
        }
    }
    private void configb() {

                long milliseconds = 3000; // 5 seconds
                float minimusDistance = (float) 0;
                try {
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, milliseconds,minimusDistance, ls);




                }
                catch (SecurityException se){
                    se.printStackTrace();
                }


    }
}

package com.example.kheetemate;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class CostumerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
String lat,lng;
static String userId;
DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costumer);
        Intent intent1=getIntent();
        lat=intent1.getStringExtra("LatCus");
        lng=intent1.getStringExtra("LngCus");
        ref= FirebaseDatabase.getInstance().getReference().child("Custom");
        farmer farm=new farmer();
        farm.setLaT(lat);
        farm.setLnG(lng);
        userId=ref.push().getKey();
        ref.child(userId).setValue(farm);
        Toolbar toolbar = findViewById(R.id.toolbar_1);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab_1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout_1);
        NavigationView navigationView = findViewById(R.id.nav_view_1);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_1)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_1);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()==R.id.nav_home_1){
                    Toast.makeText(CostumerActivity.this, "yo", Toast.LENGTH_SHORT).show();
                }
                if(destination.getId()==R.id.mainActivity4){
                    Intent intent=new Intent(CostumerActivity.this,CardView.class);
                    startActivity(intent);
                }
                if(destination.getId()==R.id.mainActivity5){
                    Intent intent=new Intent(CostumerActivity.this,MainActivity2.class);
                    startActivity(intent);
                }
                if(destination.getId()==R.id.mainActivity6){
                    Intent intent=new Intent(CostumerActivity.this,Myordercostumer.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.costumer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i=item.getItemId();
        if(i==R.id.log){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(CostumerActivity.this,Selectportal.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_1);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public  String getUserId(){
        return  userId;
    }
}

package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Description extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    ImageView imageView;
    ImageButton imageButton;
    TextView Category,Product,Description,Price,Address,Name,PhoneNum;
    Button proceed,goback;
    String Phonenum1;
    String imageUrl,CateGory,ProDuct,DesCription,price,AddRess,NaMe;
    private final int MY_PERMISSIONS_REQUEST_CODE = 1;
    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        imageButton=findViewById(R.id.imageButton);
        imageView=findViewById(R.id.RetrievedImage);
        Category=findViewById(R.id.CATEGORY);
        Product=findViewById(R.id.PRODUCT);
        proceed=(Button) findViewById(R.id.proceed);
        goback=(Button) findViewById(R.id.goback);
        Description=findViewById(R.id.DESCRIPTION);
        Price=findViewById(R.id.PRICE);
        Address=findViewById(R.id.ADDRESS);
        Name=findViewById(R.id.NAME);
        PhoneNum=findViewById(R.id.PHONENUM);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);


                    callIntent.setData(Uri.parse("tel:" + Phonenum1));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    Activity#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for Activity#requestPermissions for more details.
                            return;
                        }
                    }
                    startActivity(callIntent);


                } else {
                    setPermissions();
                }
            }
        });
        getIncomingIntent();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiii = new Intent(Description.this,Confirmcustomer.class);
                iiii.putExtra("image_url",imageUrl);
                iiii.putExtra("name",NaMe);
                iiii.putExtra("phone",Phonenum1);
                startActivity(iiii);
            }
        });


    }
    public void getIncomingIntent(){
        if(getIntent().hasExtra("image_url")){
            imageUrl=getIntent().getStringExtra("image_url");
            CateGory=getIntent().getStringExtra("Category");
             ProDuct=getIntent().getStringExtra("Product");
            DesCription=getIntent().getStringExtra("Description");
            price=getIntent().getStringExtra("Price");
             AddRess=getIntent().getStringExtra("Address");
             NaMe=getIntent().getStringExtra("Name");
           Phonenum1=getIntent().getStringExtra("PhoneNum");
            setDescription(imageUrl,CateGory,ProDuct,DesCription,price,AddRess,NaMe,Phonenum1);
        }
    }

    private void setDescription(String imageUrl, String cateGory, String proDuct, String desCription, String price, String addRess, String naMe, String phonenum) {
        Picasso.get().load(imageUrl).into(imageView);
        Category.setText("Category:     "+cateGory);
        Product.setText("Product Name:    "+proDuct);
        Description.setText("Description:     "+desCription);
        Price.setText("Price:     "+price);
        Address.setText("Address:     "+addRess);
        Name.setText("Name:     "+naMe);
        PhoneNum.setText("Phone Number:     "+phonenum);


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
            String number = PhoneNum.getText().toString();
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            startActivity(callIntent);

        } else {
            Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();

        }
    }

    private void setPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CODE);
    }
}

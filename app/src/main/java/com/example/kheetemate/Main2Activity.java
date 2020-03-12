package com.example.kheetemate;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kheetemate.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    ImageView image;
    Button select,go,goToCardView;
    Uri uriImage;
    LocationManager lm;
    LocationListener ls;
    EditText Name,PhoneNum,State,Product,Address,Description,Price,Category;
    StorageReference storageReference;
    DatabaseReference ref,ref1,ref2,ref3,ref4,ref5;
    farmer farm;
    String userid,userid1,userid2;
    StorageTask uploadTask;
    Spinner spinner;
    static  double currlat,currlong;
    String CategoryWise[]={"Grain","Other","Animal","HortiCulture","Agriculture","Organic"};
    String CategoryWise1[]={"Rice","Paddy","Wheat"};
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //   ActionBar actionBar=getSupportActionBar();
        //   actionBar.setTitle("Farmers");

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        ls = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currlat=location.getLatitude();
                currlong=location.getLongitude();



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
        image=findViewById(R.id.Image);
        select=findViewById(R.id.SelectImage);
        Name=findViewById(R.id.name);
        // gotoBuyer=findViewById(R.id.GotoBuyer);
        goToCardView=findViewById(R.id.GotoCard);
        PhoneNum=findViewById(R.id.PhoneNum);
        State=findViewById(R.id.State);
        Product=findViewById(R.id.Product);
        spinner=findViewById(R.id.spinner1);

        Address=findViewById(R.id.Address);
        Description=findViewById(R.id.Description);
        Category=findViewById(R.id.Category);
        Price=findViewById(R.id.Price);
        go=findViewById(R.id.Go);
        farm=new farmer();
        goToCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this, HomeFragment.class);
                startActivity(intent);
            }
        });


        ArrayAdapter<String > adapter=new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_spinner_item,CategoryWise);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Category.setText("Grain");
                        break;
                    case 1:
                        Category.setText("Other");
                        break;
                    case 2:
                        Category.setText("Animal");
                        break;
                    case 3:
                        Category.setText("Horticulture");
                        break;
                    case 4:
                        Category.setText("Agriculture");
                        break;
                    case 5:
                        Category.setText("Organic");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        storageReference= FirebaseStorage.getInstance().getReference("image");
        ref= FirebaseDatabase.getInstance().getReference("Farmers");
        ref1= FirebaseDatabase.getInstance().getReference("Farmers1");
        ref2= FirebaseDatabase.getInstance().getReference("Farmers2");
        ref3= FirebaseDatabase.getInstance().getReference("Farmers3");
        ref4= FirebaseDatabase.getInstance().getReference("Farmers4");
        ref5= FirebaseDatabase.getInstance().getReference("Farmers5");
      /*  gotoBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,Buyers.class);
                startActivity(intent);
            }
        });*/
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoGallery();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name1 = Name.getText().toString();
                String PhoneNum1 = PhoneNum.getText().toString();
                String State1 = State.getText().toString();
                String Product1 = Product.getText().toString();
                String Address1 = Address.getText().toString();
                String Description1 = Description.getText().toString();
                String Price1 = Price.getText().toString();
                String Category1 = Category.getText().toString();
                if (Name1.isEmpty() || PhoneNum1.isEmpty() || State1.isEmpty() || Product1.isEmpty() || Address1.isEmpty() || Description1.isEmpty() || Price1.isEmpty() || Category1.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Don't leave Anything Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (PhoneNum1.length() != 10) {
                        Toast.makeText(Main2Activity.this, "Incorrect Phone Number", Toast.LENGTH_SHORT).show();
                    }
                    if (uploadTask != null && uploadTask.isInProgress()) {
                        Toast.makeText(Main2Activity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                    } else {
                        //FileUploader();

                        // farm.setImaGe(image);
                        //Toast.makeText(MainActivity.this, "image uploaded successfully", Toast.LENGTH_SHORT).show();
                        FileUploader();
                        //ref.child(Name1).setValue(farm);

                    }
                }
            }
        });
    }
    private String Extension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void FileUploader() {
        String imageId=System.currentTimeMillis()+"."+Extension(uriImage);
        StorageReference Ref= storageReference.child(imageId);
        Ref.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String Name1 = Name.getText().toString();
                String PhoneNum1 = PhoneNum.getText().toString();
                String State1 = State.getText().toString();
                String Product1 = Product.getText().toString();
                String Address1 = Address.getText().toString();
                String Description1 = Description.getText().toString();
                //String image=uriImage.toString();
                String Price1 = Price.getText().toString();
                String Category1 = Category.getText().toString();
                farm.setNaMe(Name1);
                farm.setPhoneNuM(PhoneNum1);
                farm.setStaTe(State1);
                farm.setProDuct(Product1);
                farm.setAddRess(Address1);
                farm.setDescripTion(Description1);
                farm.setPriCe(Price1);
                farm.setCateGory(Category1);
                farm.setLaT(String.valueOf(currlat));
                farm.setLnG(String.valueOf(currlong));
                //String imageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
                if (Category1.equals("Grain")&&(Product1.equals("Rice")||Product1.equals("rice"))) {
                    Toast.makeText(Main2Activity.this, "Excellent", Toast.LENGTH_SHORT).show();
                    userid = ref.push().getKey();
                    final Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();
                            farm.setImaGe(imageUrl);
                            String Product1 = Product.getText().toString();
                            //ref.child(userid).setValue(farm);
                            ref.child(Product1).child(userid).setValue(farm);
                            Toast.makeText(Main2Activity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Main2Activity.this,HomeFragment.class);
                            startActivity(intent);
                        }
                    });


                } if (Category1.equals("Grain")&&(Product1.equals("Paddy")||Product1.equals("paddy"))) {
                    userid = ref.push().getKey();

                    final Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();
                            farm.setImaGe(imageUrl);
                            String Product1 = Product.getText().toString();
                            Toast.makeText(Main2Activity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                            ref.child(Product1).child(userid).setValue(farm);
                            Intent intent=new Intent(Main2Activity.this,HomeFragment.class);
                            startActivity(intent);

                        }
                    });


                }
                if (Category1.equals("Grain")&&(Product1.equals("Wheat")||Product1.equals("wheat"))) {
                    userid = ref.push().getKey();

                    final Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();
                            farm.setImaGe(imageUrl);
                            String Product1 = Product.getText().toString();
                            Toast.makeText(Main2Activity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                            ref.child(Product1).child(userid).setValue(farm);
                            Intent intent=new Intent(Main2Activity.this,HomeFragment.class);
                            startActivity(intent);

                        }
                    });


                }

            }

        });


    }


    private void GotoGallery() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            try{
                uriImage = data.getData();

                InputStream inputStream = getContentResolver().openInputStream(uriImage);
                Bitmap imageMap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(imageMap);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Image was not found", Toast.LENGTH_SHORT).show();

            }

        }


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
    public  String GetUserId(){
        return  userid;
    }
}
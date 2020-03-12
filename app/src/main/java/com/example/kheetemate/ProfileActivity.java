package com.example.kheetemate;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

public class ProfileActivity extends AppCompatActivity {
    ImageView image;
    Button select,go,gotoBuyer,goToCardView;
    Uri uriImage;
    EditText Name,PhoneNum,Address,District,Pin,City,State,Adhar,Upid;
    StorageReference storageReference;
    DatabaseReference ref,ref1,ref2;
    farmer farm;
    String Activity;
    static  String Name1;
    static String userid,PhoneNum1,str;
    StorageTask uploadTask;
    Spinner spinner;
    // String CategoryWise[]={"Grain","Seed","Animal"};
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Activity=getIntent().getStringExtra("login1");
        //     ActionBar actionBar=getSupportActionBar();
//        actionBar.setTitle("Farmers");
        //  image=findViewById(R.id.Image);
        // select=findViewById(R.id.SelectImage);
        Name=findViewById(R.id.name);
        // gotoBuyer=findViewById(R.id.GotoBuyer);
        goToCardView=findViewById(R.id.GotoCard);
        PhoneNum=findViewById(R.id.PhoneNum);
        Address=findViewById(R.id.address1);
        District=findViewById(R.id.district1);
        Pin=findViewById(R.id.pin1);
        City=findViewById(R.id.city1);
        State=findViewById(R.id.state1);
        Adhar=findViewById(R.id.adhar1);
        Upid=findViewById(R.id.upid1);
        //State=findViewById(R.id.State1)
        go=findViewById(R.id.Go);
        farm=new farmer();
        /*goToCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,CardView.class);
                startActivity(intent);
            }
        });*/
       /* ArrayAdapter<String > adapter=new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_spinner_item,CategoryWise);
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
                        Category.setText("Seed");
                        break;
                    case 2:
                        Category.setText("Animal");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        //storageReference= FirebaseStorage.getInstance().getReference("image");
        ref= FirebaseDatabase.getInstance().getReference("user");
        ref1= FirebaseDatabase.getInstance().getReference("customer");
        // ref2= FirebaseDatabase.getInstance().getReference("Farmers2Det");
/*        gotoBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });*/
       /* select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoGallery();
            }
        });*/
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name1 = Name.getText().toString();
                PhoneNum1 = PhoneNum.getText().toString();
                String State1 = State.getText().toString();

                String Address1 = Address.getText().toString();
                String District1 = District.getText().toString();
                String Pin1 = Pin.getText().toString();
                String City1 = City.getText().toString();
                //  String State1 = State.getText().toString();
                String Adhar1=Adhar.getText().toString();
                String Upid1=Upid.getText().toString();

                if (Name1.isEmpty() || PhoneNum1.isEmpty() || State1.isEmpty() || Address1.isEmpty() || District1.isEmpty() || Pin1.isEmpty() || City1.isEmpty() || Upid1.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Don't leave Anything Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (PhoneNum1.length() != 10) {
                        Toast.makeText(ProfileActivity.this, "Incorrect Phone Number", Toast.LENGTH_SHORT).show();
                    }
                    if (uploadTask != null && uploadTask.isInProgress()) {
                        Toast.makeText(ProfileActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
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
        // final String imageId=System.currentTimeMillis()+"."+Extension(uriImage);
        //StorageReference Ref= storageReference.child(imageId);
        // Ref.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        //  @Override
        //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Name1 = Name.getText().toString();
        PhoneNum1 = PhoneNum.getText().toString();
        String State1 = State.getText().toString();

        String Address1 = Address.getText().toString();
        String District1 = District.getText().toString();
        String Pin1 = Pin.getText().toString();
        String City1 = City.getText().toString();
        //  String State1 = State.getText().toString();
        String Adhar1=Adhar.getText().toString();
        String Upid1=Upid.getText().toString();
        //String imageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
        //  if (Category1.equals("Grain")) {
        // Toast.makeText(Main2Activity.this, "Excellent", Toast.LENGTH_SHORT).show();
        userid = ref.push().getKey();
        farm.setNaMe(Name1);
        farm.setPhoneNuM(PhoneNum1);
        farm.setStaTe(State1);
        farm.setDisTrict(District1);
        farm.setPiN(Pin1);
        farm.setCiTy(City1);
        farm.setAdharNum(Adhar1);
        farm.setUpiD(Upid1);
        //farm.setProDuct(Product1);
        farm.setAddRess(Address1);
        //farm.setDescripTion(Description1);
        //farm.setPriCe(Price1);
        // farm.setCateGory(Category1);
        farm.setId(userid);
        if(Activity.equals("farmer")) {
            ref.child(PhoneNum1).setValue(farm);
            str="farmer";
            Intent intent=new Intent(ProfileActivity.this,VerifyPhoneActivity.class);
            intent.putExtra("login","farmer");
            startActivity(intent);
        }
        else if(Activity.equals("costumer")){
            ref1.child(PhoneNum1).setValue(farm);
            str="costumer";
            Intent intent=new Intent(ProfileActivity.this,VerifyPhoneActivity.class);
            intent.putExtra("login","customer");
            startActivity(intent);
        }
                /*final Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        farm.setImaGe(imageUrl);
                        Toast.makeText(ProfileActivity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                        ref.child(userid).setValue(farm);

                    }
                });
                    /*Intent intent=new Intent(Main2Activity.this,Buyers.class);
                    intent.putExtra("Category1",Category1);
                    startActivity(intent);*/
        //Intent intent=new Intent(ProfileActivity.this,Description1.class);
        //Intent intent=new Intent(mContext,Description.class);
                   /* intent.putExtra("image_url",imageId);
                    //intent.putExtra("id",farm.getId());
                    intent.putExtra("Category",farm.getCateGory());
                    intent.putExtra("Product",farm.getProDuct());
                    intent.putExtra("Description",farm.getDescripTion());
                    intent.putExtra("Price",farm.getPriCe());
                    intent.putExtra("Address",farm.getAddRess());
                    intent.putExtra("Name",farm.getNaMe());
                    intent.putExtra("PhoneNum",farm.getPhoneNuM());*/
        // String s=getIntent().getStringExtra("login2");
        // intent.putExtra("user_id",userid);
        //intent.putExtra("login1",s);
        //startActivity(intent);



               /* if (Category1.equals("Seed")) {
                    userid1 = ref1.push().getKey();
                    //createNewPost(imageUrl);
                    // farm obj =new farmer(tv3.getText().toString(),imageUrl);
                    //String uploadId=ref.push().getKey();
                    // ref.child(uploadId).setValue(obj);
                    //Uri downloadUrl=result.getResult();
                    //saveData(downloadUrl);
                    farm.setNaMe(Name1);
                    farm.setPhoneNuM(PhoneNum1);
                    farm.setStaTe(State1);
                    farm.setProDuct(Product1);
                    farm.setAddRess(Address1);
                    farm.setDescripTion(Description1);
                    farm.setPriCe(Price1);
                    farm.setCateGory(Category1);

                    final Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();
                            farm.setImaGe(imageUrl);
                            Toast.makeText(Main2Activity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                            ref1.child(userid1).setValue(farm);

                        }
                    });
                    Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                    intent.putExtra("Category1",Category1);
                    startActivity(intent);

                }*/
    }

    // });

    // }


    /* private void GotoGallery() {
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


      }*/
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(ProfileActivity.this, FarmerActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

    }
    public  String GetUserId(){
        return PhoneNum1;
    }
    public String GetName(){
        return Name1;
    }
    public String GetStr(){
        return str;
    }
}
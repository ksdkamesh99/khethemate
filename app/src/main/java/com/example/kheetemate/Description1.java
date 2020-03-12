package com.example.kheetemate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kheetemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static java.lang.String.valueOf;

public class Description1 extends AppCompatActivity {
    ImageView imageView;
    DatabaseReference reference;
    TextView Name,Phone,Address,District,Pin,City,State,Adhar,Upid;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description1);
        button=findViewById(R.id.otp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Description1.this,VerifyPhoneActivity.class);
                String s=getIntent().getStringExtra("login1");
                intent.putExtra("login",s);
                startActivity(intent);

            }
        });
        //String userid=getIntent().getStringExtra("user_id");
        reference= FirebaseDatabase.getInstance().getReference();
        imageView=findViewById(R.id.RetrievedImage);
        Name=findViewById(R.id.Name);
        Phone=findViewById(R.id.Phone);
        Address=findViewById(R.id.Adress);
        District=findViewById(R.id.District);
        Pin=findViewById(R.id.pin);
        City=findViewById(R.id.city);
        State=findViewById(R.id.State);
        Adhar=findViewById(R.id.adhar);
        Upid=findViewById(R.id.upid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            ProfileActivity profileActivity=new ProfileActivity();

            String userid=profileActivity.GetUserId();
            String string=profileActivity.GetStr();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String imageUrl=valueOf(dataSnapshot.child("Farmers").child(userid).child("imaGe").getValue());
                if(string.equals("farmer")) {
                    String NaMe = valueOf(dataSnapshot.child("user").child(userid).child("naMe").getValue());
                    String PhoneNuM = valueOf(dataSnapshot.child("user").child(userid).child("phoneNuM").getValue());
                    String AddRess = valueOf(dataSnapshot.child("user").child(userid).child("addRess").getValue());
                    String DisTrict = valueOf(dataSnapshot.child("user").child(userid).child("disTrict").getValue());
                    String PiN = valueOf(dataSnapshot.child("user").child(userid).child("piN").getValue());
                    String CiTy = valueOf(dataSnapshot.child("user").child(userid).child("ciTy").getValue());
                    String StaTe = valueOf(dataSnapshot.child("user").child(userid).child("staTe").getValue());
                    String AdharNum = valueOf(dataSnapshot.child("user").child(userid).child("adharNum").getValue());
                    String UpiD = valueOf(dataSnapshot.child("userid").child(userid).child("upiD").getValue());
                    setDescription(PhoneNuM,StaTe,DisTrict,PiN,CiTy,UpiD,AddRess,NaMe,AdharNum);
                }
                else if(string.equals("costumer")){
                    String NaMe = valueOf(dataSnapshot.child("customer").child(userid).child("naMe").getValue());
                    String PhoneNuM = valueOf(dataSnapshot.child("customer").child(userid).child("phoneNuM").getValue());
                    String AddRess = valueOf(dataSnapshot.child("customer").child(userid).child("addRess").getValue());
                    String DisTrict = valueOf(dataSnapshot.child("customer").child(userid).child("disTrict").getValue());
                    String PiN = valueOf(dataSnapshot.child("customer").child(userid).child("piN").getValue());
                    String CiTy = valueOf(dataSnapshot.child("customer").child(userid).child("ciTy").getValue());
                    String StaTe = valueOf(dataSnapshot.child("customer").child(userid).child("staTe").getValue());
                    String AdharNum = valueOf(dataSnapshot.child("customer").child(userid).child("adharNum").getValue());
                    String UpiD = valueOf(dataSnapshot.child("customer").child(userid).child("upiD").getValue());
                    setDescription(PhoneNuM,StaTe,DisTrict,PiN,CiTy,UpiD,AddRess,NaMe,AdharNum);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //getIncomingIntent();
    }
    /*public void getIncomingIntent(){
        //if(getIntent().hasExtra("image_url")){
            String imageUrl=getIntent().getStringExtra("image_url");
            String CateGory=getIntent().getStringExtra("Category");
            String ProDuct=getIntent().getStringExtra("Product");
            String DesCription=getIntent().getStringExtra("Description");
            String Price=getIntent().getStringExtra("Price");
            String AddRess=getIntent().getStringExtra("Address");
            String NaMe=getIntent().getStringExtra("Name");
            String Phonenum=getIntent().getStringExtra("PhoneNum");
            setDescription(imageUrl,CateGory,ProDuct,DesCription,Price,AddRess,NaMe,Phonenum);
        //}
    }*/
    // setDescription(imageUrl,PhoneNuM,DisTrict,PiN,CiTy,UpiD,AddRess,NaMe,AdharNum);
    private void setDescription( String PhoneNuM,String StaTe, String DisTrict, String PiN, String CiTy, String UpiD, String AddRess, String NaMe,String AdharNum) {
        //Picasso.get().load(imageUrl).into(imageView);


        Address.setText(AddRess);
        Name.setText(NaMe);
        Phone.setText(PhoneNuM);
        District.setText(DisTrict);
        Pin.setText(PiN);
        City.setText(CiTy);
        State.setText(StaTe);
        Adhar.setText(AdharNum);
        Upid.setText(UpiD);


    }

}
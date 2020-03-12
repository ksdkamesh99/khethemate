package com.example.kheetemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.valueOf;

public class farmerotp extends AppCompatActivity {
    static String number;
    private EditText editTextMobile;
    farmer farm;
    Boolean found;
    DatabaseReference ref;
    String userid;
    int t=0;
    Firebase url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmerotp);
        //ref = FirebaseDatabase.getInstance().getReference();
        editTextMobile = findViewById(R.id.editTextMobile);
        farm = new farmer();
        Firebase.setAndroidContext(this);
        url = new Firebase("https://kheetemate.firebaseio.com/");
        Button btn = findViewById(R.id.buttonContinue);
        /*findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number1 = editTextMobile.getText().toString();

                if (number1.isEmpty() || number1.length() < 10) {
                    editTextMobile.setError("Valid number is required");
                    editTextMobile.requestFocus();
                    return;
                }
                number = "+91" + number1;
                //String phonenumber = "+"+code+number;

            }
        });*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String mobile = editTextMobile.getText().toString();
                        number="+91"+mobile;
                        String ans = valueOf(dataSnapshot.child("user").child(mobile).exists());
                        //Toast.makeText(farmerotp.this, "Value" + ans, Toast.LENGTH_LONG).show();
                        Log.d("piii",ans);
                        if(ans.equals("true")){
                            Intent farmer_act = new Intent(farmerotp.this,VerifyPhoneActivity.class);
                            farmer_act.putExtra("login","farmer");
                            startActivity(farmer_act);

                        }
                        else{
                            Intent farmer_pro = new Intent(farmerotp.this,ProfileActivity.class);
                            farmer_pro.putExtra("login1","farmer");
                            startActivity(farmer_pro);
                        }
                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });
    }
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(farmerotp.this, FarmerActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

    }
    public String GetNum(){
        return  number;
    }
    public  String GetFarm(){
        return "farmer";
    }
}
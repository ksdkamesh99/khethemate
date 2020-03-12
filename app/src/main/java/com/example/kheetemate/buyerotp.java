package com.example.kheetemate;

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

import static java.lang.String.valueOf;

public class buyerotp extends AppCompatActivity {

    private EditText editTextMobile;
    Firebase url;
    static String number1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerotp);
        Button btn = findViewById(R.id.buttonContinue);
        editTextMobile = findViewById(R.id.editTextMobile);
        Firebase.setAndroidContext(this);
        url = new Firebase("https://kheetemate.firebaseio.com/");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String mobile = editTextMobile.getText().toString();
                        String ans = valueOf(dataSnapshot.child("customer").child(mobile).exists());
                        number1="+91"+mobile;
                        if(ans.equals("true")){
                            Intent customer_act = new Intent(buyerotp.this, VerifyPhoneActivity.class);
                            customer_act.putExtra("login","customer");
                            startActivity(customer_act);

                        }
                        else{
                            Intent customer_pro = new Intent(buyerotp.this,ProfileActivity.class);
                            customer_pro.putExtra("login1","costumer");
                            startActivity(customer_pro);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

        /*findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(buyerotp.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                intent.putExtra("login","buyer");
                startActivity(intent);
            }
        });*/
    }
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(buyerotp.this, CostumerActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

    }
    public String GetNum1(){
        return number1;
    }
}
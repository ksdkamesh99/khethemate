package com.example.kheetemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Paymentcheckout extends AppCompatActivity {

    String amount;
    String note,name,virtualupiid;
    final int UPI_PAYMENT=0;
    TextView gpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentcheckout);
        amount="1";
        gpay=(TextView) findViewById(R.id.gpay);
        note="test";
        name="harsha";
        virtualupiid="mycreations2015@okhdfcbank";
        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payUsingUpi(name,virtualupiid,note,amount);
            }
        });

    }
    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name " + name + "--up--" + upiId + "--" + note + "--" + amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                //.appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                //.appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        int GOOGLE_PAY_REQUEST_CODE = 123;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response " + resultCode);
        if (resultCode == -1) {
            String trxt = data.getStringExtra("response");
            Log.e("UPI", "onActivityResult: " + trxt);
            String response[] = trxt.split("&");
            String transac[] = response[0].split("=");
            String status[] = response[2].split("=");
            String ref[] = response[3].split("=");
            String transactionid = transac[1];
            String Status = status[1];
            String Ref = ref[1];
            Log.e("UPI", "onActivityResult: " + transactionid+" "+Status+" "+Ref);
            if (isConnectionAvailable(Paymentcheckout.this)){
            if (Status.equals("SUCCESS")) {
                Intent intent = new Intent(Paymentcheckout.this, ConfirmationPayment.class);
                intent.putExtra("status", Status);
                intent.putExtra("transactionid", transactionid);
                intent.putExtra("referenceid", Ref);
                startActivity(intent);
            } else {
                Intent intent = new Intent(Paymentcheckout.this, ConfirmationPayment.class);
                intent.putExtra("status", Status);
                intent.putExtra("transactionid", transactionid);
                intent.putExtra("referenceid", Ref);
                startActivity(intent);
            }

        }}
    }


        /*
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }

         */

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(Paymentcheckout.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(Paymentcheckout.this, "Transaction successful.", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ConfirmationPayment.class);
                intent.putExtra("stat","success");
                startActivity(intent);
                Log.d("UPI", "payment successful: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(Paymentcheckout.this, "Payment cancelled by user.", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ConfirmationPayment.class);
                intent.putExtra("stat","fail");
                startActivity(intent);
                Log.d("UPI", "Cancelled by user: "+approvalRefNo);
            }
            else {
                Toast.makeText(Paymentcheckout.this, "Transaction failed.Please try again", Toast.LENGTH_LONG).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
            }
        } else {
            Log.d("UPI", "Internet issue: ");
            Toast.makeText(Paymentcheckout.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }



}

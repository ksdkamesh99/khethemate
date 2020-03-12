package com.example.kheetemate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;


public class SmsReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Intent pass_msg = new Intent(context, reciever.class);
            Bundle Messages = intent.getExtras();
            SmsMessage[] msgs;
            if(Messages != null) {
                try {
                    Object[] pdus = (Object[]) Messages.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        String msg_Body = msgs[i].getMessageBody();
                        pass_msg.putExtra("MESSAGE",msg_Body);
                        context.startActivity(pass_msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
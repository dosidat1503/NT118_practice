package com.example.lab4main2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {
    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SmsReceiver", "onReceive called");
        String queryString = context.getString(R.string.are_you_ok).toLowerCase();

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                ArrayList<String> addresses = new ArrayList<>();
                for (Object pdu : pdus) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                    if (message != null) {
                        String messageBody = message.getMessageBody();
                        Log.d("SmsReceiver", "Message received: " + messageBody);
                        if (messageBody != null && messageBody.toLowerCase().contains(queryString)) {
                            addresses.add(message.getOriginatingAddress());
                        }
                    }
                }

                if (!addresses.isEmpty()) {
                    if (!Bai4Activity.isRunning) {
                        Intent iMain = new Intent(context, MainActivity.class);
                        iMain.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                        iMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        iMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Log.d("SmsReceiver", "Starting MainActivity");
                        context.startActivity(iMain);
                    } else {
                        Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                        iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                        Log.d("SmsReceiver", "Broadcasting to MainActivity");
                        context.sendBroadcast(iForwardBroadcastReceiver);
                    }
                }
            } else {
                Log.e("SmsReceiver", "PDUs is null");
            }
        } else {
            Log.e("SmsReceiver", "Bundle is null");
        }
    }
}

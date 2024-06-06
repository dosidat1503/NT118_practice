package com.example.lab4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.credentials.UnregisterCredentialDescriptionRequest;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class MainActivity extends AppCompatActivity {

    //bài 1
    private BroadcastReceiver broadcastReceiver;
    //bài 1

    // bài 4
    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButtons;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver broadcastReceiver_bai4;
    public static boolean isRunning;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private final String AUTO_RESPONSE = "auto_response";
    private IntentFilter filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai4);

        // bài 1
//        initBroadcastReceiver();

        //bài 4
        findViewsByIds();
        initVariables();
        handleOnClickListenner();

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    //bài 1
//    public void processReceive(Context context, Intent intent) {
//        // Display a toast notification
//        Toast.makeText(context, context.getString(R.string.you_have_a_new_message), Toast.LENGTH_LONG).show();
//
//        TextView tvContent = (TextView) findViewById(R.id.tv_content);
//        // Get the TextView from your layout (this assumes you have an activity layout with a TextView with the id 'tv_content')
//        // Note: You might need to update this part to properly update your UI, as accessing UI elements directly in a BroadcastReceiver is not typical.
//        // Usually, you would send a broadcast or start an activity to update the UI.
//        // TextView tvContent = (TextView) findViewById(R.id.tv_content);
//
//        // Use "pdus" as key to get message
//        final String SMS_EXTRA = "pdus";
//        Bundle bundle = intent.getExtras();
//
//        // Get array of messages which were received at the same time
//        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
//        String sms = "";
//
//        SmsMessage smsMsg;
//        for (int i = 0; i < messages.length; i++) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                String format = bundle.getString("format");
//                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i], format);
//            } else {
//                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);
//            }
//
//            // Get message body
//            String msgBody = smsMsg.getMessageBody();
//            // Get source address of message
//            String address = smsMsg.getDisplayOriginatingAddress();
//            sms += address + "\n" + msgBody + "\n";
//        }
//
//        // Show messages in textview
//        tvContent.setText(sms);
//    }
//
//    //bài 1
//    private void initBroadcastReceiver() {
//    // Create filter to listen to incoming messages
//        filter = new IntentFilter("android.provider. Telephony. SMS_RECEIVED");
//    // Create broadcastReceiver
//        broadcastReceiver = new BroadcastReceiver() {
//            // Process when a message comes
//            public void onReceive (Context context, Intent intent) {
//                processReceive (context, intent);
//            }
//        };
//    }
//
//    //bài 1
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Make sure broadcastReceiver was created
//        if (broadcastReceiver == null) initBroadcastReceiver();
//        // Register Receiver
//        registerReceiver (broadcastReceiver, filter);
//    }
//
//    //bài 1
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(broadcastReceiver);
//    }

    //bài 4
    private void findViewsByIds() {
        swAutoResponse = (Switch) findViewById(R.id.sw_auto_response);
        llButtons = (LinearLayout) findViewById(R.id.ll_buttons);
        lvMessages = (ListView) findViewById(R.id.lv_messages);
        btnSafe = (Button) findViewById(R.id.btn_safe);
        btnMayday = (Button) findViewById(R.id.btn_mayday);
    }

    private void respond (String to, String response) {
        reentrantLock.lock();
        requesters.remove(to);
        adapter.notifyDataSetChanged();
        reentrantLock.unlock();
// Send the message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(to, null, response, null, null);
    }

    public void respond (boolean ok) { String okString = getString(
        R.string.i_am_safe_and_well_worry_not);
        String notOkString = getString(
                R.string.tell_my_mother_i_love_her);
        String outputString = ok ? okString: notOkString; ArrayList<String> requestersCopy =
                (ArrayList<String>) requesters.clone();
        for (String to: requestersCopy)
            respond (to, outputString);
    }


    public void processReceiveAddresses (ArrayList<String> addresses)
    {
        for (int i = 0; i < addresses.size(); i++) {
            if (!requesters.contains(addresses.get(i))) {
                reentrantLock.lock();
                requesters.add(addresses.get(i));
                adapter.notifyDataSetChanged();
                reentrantLock.unlock();
            }
            // Check to response automatically
            if (swAutoResponse.isChecked()) respond (true);
        }
    }

    private void handleOnClickListenner() {
// Handle onClickListenner
        btnSafe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                respond(true);
            }
        });
        btnMayday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                respond(false);
            }
        });
        swAutoResponse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) llButtons.setVisibility(View.GONE);
            else llButtons.setVisibility(View.VISIBLE);
// Save auto response setting
                editor.putBoolean(AUTO_RESPONSE, isChecked);
                editor.commit();
            }
        });
    }

    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get ArrayList addresses
                ArrayList<String> addresses = intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);

                // Process these addresses
                processReceiveAddresses(addresses);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
        // Make sure broadcastReceiver was inited
        if (broadcastReceiver == null) initBroadcastReceiver();
        // RegisterReceiver
        IntentFilter intentFilter = new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER);
        registerReceiver(broadcastReceiver, intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
        // UnregisterReceiver
        unregisterReceiver(broadcastReceiver);
    }
    private void initVariables() {
        sharedPreferences = getPreferences (MODE_PRIVATE); editor = sharedPreferences.edit(); reentrantLock = new ReentrantLock(); requesters = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, requesters);
        lvMessages.setAdapter (adapter);
    // Load auto response setting
        boolean autoResponse = sharedPreferences.
                getBoolean (AUTO_RESPONSE, false); swAutoResponse.setChecked (autoResponse);
        if (autoResponse) llButtons.setVisibility(View.GONE);
        initBroadcastReceiver ();

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.hasExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY)) {
                    ArrayList<String> addresses = intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);
                    if (addresses != null) {
                        processReceiveAddresses(addresses);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER);
        registerReceiver(broadcastReceiver, filter);
    }
}
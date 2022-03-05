package com.vinnie.textapp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG = MainActivity.class.getSimpleName();
    private static  final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForSmspermission();

    }
    private void checkForSmspermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){

            Log.d( TAG,getString(R.string.permission_not_granted));

            ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.SEND_SMS},
            MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else{
        enableSmsButton();


    }



}

    private void enableSmsButton() {
        ImageButton smsButton = (ImageButton)findViewById(R.id.message_icon);
        smsButton.setVisibility(View.VISIBLE);
    }
    private void disableSmsButton(){
        Toast.makeText(this, R.string .sms_disabled, Toast .LENGTH_LONG).show();
    ImageButton smsButton = (ImageButton) findViewById(R .id.message_icon);
        smsButton.setVisibility(View .INVISIBLE);
        Button retryButton = (Button) findViewById(R.id.button_retry);
        retryButton.setVisibility(View . VISIBLE);
}

    public void retryApp(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        startActivity(intent);
    }



    public void smsSendmessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_main);
        String destinationAddress = editText.getText().toString();
        EditText smsEditText = (EditText) findViewById(R.id.sms_message);
        String smsmessage = smsEditText.getText().toString();
        String scAddress = null;
        PendingIntent sentIntent = null, deliveryIntent = null;
        checkForSmspermission();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(destinationAddress,   scAddress ,smsmessage,sentIntent,deliveryIntent);
    }
}

package com.example.sdeintern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    TextView t;
    Button checkOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t=findViewById(R.id.result);
        checkOut=findViewById(R.id.checkout);
        Intent i = getIntent();
        final String Vname = i.getStringExtra("vname");
        final String Vemail = i.getStringExtra("vmail");
        final String Vphone = i.getStringExtra("vphone");
        final String Hname=i.getStringExtra("hostName");
        final String Hmail=i.getStringExtra("hostMail");
        final String Hphone=i.getStringExtra("hostPhone");
        final String time=i.getStringExtra("time");
        final String address=i.getStringExtra("address");
        t.setText("Hello "+Vname+" \nYour Details will be sent to your phone number: "+Vphone+" " +
                "and \nE-Mail:"+Vemail+" soon you checkout\nYour Check-In time is "+time+" at "+address+"\nPress Checkout to confirm your checkout");
        if(ContextCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            checkOut.setEnabled(false);
            ActivityCompat.requestPermissions(Main3Activity.this,
                    new String []{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS},0);
        }

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
                String checkoutTime = dateFormat.format(currentTime);
                String message="Hello " +Vname+ " Your meeting with "+Hname+" at "+time+" and the checkout time is  "+checkoutTime+" at "+address+"";
                SmsManager smsManager = SmsManager.getDefault();
               /* smsManager.sendTextMessage(Hphone, null, "Hello " +Hname+
                        " Your meeting with "+Hname+" at "+time+" and the checkout time is"+checkoutTime+" at "+address+"", null, null);*/
                smsManager.sendTextMessage(Vphone, null, "Hello " +Vname+
                        " Your meeting at "+address+ " with "+Hname+" at "+time+" and the checkout time is "+checkoutTime+" ", null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
                /*Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{Vemail});
                it.putExtra(Intent.EXTRA_SUBJECT,"Your Meeting");
                it.putExtra(Intent.EXTRA_TEXT,message);
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Choose Mail App"));*/
                String[] TO = {
                        Vemail
                };
                String[] CC = {
                        Hmail
                };
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your meeting confirmed");
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Toast.makeText(getApplicationContext(), "EMAIL SENT", Toast.LENGTH_SHORT).show();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Main3Activity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED)
                checkOut.setEnabled(true);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

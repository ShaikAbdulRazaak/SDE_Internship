package com.example.sdeintern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        t.setText("Hello "+Vname+" \nYour Details will be sent to your phone number: "+Vphone+" " +
                "and \nE-Mail:"+Vemail+" soon you checkout\nYour Check-In time is "+time+"");
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss aa");
                String checkoutTime = dateFormat.format(currentTime);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(Hphone, null, "Hello " +Vname+
                        " Your meeting with "+Hname+" at "+time+" and the checkout time is"+checkoutTime+"", null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage(Vphone, null, "Hello " +Vname+
                        " Your meeting with "+Hname+" at "+time+" and the checkout time is"+checkoutTime+"", null, null);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, Vemail);
                emailIntent.putExtra(Intent.EXTRA_CC, Hmail);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your meeting");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello "+Vname+"Your meeting with "+Hname+" at "+time+" and the checkout time is "+checkoutTime+"");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email.", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Main3Activity.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

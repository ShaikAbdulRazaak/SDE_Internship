package com.example.sdeintern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

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
        String Vname = i.getStringExtra("vname");
        String Vemail = i.getStringExtra("vmail");
        String Vphone = i.getStringExtra("vphone");
        String Hname=i.getStringExtra("hostName");
        String Hmail=i.getStringExtra("hostMail");
        String Hphone=i.getStringExtra("hostPhone");
        String time=i.getStringExtra("time");
        t.setText("Hello "+Vname+" \nYour Details will be sent to your phone number: "+Vphone+" " +
                "and \nE-Mail:"+Vemail+" soon you checkout\nYour Check-In time is "+time+"");
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent i=new Intent(Main3Activity.this,Main4Activity.class);
            }
        });
    }
}

package com.example.sdeintern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText hostName, hostMail, hostPhone;
    MaterialButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hostName = findViewById(R.id.hostname);
        hostMail = findViewById(R.id.hostemail);
        hostPhone = findViewById(R.id.hostphone);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hostMail.getText().toString().isEmpty() || hostName.getText().toString().isEmpty()
                        || hostPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle b = new Bundle();
                    b = getIntent().getExtras();
                    String name = b.getString("visitorName");
                    String email = b.getString("visitorEmail");
                    String phone = b.getString("visitorPhone");
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss aa");
                    String output = dateFormat.format(currentTime);//Toast.makeText(Main2Activity.this,"Mr. " + name + " Your Check-in Time is:" + output + "", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                    i.putExtra("vname",name);
                    i.putExtra("vmail",email);
                    i.putExtra("vphone",phone);
                    i.putExtra("hostName", hostName.getText().toString());
                    i.putExtra("hostMail", hostMail.getText().toString());
                    i.putExtra("hostPhone", hostPhone.getText().toString());
                    i.putExtra("time", output);
                    startActivity(i);
                }
            }
        });
    }
}

package com.example.sdeintern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText hostName, hostMail, hostPhone,address;
    MaterialButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hostName = findViewById(R.id.hostname);
        hostMail = findViewById(R.id.hostemail);
        hostPhone = findViewById(R.id.hostphone);
        address=findViewById(R.id.address);
        submit = findViewById(R.id.submit);
        if(ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            submit.setEnabled(false);
            ActivityCompat.requestPermissions(Main2Activity.this,
                    new String []{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS},0);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hostMail.getText().toString().isEmpty() || hostName.getText().toString().isEmpty()
                        || hostPhone.getText().toString().isEmpty()||address.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle b = new Bundle();
                    b = getIntent().getExtras();
                    String name = b.getString("visitorName");
                    String email = b.getString("visitorEmail");
                    String phone = b.getString("visitorPhone");
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    String output = dateFormat.format(currentTime);
                    Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                    SmsManager smsManager= SmsManager.getDefault();
                    smsManager.sendTextMessage(hostPhone.getText().toString(), null, "Hello " +hostName.getText().toString()+
                            " Your meeting with "+name+" is confirmed and "+name+"'s check-in time is  "+output+"", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                    i.putExtra("vname",name);
                    i.putExtra("vmail",email);
                    i.putExtra("vphone",phone);
                    i.putExtra("hostName", hostName.getText().toString());
                    i.putExtra("hostMail", hostMail.getText().toString());
                    i.putExtra("hostPhone", hostPhone.getText().toString());
                    i.putExtra("address",address.getText().toString());
                    i.putExtra("time", output);
                    startActivity(i);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED)
                submit.setEnabled(true);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

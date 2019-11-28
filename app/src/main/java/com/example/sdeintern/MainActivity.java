package com.example.sdeintern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    EditText name, email, phone_Number;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone_Number = findViewById(R.id.phone);
        materialButton = findViewById(R.id.hostDetails);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                        || phone_Number.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("visitorName", name.getText().toString());
                    i.putExtra("visitorEmail", email.getText().toString());
                    i.putExtra("visitorPhone", phone_Number.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
}





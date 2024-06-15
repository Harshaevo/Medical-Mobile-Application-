package com.example.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView medicine;
    CardView appointment;
    CardView patient;
    CardView about_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicine = findViewById(R.id.medicine_info);
        appointment = findViewById(R.id.appointment);
        patient = findViewById(R.id.patient);
        about_app = findViewById(R.id.about_app);

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Medicine info clicked");
                Intent intent = new Intent(getApplicationContext(),Medicine_infor.class);
                startActivity(intent);

            }
        });
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Doctor appointment clicked");
                Intent intent = new Intent(getApplicationContext(),DoctorAppoinment.class);
                startActivity(intent);


            }
        });
       patient.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showToast("Patient info");
               Intent intent = new Intent(getApplicationContext(),PatientInfo.class);
               startActivity(intent);
           }
       });
       about_app.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showToast("About Doctor app");
               Intent intent = new Intent(getApplicationContext(),aboutapp.class);
               startActivity(intent);
           }
       });
    }

    private void showToast(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
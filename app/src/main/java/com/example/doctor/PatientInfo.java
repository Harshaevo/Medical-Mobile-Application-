package com.example.doctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientInfo extends AppCompatActivity {

    EditText pname, age, address, number, payments;
    Button insert, update, delete, view;
    DBpatient DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        pname = findViewById(R.id.PatName);
        age = findViewById(R.id.PatAge);
        address = findViewById(R.id.PatAddress);
        number = findViewById(R.id.PatNumber);
        payments = findViewById(R.id.PatPayments);

        insert = findViewById(R.id.btnPatInsert);
        update = findViewById(R.id.btnPatUpdate);
        delete = findViewById(R.id.btnPatDelete);
        view = findViewById(R.id.btnPatView);
        DB = new DBpatient(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = pname.getText().toString();
                String dateTXT = age.getText().toString();
                String addressTXT= address.getText().toString();
                String numberTXT= number.getText().toString();
                String paymentsTXT= payments.getText().toString();

                Boolean checkinsertdata = DB.insertpuserdata(nameTXT,dateTXT,addressTXT,numberTXT,paymentsTXT);
                if (checkinsertdata==true)
                    Toast.makeText(PatientInfo.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PatientInfo.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = pname.getText().toString();
                String dateTXT = age.getText().toString();
                String addressTXT= address.getText().toString();
                String numberTXT= number.getText().toString();
                String paymentsTXT= payments.getText().toString();

                Boolean checkupdatepdata = DB.updatepuserdata(nameTXT,dateTXT,addressTXT,numberTXT,paymentsTXT);
                if (checkupdatepdata==true)
                    Toast.makeText(PatientInfo.this, "Entry updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PatientInfo.this, "Entry not updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = pname.getText().toString();
                Boolean checkdeletepdata = DB.deletepdata(nameTXT);
                if (checkdeletepdata==true)
                    Toast.makeText(PatientInfo.this, " Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PatientInfo.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =DB.getpdata();
                if (res.getCount()==0){
                    Toast.makeText(PatientInfo.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Patient Name :"+ res.getString(0)+"\n");
                    buffer.append("Age :"+ res.getString(1)+"\n");
                    buffer.append("Address :"+ res.getString(2)+"\n");
                    buffer.append("Tel Number :"+ res.getString(3)+"\n");
                    buffer.append("Payments :"+ res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(PatientInfo.this);
                builder.setCancelable(true);
                builder.setTitle("Patient Information");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
package com.example.doctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorAppoinment extends AppCompatActivity {

    EditText name, date, description;
    Button insert, update, delete, view;
    DBappointment DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appoinment);

        name = findViewById(R.id.AppName);
        date = findViewById(R.id.AppDate);
        description = findViewById(R.id.AppDescription);

        insert = findViewById(R.id.btnApInsert);
        update = findViewById(R.id.btnApUpdate);
        delete = findViewById(R.id.btnApDelete);
        view = findViewById(R.id.btnApView);
        DB = new DBappointment(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dateTXT = date.getText().toString();
                String descriptionTXT= description.getText().toString();
                
                Boolean checkinsertdata = DB.insertuserdata(nameTXT,dateTXT,descriptionTXT);
                if (checkinsertdata==true)
                    Toast.makeText(DoctorAppoinment.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DoctorAppoinment.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dateTXT = date.getText().toString();
                String descriptionTXT= description.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT,dateTXT,descriptionTXT);
                if (checkupdatedata==true)
                    Toast.makeText(DoctorAppoinment.this, "Entry updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DoctorAppoinment.this, "Entry not updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if (checkdeletedata==true)
                    Toast.makeText(DoctorAppoinment.this, " Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DoctorAppoinment.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();

            }
        });
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(DoctorAppoinment.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Appointment Name :"+ res.getString(0)+"\n");
                    buffer.append("Appointment Date :"+ res.getString(1)+"\n");
                    buffer.append("Appointment Description :"+ res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorAppoinment.this);
                builder.setCancelable(true);
                builder.setTitle("Doctor Appointment");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
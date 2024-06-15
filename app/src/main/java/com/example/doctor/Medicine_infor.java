package com.example.doctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Medicine_infor extends AppCompatActivity {

    EditText mname, amount, price, expdate;
    Button insert,update, delete, view;
    DBmedi DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_infor);

        mname = findViewById(R.id.Mediname);
        amount = findViewById(R.id.Mediamount);
        price = findViewById(R.id.Mediprice);
        expdate = findViewById(R.id.Medidate);

        insert = findViewById(R.id.btnMedInsert);
        update = findViewById(R.id.btnMedUpdate);
        delete = findViewById(R.id.btnMedDelete);
        view = findViewById(R.id.btnMedView);
        DB = new DBmedi(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = mname.getText().toString();
                String amountTXT = amount.getText().toString();
                String priceTXT = price.getText().toString();
                String expdateTXT = expdate.getText().toString();

                Boolean checkinsertmdata =DB.insertusermdata(nameTXT,amountTXT,priceTXT,expdateTXT);
                if(checkinsertmdata==true)
                    Toast.makeText(Medicine_infor.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Medicine_infor.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = mname.getText().toString();
                String amountTXT = amount.getText().toString();
                String priceTXT = price.getText().toString();
                String expdateTXT = expdate.getText().toString();

                Boolean checkupdatemdata =DB.updateusermdata(nameTXT,amountTXT,priceTXT,expdateTXT);
                if(checkupdatemdata==true)
                    Toast.makeText(Medicine_infor.this, "New Entry updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Medicine_infor.this, "New Entry not updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = mname.getText().toString();
                Boolean checkdeletemdata =DB.deleteusermdata(nameTXT);
                if(checkdeletemdata==true)
                    Toast.makeText(Medicine_infor.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Medicine_infor.this, " Entry not deleted", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getmdata();
                if (res.getCount()==0){
                    Toast.makeText(Medicine_infor.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Drug Name :"+res.getString(0)+"\n");
                    buffer.append("Drug Amount :"+res.getString(1)+"\n");
                    buffer.append("Drug Unit Price:"+res.getString(2)+"\n");
                    buffer.append("Drug Expire date :"+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder =  new AlertDialog.Builder(Medicine_infor.this);
                builder.setCancelable(true);
                builder.setTitle("Medicine Information");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });


    }
}
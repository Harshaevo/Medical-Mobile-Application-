package com.example.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup_Form extends AppCompatActivity {

    EditText username,password,conf_password;
    Button reg_btn,sign_btn;
    DBlog DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Doctor app Signup Form");


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        conf_password = (EditText) findViewById(R.id.conf_password);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        sign_btn = (Button) findViewById(R.id.sign_btn);
        DB = new DBlog(this);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString() ;
                String pass = password.getText().toString();
                String repass = conf_password.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Signup_Form.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(Signup_Form.this,"Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login_Form.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup_Form.this,"Registration failed",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(Signup_Form.this,"User already exists! please login",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup_Form.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (getApplicationContext(), Login_Form.class);
                startActivity(intent);

            }
        });

    }
}
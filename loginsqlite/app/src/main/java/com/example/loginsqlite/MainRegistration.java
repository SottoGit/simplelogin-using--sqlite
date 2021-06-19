package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainRegistration extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText repassword;
    Button register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registration);

        username = (EditText) findViewById(R.id.editTextregUsername);
        password = (EditText) findViewById(R.id.editTextregPassword);
        repassword = (EditText) findViewById(R.id.editTextregconPassword);
        register = (Button) findViewById(R.id.buttonNewRegister);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainRegistration.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainRegistration.this, "Successfully Registried",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainLogin.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainRegistration.this, "Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                           Toast.makeText(MainRegistration.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainRegistration.this,"Password not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainLogin extends AppCompatActivity {
    EditText username;
    EditText password;
    Button btnlogin;
    Button btnregister;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        username=(EditText) findViewById(R.id.editTextloginUsername);
        password=(EditText) findViewById(R.id.editTextloginpassword);
        btnlogin=(Button) findViewById(R.id.buttonLoginin);
        btnregister = (Button) findViewById(R.id.buttonLoginregister);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("")||pass.equals("")) {
                    Toast.makeText(MainLogin.this, "Please Enter Proper Details", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainLogin.this, "Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainHome.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainLogin.this,"Username or Password is Wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainRegistration.class);
                startActivity(intent);
            }
        });
    }
}
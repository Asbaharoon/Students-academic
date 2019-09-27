package com.example.gfmcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginact extends AppCompatActivity {

        private EditText username;
        private EditText password;
        private Button signin;
        private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);

        username=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        signin=(Button)findViewById(R.id.signupbutton);
        login=(Button)findViewById(R.id.loginpagebutton);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(loginact.this,MainActivity.class);
                startActivity(intent2);
            }
        });



    }
}

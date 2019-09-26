package com.example.gfmcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText f;
    private EditText m;
    private EditText l;
    private EditText ei;
    private EditText zprn;
    private EditText pwd1;
    private EditText conpwd;
    private Button signup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f=(EditText)findViewById(R.id.f);
        m=(EditText)findViewById(R.id.m);
        l=(EditText)findViewById(R.id.l);
        ei=(EditText)findViewById(R.id.ei);
        zprn=(EditText)findViewById(R.id.zprn);
        pwd1=(EditText)findViewById(R.id.pwd);
        conpwd=(EditText)findViewById(R.id.conpwd);
        signup=(Button)findViewById(R.id.signbut);

    }
}

package com.example.gfmcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText f;
    private EditText m;
    private EditText l;
    private EditText ei;
    private EditText zprn;
    private EditText pwd1;
    private EditText conpwd;
    private Button signup;
    private Button backtologin;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


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
        signup=(Button)findViewById(R.id.signupbutton);
        backtologin=(Button)findViewById(R.id.backtologinbutton);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(f.getText().toString(),m.getText().toString(),l.getText().toString(),ei.getText().toString(),zprn.getText().toString(),pwd1.getText().toString(),conpwd.getText().toString());

            }
        });
        backtologin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {
                abc();
            }
        });


    }


    private void validate(String fn,String mn,String ln,String ein,String zprnn,String pwd1n,String conpwdn){
    //if(fn!=null || mn!=null || ln!=null || ein!=null || zprnn!=null || pwd1n!=null || conpwdn!=null) {
    //if(f.getText().toString()!=null && m.getText().toString()!=null||l.getText().toString()!=null ||ei.getText().toString()!=null || zprn.getText().toString()!=null || pwd1.getText().toString()!=null || conpwd.getText().toString()!=null){
      if(pwd1n.isEmpty() || conpwdn.isEmpty() || mn.isEmpty() || ln.isEmpty() || ein.isEmpty() || fn.isEmpty() || zprnn.isEmpty()){

          if(pwd1n.isEmpty()) pwd1.setError("Content missing");
          if(conpwdn.isEmpty()) conpwd.setError("Content missing");
          if(ln.isEmpty()) l.setError("Content missing");
          if(mn.isEmpty()) m.setError("Content missing");
          if(fn.isEmpty()) f.setError("Content missing");
          if(zprnn.isEmpty()) zprn.setError("Content missing");
          if(ein.isEmpty()) ei.setError("Content missing");

            }
    else{

        if (pwd1n.equals(conpwdn) && pwd1n!=null && conpwdn!=null) {

            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("first",fn);
            user.put("middle",mn);
            user.put("last",ln);
            user.put("email",ein);
            user.put("zprn",zprnn);
            user.put("pwd",pwd1n);
            user.put("confirmpwd",conpwdn);

// Add a new document with a generated ID
            db.collection("users")
                    .add(user)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT);
                           // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            Intent intent1 = new Intent(MainActivity.this,dataact.class);
                            startActivity(intent1);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error adding document try again",Toast.LENGTH_LONG);
                            // Log.w(TAG, "Error adding document", e);
                        }
                    });

        }
        else{
            conpwd.setError("*Enter same password as above");
        }


    }

    }

    private void abc() {
        Intent intent = new Intent(MainActivity.this, loginact.class);
        startActivity(intent);
    }


        }



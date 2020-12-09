package com.example.gfmcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfmcheck.ui.profile.ProfileFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;

public class loginact extends AppCompatActivity {

        public EditText username;
        public EditText password;
        public Button signin;
        public Button login;
        public TextView a;
        public String zprn,copass,user,passw1;
        public DatabaseReference myRef;
        public ValueEventListener eventListener;
   // ValueEventListener addValueEventListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //

        username=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        signin=findViewById(R.id.signupbutton);
        login=findViewById(R.id.loginpagebutton);

        login.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read from the database
                user=username.getText().toString().trim();
                passw1 = password.getText().toString();

                myRef = FirebaseDatabase.getInstance().getReference("User").child(user);
//


                eventListener = myRef.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //

                        zprn = dataSnapshot.child("zprn").getValue().toString();
                        copass = dataSnapshot.child("copass").getValue().toString().trim();
                        Log.i("copass", copass);
                        Log.i("pass1", passw1);
                        Toast.makeText(getApplicationContext(),copass,Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), passw1, Toast.LENGTH_SHORT).show();

                         if (TextUtils.equals(copass, passw1)) {
                             Intent i1 = new Intent(loginact.this, dataact.class);
                             i1.putExtra("userName", user);
                            startActivity(i1);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
            }
        });




           signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(loginact.this,MainActivity.class);
                    startActivity(intent2);
                }
            });


    }

}

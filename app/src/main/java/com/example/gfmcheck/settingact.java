package com.example.gfmcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settingact extends AppCompatActivity {

    private Button attendence3;
    private Button form3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingact);

    attendence3=(Button)findViewById(R.id.attendences);
    form3=(Button)findViewById(R.id.forms);

    attendence3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent7=new Intent(settingact.this,attendenceact.class);
            startActivity(intent7);
        }
    });

    form3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent intent8=new Intent(settingact.this,dataact.class);
                startActivity(intent8);
        }
    });

    }
}

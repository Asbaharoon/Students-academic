package com.example.gfmcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class attendenceact extends AppCompatActivity {
    private Button form2;
    private Button setting2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendenceact);
    form2=(Button)findViewById(R.id.forma);
    setting2=(Button)findViewById(R.id.settinga);

        form2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9=new Intent(attendenceact.this,dataact.class);
                startActivity(intent9);
            }
        });


        setting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10=new Intent(attendenceact.this,settingact.class);
                startActivity(intent10);
            }
        });


    }
}

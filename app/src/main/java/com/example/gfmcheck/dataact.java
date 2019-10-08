package com.example.gfmcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dataact extends AppCompatActivity {

    private Button attendence1;
    private Button setting1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataact);

    attendence1=(Button)findViewById(R.id.attendence);
    setting1=(Button)findViewById(R.id.setting);

    attendence1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent5= new Intent(dataact.this,attendenceact.class);
            startActivity(intent5);
        }
    });

        setting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6= new Intent(dataact.this,settingact.class);
                startActivity(intent6);
            }
        });

    }
}

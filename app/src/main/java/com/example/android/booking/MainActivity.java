package com.example.android.booking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private Button admin;
   private Button user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin=(Button)findViewById(R.id.admin);
        user=(Button)findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent g=new Intent(MainActivity.this,request.class);
             startActivity(g);
            }
        }
        );
        admin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent g=new Intent(MainActivity.this,approve.class);
                                        startActivity(g);
                                    }
                                }
        );
    }
}

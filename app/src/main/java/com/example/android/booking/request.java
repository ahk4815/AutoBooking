package com.example.android.booking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class request extends AppCompatActivity {
    private EditText name;
    private EditText slot;
    private EditText audit;
    private EditText date;
    private EditText phone;
    private Button submit;
    private Button see;
    database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Intent g=getIntent();
        name=(EditText)findViewById(R.id.name);
        slot=(EditText)findViewById(R.id.slot);
        audit=(EditText)findViewById(R.id.audit);
        date=(EditText)findViewById(R.id.date);
        phone=(EditText)findViewById(R.id.phone);
        submit=(Button)findViewById(R.id.submit);
        see=(Button)findViewById(R.id.see);
        myDb = new database(this);
       see.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                    Intent i=new Intent(request.this,user_approve.class);
                    startActivity(i);
                   }
               }
       );
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(name.getText().toString(),
                                audit.getText().toString(),
                                slot.getText().toString(),date.getText().toString(),phone.getText().toString());
                        if(isInserted == true)
                        { Toast.makeText(request.this,"Data Inserted",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(request.this , status.class);
                            Cursor res= myDb.getrow(name.getText().toString());
                            // System.out.println("++++++++++++++"+res.getString(0)+"+++++++"+res.getString(1));
                            if(res.getCount() == 0) {
                                // show message
                                System.out.println("++++++++++++Error+++++++++++++");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id :"+ res.getString(0)+"\n");
                                buffer.append("Name :"+ res.getString(1)+"\n");
                                buffer.append("audit :"+ res.getString(2)+"\n");
                                buffer.append("slot :"+ res.getString(3)+"\n\n");
                            }
                            System.out.println("+++++++++++"+buffer.toString()+"++++++++");
                            startActivity(i);
                        }
                        else
                            Toast.makeText(request.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}

package com.example.android.booking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class user_approve extends AppCompatActivity {
    database myDb;

    ListView lw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_approve);
        Intent i=getIntent();
        lw=(ListView)findViewById(R.id.list2);
        myDb = new database(this);
        Cursor res= myDb.getall();
        Toast.makeText(user_approve.this,"---"+res.getCount(),Toast.LENGTH_SHORT).show();
        String[] items=new String[res.getCount()];

        int i1=0;
        while (res.moveToNext()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Id :"+ res.getString(0)+"\n");
            items[i1]=buffer.toString();
            i1++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        lw.setAdapter(adapter);
        lw.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(user_approve.this,i+"",Toast.LENGTH_SHORT).show();
                        Intent u=new Intent(user_approve.this,update.class);
                        i=i+1;
                        String f=i+"";
                        u.putExtra("position", f);
                        u.putExtra("visible", "no");
                        startActivity(u);

                    }
                }
        );


    }
}

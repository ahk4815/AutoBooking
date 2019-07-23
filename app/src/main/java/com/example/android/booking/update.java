package com.example.android.booking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class update extends AppCompatActivity {
    database myDb;
    private TextView name;
    private TextView slot;
    private TextView audit;
    private TextView date;
    private TextView phone;
    private TextView status2;
    private Button submit;
    private Button submit3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent u=getIntent();
        String pos=u.getStringExtra("position");
        String vis=u.getStringExtra("visible");
        myDb = new database(this);
        name=(TextView)findViewById(R.id.name2);
        slot=(TextView)findViewById(R.id.slot2);
        audit=(TextView)findViewById(R.id.audit2);
        date=(TextView)findViewById(R.id.date2);
        phone=(TextView)findViewById(R.id.phone2);
        status2=(TextView)findViewById(R.id.status2);
        submit=(Button)findViewById(R.id.submit2);
        submit3=(Button)findViewById(R.id.submit3);

        if(vis.equals("no"))
        {
            submit.setVisibility(View.GONE);
            submit3.setVisibility(View.GONE);

        }

        final int posx=((int)pos.charAt(0)) - 48;
        //posx=posx-48;
        Cursor res=myDb.getrow2(posx);
        while (res.moveToNext()) {
            name.setText(res.getString(1));
            slot.setText(res.getString(2));
            audit.setText(res.getString(3));
            date.setText(res.getString(4));
            phone.setText(res.getString(5));
            status2.setText(res.getString(6));
        }
            if(status2.equals("approved")||status2.equals("rejected"))
            {
                submit.setVisibility(View.GONE);
                submit3.setVisibility(View.GONE);
            }
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // ContentValues cv = new ContentValues();
                   // cv.put("STATUS","ACCEPTED");
                   // myDb.update("BOOKING_TABLE", cv, "_id="+posx, null);
                    myDb.update_status(posx);
                    submit.setVisibility(View.GONE);
                    submit3.setVisibility(View.GONE);
                    status2.setText("approved");
                //    myDb.execSQL("UPDATE DB_TABLE SET YOUR_COLUMN='newValue' WHERE id=6 ");
                }
            });
        submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ContentValues cv = new ContentValues();
                // cv.put("STATUS","ACCEPTED");
                // myDb.update("BOOKING_TABLE", cv, "_id="+posx, null);
                myDb.update_statusr(posx);
                submit.setVisibility(View.GONE);
                submit3.setVisibility(View.GONE);
                status2.setText("rejected");
                //    myDb.execSQL("UPDATE DB_TABLE SET YOUR_COLUMN='newValue' WHERE id=6 ");
            }
        });




    }
}

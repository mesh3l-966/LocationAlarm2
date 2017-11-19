package com.example.asus_pc.locationalarm2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);


        final EditText name = (EditText)findViewById(R.id.namein);
        EditText note = (EditText)findViewById(R.id.notein);
        final Button vmap = (Button)findViewById(R.id.map);
        TextView loc = (TextView) findViewById(R.id.address);



        final String sname = name.getText().toString();
        final String snote = note.getText().toString();
        final String sloc = loc.getText().toString();


        vmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(AddAlarm.this,forMap.class);
                //startActivity(intent);
            }
        });


        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   dbhelper db =new dbhelper(v.getContext());
                  SQLiteDatabase sql = db.getWritableDatabase();

              // db.deleteRow(3);


                db.insertRow(3,7,"jk","ddjjjjj");
                db.getAllrec();


               // String stmt1="select alarm_id from alarmloc";

                //Cursor c =sql.rawQuery(stmt1,null);
              //  c.moveToFirst();

                //name.setText(c.getInt(2)+"");







            }
        });

        // Hello Ali, This is after transformation
        //heloow messhal this is ali
        //clcit 3
        
    }
}

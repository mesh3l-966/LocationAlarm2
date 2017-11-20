package com.example.asus_pc.locationalarm2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class AddAlarm extends AppCompatActivity implements dialogMap.ifaceTest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);


         final EditText name = (EditText)findViewById(R.id.namein);
      final  EditText note = (EditText)findViewById(R.id.notein);
        final Button vmap = (Button)findViewById(R.id.map);
      final   TextView loc = (TextView) findViewById(R.id.address);






        vmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm =  getSupportFragmentManager();
                dialogMap d1 = new dialogMap();


                d1.show(fm,"d");

            }
        });


        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   dbhelper db =new dbhelper(v.getContext());
                  SQLiteDatabase sql = db.getWritableDatabase();

               // Log.d("myTag1",  name.getText().toString()+"--"+note.getText()+"--"+loc.getText());

              //db.deleteRow(1);
               // db.deleteRow(2);
               // db.deleteRow(3);
               // db.deleteRow(4);
              //  db.deleteRow(5);

                String stmt1="select max(alarm_id) from alarmloc";
                Cursor c =sql.rawQuery(stmt1,null);
                c.moveToFirst();

               db.insertRow( (c.getInt(0)+1),loc.getText().toString(),name.getText().toString(),note.getText().toString());
               db.getAllrec();

               Intent nt = new Intent(AddAlarm.this,MainActivity.class);
               startActivity(nt);











            }
        });

        // Hello Ali, This is after transformation
        //heloow messhal this is ali
        //clcit 3
        
    }


    public   void select_loc (String x){

        TextView txt1= (TextView) findViewById(R.id.address);
        txt1.setText(x.toString());


    }


}

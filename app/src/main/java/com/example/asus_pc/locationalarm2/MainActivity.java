package com.example.asus_pc.locationalarm2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        dbhelper db =new dbhelper(getApplicationContext());
        ArrayList <String> ar=db.getAllrec();
        setListAdapter(new ArrayAdapter< String >(this,android.R.layout.simple_list_item_1,ar));


        Button btn= (Button) findViewById(R.id.delBtn) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                EditText tv = (EditText) findViewById(R.id.editText) ;
                try {
                    dbhelper db =new dbhelper(getApplicationContext());
                    SQLiteDatabase sql = db.getWritableDatabase();
                    db.deleteRow(Integer.parseInt(tv.getText().toString()));
                    db.getAllrec();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(view.getContext(),"you must insert number",Toast.LENGTH_LONG).show();
                }

            }
        });



        FloatingActionButton add = (FloatingActionButton)findViewById(R.id.addfloat);
       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MainActivity.this,AddAlarm.class);
                startActivity(intent);


            }
        });








    }




}

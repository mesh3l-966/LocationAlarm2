package com.example.asus_pc.locationalarm2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton add = (FloatingActionButton)findViewById(R.id.addfloat);
       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MainActivity.this,AddAlarm.class);
                startActivity(intent);
            }
        });



        String stmt="select * from alarmloc";
        dbhelper db =new dbhelper(this.getApplicationContext());
        SQLiteDatabase sql = db.getWritableDatabase();
        Cursor c =         sql.rawQuery(stmt,null);





    }




}

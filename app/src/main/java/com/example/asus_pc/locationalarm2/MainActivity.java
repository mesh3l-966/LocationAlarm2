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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import java.util.ArrayList;

public class MainActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this code fix the layout when keyboard appear
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        dbhelper db =new dbhelper(getApplicationContext());
        ArrayList <String> ar=db.getAllrec();
        setListAdapter(new ArrayAdapter< String >(this,android.R.layout.simple_list_item_1,ar));





        Button btn= (Button) findViewById(R.id.delBtn) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText tv = (EditText) findViewById(R.id.editText) ;

                // add try catch Exception to make sure that the Edit text has a value
                try {
                    dbhelper db =new dbhelper(getApplicationContext());
                    SQLiteDatabase sql = db.getWritableDatabase();
                    db.deleteRow(Integer.parseInt(tv.getText().toString()));
                    db.getAllrec();

                    // when click on delete button, refresh the activity
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

    public void deleteAllRows(View view){
        try {
            dbhelper db = new dbhelper(getApplicationContext());
            SQLiteDatabase sql = db.getWritableDatabase();
            db.deleteAllRows();
            db.getAllrec();

            // when click on delete all button, refresh the activity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            } catch (Exception e){
        Toast.makeText(view.getContext(),"you must insert number",Toast.LENGTH_LONG).show();
    }
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

            String arr = l.getItemAtPosition(position).toString();
       // Toast.makeText(v.getContext(), arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3],Toast.LENGTH_LONG).show();

        Intent i = new Intent(MainActivity.this, EditAlarm.class);
        i.putExtra("info", arr);

        startActivity(i);

    }

}

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

public class AddAlarm extends AppCompatActivity {
    String location = null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_alarm);

            final EditText name = (EditText) findViewById(R.id.namein);
            final EditText note = (EditText) findViewById(R.id.notein);
            final Button vmap = (Button) findViewById(R.id.map);

            final TextView alaramName = (TextView) findViewById(R.id.textView);
            final TextView theNote = (TextView) findViewById(R.id.textView2);
            final TextView theLocation = (TextView) findViewById(R.id.textView3);

            String nName = "";
            String nNote = "";


            if (getIntent().getExtras().getString("STRING_I_NEED") != null) {
                location = getIntent().getExtras().getString("STRING_I_NEED");
                nName = getIntent().getExtras().getString("name");
                nNote = getIntent().getExtras().getString("note");
                name.setText(nName);
                note.setText(nNote);
            }

            if (location != null) {
                alaramName.append(" " + nName);
                theNote.append(" " + nNote);
                theLocation.append(" " + location);
            }

            vmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nt2 = new Intent(AddAlarm.this, MapsActivity.class);
                    nt2.putExtra("name", name.getText().toString());
                    nt2.putExtra("note", note.getText().toString());
                    nt2.putExtra("theType", "add");
                    startActivity(nt2);
                }
            });

            Button save = (Button) findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbhelper db = new dbhelper(v.getContext());
                    SQLiteDatabase sql = db.getWritableDatabase();

                    String stmt1 = "select max(alarm_id) from alarmloc";
                    Cursor c = sql.rawQuery(stmt1, null);
                    c.moveToFirst();
                    if (name.getText().toString().isEmpty())
                        name.setText("name");
                    if (note.getText().toString().isEmpty())
                        note.setText("note");
                    if (location != null) {
                        db.insertRow((c.getInt(0) + 1), location, name.getText().toString(), note.getText().toString());
                        db.getAllrec();
                        Intent nt = new Intent(AddAlarm.this, MainActivity.class);
                        startActivity(nt);
                    } else {
                        Toast.makeText(v.getContext(), "You must choose a location", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e){
            Toast.makeText(AddAlarm.this, e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}

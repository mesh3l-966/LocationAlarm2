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

public class EditAlarm extends AppCompatActivity {
     int yy = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_alarm);

            final EditText name = findViewById(R.id.namein);
            final EditText note = findViewById(R.id.notein);
            final Button vmap = findViewById(R.id.map);

            final TextView alaramName = findViewById(R.id.textView);
            final TextView theNote = findViewById(R.id.textView2);
            final TextView theLocation = findViewById(R.id.textView3);
            final TextView textView6 = (TextView) findViewById(R.id.textView6);

            String nName = "";
            String nNote = "";

            String location = null;

            if (getIntent().getExtras().getString("STRING_I_NEED") != null) {
                location = getIntent().getExtras().getString("STRING_I_NEED");
                nName = getIntent().getExtras().getString("name");
                nNote = getIntent().getExtras().getString("note");
                name.setText(nName);
                note.setText(nNote);
                textView6.setText(location.toString());
                yy = Integer.parseInt(getIntent().getExtras().getString("theId"));
            }

            if (getIntent().getExtras().getString("info") != null) {
                final String info[] = getIntent().getExtras().getString("info").toString().split("--");
                name.setText(info[2]);
                note.setText(info[3]);
                textView6.setText(info[1]);
                alaramName.append(" " + info[2]);
                theNote.append(" " + info[3]);
                theLocation.setText("Location: " + info[1]);
                String loc_id = info[0];
                yy = Integer.parseInt(info[0]);
            }

            if (location != null) {
                alaramName.append(" " + nName);
                theNote.append(" " + nNote);
                theLocation.append(" " + location.toString());
            }

            vmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nt2 = new Intent(EditAlarm.this, MapsActivity.class);
                    nt2.putExtra("name", name.getText().toString());
                    nt2.putExtra("note", note.getText().toString());
                    nt2.putExtra("theId", String.valueOf(yy));
                    nt2.putExtra("theType", "edit");
                    startActivity(nt2);
                }
            });

            final String theLocation2 = theLocation.getText().toString();
            Button save = findViewById(R.id.update);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dbhelper db = new dbhelper(v.getContext());
                        db.updateRow(yy, textView6.getText().toString(), name.getText().toString(), note.getText().toString());
                        Intent nt = new Intent(EditAlarm.this, MainActivity.class);
                        startActivity(nt);
                    } catch (Exception e) {
                        Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e){
             Toast.makeText(EditAlarm.this, e.toString(),Toast.LENGTH_LONG).show();

        }

    }
}

package com.example.asus_pc.locationalarm2;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this code fix the layout when keyboard appear
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        dbhelper db = new dbhelper(getApplicationContext());
       final ArrayList<String> ar = db.getAllrec();

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar));


        LocationManager lm = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);
        LocationListener ls = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Log.d("my_log", ar.toString());
                if(ar.size() > 0) {
                    TextView textView4 = (TextView) findViewById(R.id.textView4);
                    LatLng ll = new LatLng(24.864, 46.796);
                    float[] results = new float[10];
                    ArrayList<String> ar2 = new ArrayList<>();

                    for(int i=0; i < ar.size(); i++) {
                        String str = ar.get(i);

                        str = str.substring(str.indexOf('(') + 1, str.indexOf(')'));
                        double alt = Double.parseDouble(str.split(",")[0]);
                        double longitude = Double.parseDouble(str.split(",")[1]);

                        // 10--lat/lng: (25.567559126828513,45.22895269095898)--aa--bb

                        //
                        Location.distanceBetween(location.getAltitude(), location.getLongitude(), alt, longitude, results);
//
                        ar2.add(" " + results[1]);


                    }
                    Collections.sort(ar2);
                    /*
                    final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.sound);

                    if(Double.parseDouble(ar2.get(0)) < 1000)
                    {
                        mp.start();

                    } else {
                        mp.pause();
                    }
                    */
                    textView4.setText(" " + (Float.parseFloat(ar2.get(0))));
                    //Log.d("aaa", ar2.toString());

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ls);

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
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}

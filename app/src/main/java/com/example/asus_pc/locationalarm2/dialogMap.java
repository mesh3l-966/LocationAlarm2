package com.example.asus_pc.locationalarm2;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/*


YOUR API KEY for the map

AIzaSyCR07q90D-TIEGE_nti3PEYhtp0gUzRalA

using : team4prjct email



 */

public class dialogMap extends DialogFragment {
    public static interface ifaceTest {
        public abstract void select_loc (String x);
    }

    private ifaceTest mlsnr;

    public void onAttach(Activity AddAlarm){
        super.onAttach(AddAlarm);
        try {
            this.mlsnr=(ifaceTest)AddAlarm;

        }catch (final ClassCastException e){
            throw new ClassCastException(AddAlarm.toString() +"dddd");

        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rv =inflater.inflate(R.layout.activity_dialog_map, container, false);



        Button x = (Button) rv.findViewById(R.id.btn_cancel);
        Button o = (Button) rv.findViewById(R.id.btn_ok);
        // final DatePicker dp = (DatePicker) rv.findViewById(R.id.datePicker);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });



        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act();

            }
        });

        return rv;
    }


    public void act (){


        EditText et= (EditText) this.getView().findViewById(R.id.edt_txt);




        this.mlsnr.select_loc(et.getText().toString());

        getDialog().dismiss();

    }
}

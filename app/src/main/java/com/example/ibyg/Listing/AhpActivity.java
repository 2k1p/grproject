package com.example.ibyg.Listing;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;

import java.util.ArrayList;


public class AhpActivity extends BasicActivity {
    public static String text = "";
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahp);

        setToolbarTitle("원하는 카페찾기");


        arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");

        final String[] select_item = {""};


        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner dropdown2 = (Spinner) findViewById(R.id.spinner2);
        Spinner dropdown3 = (Spinner) findViewById(R.id.spinner3);
        Spinner dropdown4 = (Spinner) findViewById(R.id.spinner4);
        Spinner dropdown5 = (Spinner) findViewById(R.id.spinner5);
        Spinner dropdown6 = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(adapter);
        dropdown2.setAdapter(adapter);
        dropdown3.setAdapter(adapter);
        dropdown4.setAdapter(adapter);
        dropdown5.setAdapter(adapter);
        dropdown6.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                select_item[0] = String.valueOf(arrayList.get(arg2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });



        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select_item[0].equals("0")) {
                    myStartActivity(sagi.class);
                }else if (select_item[0].equals("1")) {
                    myStartActivity(sagi.class);
                }else if (select_item[0].equals("2")) {
                    myStartActivity(sagi.class);
                }else if (select_item[0].equals("3")) {
                    myStartActivity(sagi.class);
                }else if (select_item[0].equals("4")) {
                    myStartActivity(sagi2.class);
                }else if (select_item[0].equals("5")) {
                    myStartActivity(sagi2.class);
                }else if (select_item[0].equals("6")) {
                    myStartActivity(sagi2.class);
                }else if (select_item[0].equals("7")) {
                    myStartActivity(sagi3.class);
                }else if (select_item[0].equals("8")) {
                    myStartActivity(sagi3.class);
                }else if (select_item[0].equals("9")) {
                    myStartActivity(sagi3.class);
                }
            }
        });

    }
}

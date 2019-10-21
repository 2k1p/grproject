package com.example.ibyg.Listing;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;


public class AhpActivity extends BasicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahp);

        setToolbarTitle("카페 선택 도우미");

        final EditText edittext = (EditText) findViewById(R.id.editText);
        final EditText edittext2 = (EditText) findViewById(R.id.editText2);
        final EditText edittext3 = (EditText) findViewById(R.id.editText3);
        final EditText edittext4 = (EditText) findViewById(R.id.editText4);
        final EditText edittext5 = (EditText) findViewById(R.id.editText5);
        final EditText edittext6 = (EditText) findViewById(R.id.editText6);

        Button button = (Button) findViewById(R.id.button2);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStartActivity(sagi.class);
            }
        });
    }
}

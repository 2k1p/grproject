package com.example.ibyg.Listing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;


public class AhpActivity extends BasicActivity {
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahp);

        setToolbarTitle("원하는 카페찾기");

        final EditText edittext = (EditText) findViewById(R.id.editText);
        final EditText edittext2 = (EditText) findViewById(R.id.editText2);
        final EditText edittext3 = (EditText) findViewById(R.id.editText3);
        final EditText edittext4 = (EditText) findViewById(R.id.editText4);
        final EditText edittext5 = (EditText) findViewById(R.id.editText5);
        final EditText edittext6 = (EditText) findViewById(R.id.editText6);

        Button button = (Button) findViewById(R.id.button2);

        number = edittext.getText().toString();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = edittext.getText().toString();
                if(Integer.parseInt(number) >= 0 && Integer.parseInt(number) <= 2){ //0,1,2
                    myStartActivity(sagi.class);
                }else if(Integer.parseInt(number) >=3 && Integer.parseInt(number) <= 5){ //3,4,5
                    myStartActivity(sagi2.class);
                }else if(Integer.parseInt(number) >=6 && Integer.parseInt(number) <= 9){  //6,7,8,9
                    myStartActivity(sagi3.class);
                }
            }
        });
    }
}

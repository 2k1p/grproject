package com.example.ibyg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AhpActivity extends BasicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahp);

        final EditText edittext = (EditText) findViewById(R.id.editText);
        final EditText edittext2 = (EditText) findViewById(R.id.editText2);
        final EditText edittext3 = (EditText) findViewById(R.id.editText3);
        final EditText edittext4 = (EditText) findViewById(R.id.editText4);
        final EditText edittext5 = (EditText) findViewById(R.id.editText5);
        final EditText edittext6 = (EditText) findViewById(R.id.editText6);

        Button button = (Button)findViewById(R.id.button2);
        final TextView textView = (TextView)findViewById(R.id.textView29);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //처음에 스트링으로 값을 가져온다.
                String res1 = edittext.getText().toString();
                String res2 = edittext2.getText().toString();
                String res3 = edittext3.getText().toString();
                String res4 = edittext4.getText().toString();
                String res5 = edittext5.getText().toString();
                String res6 = edittext6.getText().toString();

                //스트링으로 가져온 값을 더블로 형변환
                double dou1 = Double.parseDouble(res1);
                double dou2 = Double.parseDouble(res2);
                double dou3 = Double.parseDouble(res3);
                double dou4 = Double.parseDouble(res4);
                double dou5 = Double.parseDouble(res5);
                double dou6 = Double.parseDouble(res6);

                /*그걸 합으로 dobres에 담는다
                double dobres = dou1+dou2+dou3;*/

                //score 배열에 사용자 입력 값을 받았다. score배열은 사용자 입력받는 점수값 배열이다.
                double[][] score = {{1, dou1, dou2, dou3}, {(1 / dou1), 1, dou4, dou5}, {(1 / dou2), (1 / dou4), 1, dou6}, {(1 / dou3), (1 / dou5), (1 / dou6), 1}};


                //textView로 넘기기위해 Double형태의 인덱스값을 String으로 바꾼것  결국 출력되게끔. 이건 출력되는 코드
                String result = "";
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        Log.d("hoon", "값 :" + score[i][j]);
                        result = result + Double.toString(score[i][j]) + ", ";
                        textView.setText(result);
                    }
                }



            }
        });






    }
}

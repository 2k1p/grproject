package com.example.ibyg.QNA;

import android.os.Bundle;
import android.view.View;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;

public class QnaManager extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna_manager);

        setToolbarTitle("1:1문의 관리");

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.qnaTextview:
                    break;
            }
        }
    };


}

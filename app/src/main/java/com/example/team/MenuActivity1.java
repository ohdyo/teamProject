package com.example.team;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity1 extends AppCompatActivity {

    private TextView cal_text;
    private Button btnOk;
    private CheckBox shoulder, chest, lowerBody, back;
    private EditText sportName, setNum, realNum;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1);

        // 날짜에 값 느는 곳
        cal_text = (TextView) findViewById(R.id.cal_text);

        Intent intent = new Intent(this.getIntent());
        int year = intent.getIntExtra("year",0);
        int month = intent.getIntExtra("month",0);
        int dayOfMonth = intent.getIntExtra("dayOfMonth",0);
        Log.d("일수","날짜 : " + year + month + dayOfMonth);
        cal_text.setText(year + "/" + month +"/" + dayOfMonth);

        //운동 부위 클릭
        shoulder = (CheckBox) findViewById(R.id.shoulder);
        chest = (CheckBox) findViewById(R.id.chest);
        lowerBody = (CheckBox) findViewById(R.id.lowerBody);
        back = (CheckBox) findViewById(R.id.back);

        //텍스트 입력하는 곳(이름, 셋트, 횟수)
        sportName = (EditText) findViewById(R.id.sportName);
        setNum = (EditText) findViewById(R.id.setNum);
        realNum = (EditText) findViewById(R.id.realNum);

        // 확인 누르는 곳 ( 값 넘기기)
        btnOk = (Button) findViewById(R.id.okBtn);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("date1", cal_text.getText().toString());
                if(shoulder.isChecked()){
                    intent.putExtra("checkBox","어깨");
                } else if(chest.isChecked()){
                    intent.putExtra("checkBox","가슴");
                } else if(lowerBody.isChecked()){
                    intent.putExtra("checkBox","하체");
                } else if(back.isChecked()){
                    intent.putExtra("checkBox","등");
                }
                intent.putExtra("sportName", sportName.getText().toString());
                intent.putExtra("setNum", setNum.getText().toString());
                intent.putExtra("realNum", realNum.getText().toString());

                setResult(0, intent);
                finish();
            }
        });


    }

}

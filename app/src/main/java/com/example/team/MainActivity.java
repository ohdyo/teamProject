package com.example.team;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.ProgressBar;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button schAdd;
    private CalendarView calendarView;
    private int year, month, dayOfMonth;

    private ListView sportList;
    private ArrayList<Data> sportDataList = new ArrayList<> ();

    private Button btnOk;
    private int amount=0;

    private ProgressBar prgBar;


    // ActivityResultLauncher Method DB가 없어서 사용, 메뉴엑티비티의 내용을 서버에 넣으면 이 메서드 삭제 가능
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 0) {
                        String date = result.getData().getStringExtra("date1");
                        String checkBox = result.getData().getStringExtra("checkBox");
                        String sportName = result.getData().getStringExtra("sportName");
                        String setNum = result.getData().getStringExtra("setNum");
                        String realNum = result.getData().getStringExtra("realNum");
                        Log.i("디버깅", "코드1 : " + date);
                        Log.i("디버깅", "코드2 : " + checkBox);
                        Log.i("디버깅", "코드3 : " + sportName);
                        Log.i("디버깅", "코드4 : " + setNum);
                        Log.i("디버깅", "코드5 : " + realNum);
                        Data a = new Data(date, checkBox, sportName, setNum, realNum,false);

                        boolean b=false;
                        for(int j=0; j<sportDataList.size(); j++){
                            if(sportDataList.get(j).getDate().equals(year + "/" + month + "/" + dayOfMonth)){
                                b=true;
                            }
                        }
                        if(!b){
                            amount++;
                            prgBar.setMax(amount);
                        }
                        sportDataList.add(a);

                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendarView1);
        schAdd = (Button) findViewById(R.id.schAdd);
        sportList = (ListView) findViewById(R.id.sportList);



        //날짜 값을 메뉴1으로 넘기기 and 캘린더 클릭시 일정 보여주기
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Log.i("디버깅", "선택한 날짜는 " + i + "년 " + (i1 + 1) + "월 " + i2 + "일");
                year = i; month = i1+1; dayOfMonth = i2;

                Log.i("디버깅", "코드6 : " + sportDataList);

                ArrayList<Data> a = new ArrayList<> ();

                for(int j = 0; j < sportDataList.size(); j++) {
                    if(sportDataList.get(j).getDate().equals(year+"/"+month+"/"+dayOfMonth)) {
                        a.add(sportDataList.get(j));
                    }
                }
                //리스트 뷰에 데이터 뿌려주기
               listAddData(a);

            }
        });

        //일정 추가 버튼 클릭 시 메뉴 1로 날짜 넘기기
        schAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity1.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("dayOfMonth", dayOfMonth);
                startActivityResult.launch(intent);
            }
        });

        btnOk = (Button) findViewById(R.id.btnOk);
        prgBar = (ProgressBar) findViewById(R.id.prgBar);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cnt = false;
                int a = 0;
                ArrayList<String>  strArray = new ArrayList<String> ();
                for(int j=0; j<sportDataList.size(); j++){
                    if(sportDataList.get(j).getDate().equals(year + "/" + month + "/" + dayOfMonth)){
                        //이전에 완료했는지 검사하는 if 문 && 2번쨰부터 내가 true로 셋팅해서 if문 안걸침 else 로 감
                        if(sportDataList.get(j).getOkOrNo() == false){
                           cnt = true; // 프로그레스바 갱신
                            strArray.add(sportDataList.get(j).getDate());
                           sportDataList.get(j).setOkOrNo(true);
                        }
                    }
                    else{
                        Log.i("분1",Boolean.toString(sportDataList.get(j).getOkOrNo() ));
                        Log.i("분2",Boolean.toString(!strArray.contains(sportDataList.get(j).getDate())));
                        Log.i("분3", sportDataList.get(j).getDate());
                        // 이전에 해당된 데이터가 아니였는지 검사
                        if(sportDataList.get(j).getOkOrNo()){
                            if(!strArray.contains(sportDataList.get(j).getDate())){
                                a++;
                                strArray.add(sportDataList.get(j).getDate());
                            }
                        }
                    }
                }
                if(cnt){
                    //프로그래스 바 증가
                    a++;
                    Log.i("디버깅 분자", a+"");
                    Log.i("디버깅 분모", amount+"");
                    prgBar.setProgress(a);
                }
            }
        });


    }

    // 리스트에 데이터 삽입 메서드 & 어뎁터 셋팅팅
   public void listAddData(ArrayList<Data> arrayList){
        Log.i("디버깅", arrayList.size() + "");
        final MyAdapter myAdapter = new MyAdapter(this,arrayList);
        sportList.setAdapter(myAdapter);


    }
}
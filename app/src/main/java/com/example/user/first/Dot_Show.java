package com.example.user.first;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;

public class Dot_Show extends AppCompatActivity {
// 파일 만든게 제대로 만들어 졌나 그냥 보여줄려고 만든 액티비티 나중에 없애도 됨 ㅇㅇ
    FileReader fr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show);
        //부모 뷰
        RelativeLayout RL = new RelativeLayout(this);
        //TextView 생성
        TextView view1 = new TextView(this);
        view1.setTextSize(5);
        view1.setTextColor(Color.BLACK);
        view1.setWidth(30);
        view1.setHeight(50);

        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Name = intent.getStringExtra("File_Name");
        int data;
        char ch;
        String tmp;
        File file = new File(File_Name);
        try{
            fr=new FileReader(file);
            while((data = fr.read()) != -1)
            {
                //글자 하나를 읽을 때마다 초기화.
                tmp=null;
                ch=(char)data;
                for(int i=5;i>0;i--)
                {
                    if(((ch>>i)&0x1) == 1)
                        tmp=tmp+"O";
                    else if(((ch>>i)&0x1)==0)
                        tmp=tmp+"X";
                    if(i==4 || i==2)
                        tmp=tmp+'\n';
                }
                view1.setText(tmp);
                view1.setPadding(15,0,10,0);
                RL.addView(view1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

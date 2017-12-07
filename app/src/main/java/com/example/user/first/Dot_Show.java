package com.example.user.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class Dot_Show extends AppCompatActivity {
// 파일 만든게 제대로 만들어 졌나 그냥 보여줄려고 만든 액티비티 나중에 없애도 됨 ㅇㅇ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show);

        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Name = intent.getStringExtra("File_Name");

        File file = new File(File_Name);

    }
}

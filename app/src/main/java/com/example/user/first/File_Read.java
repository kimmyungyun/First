package com.example.user.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class File_Read extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__read);

        Intent intent = getIntent();
        String File_Path = intent.getStringExtra("File_Path");

        textView = (TextView)findViewById(R.id.textView2);
        // http://recipes4dev.tistory.com/113
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_ALL = File_Path;

        //경로에 있는 파일을 가져옴.
        FileInputStream fileInputStream = null;
        int line;
        int jong,jung,cho;

        try{
            fileInputStream = new FileInputStream(File_ALL);
            Reader in = new InputStreamReader(fileInputStream,"euc-kr");
            BufferedReader reader = new BufferedReader(in);
            while((line = reader.read()) != -1){
                line = line - 0xAC00;
                jong = line % 28;
                jung = ((line - jong) / 28) % 21;
                cho = (((line - jong) / 28) - jung) / 21;
             }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

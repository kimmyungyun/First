package com.example.user.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class File_Read extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__read);

        Intent intent = getIntent();
        String File_Name = intent.getStringExtra("File_Name");
        String File_Path = intent.getStringExtra("File_Path");

        textView = (TextView)findViewById(R.id.textView2);
        // http://recipes4dev.tistory.com/113
    }
}

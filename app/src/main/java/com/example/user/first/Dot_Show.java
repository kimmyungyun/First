package com.example.user.first;

import android.content.Intent;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;

public class Dot_Show extends AppCompatActivity {
    private FileReader fr = null;
    private GridView girdview;
    private DotShowAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show);
        adapter = new DotShowAdapter();

        girdview = (GridView) findViewById(R.id.Dot_Show);
        Toast.makeText(getApplicationContext(), "읽는 것.", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Name = intent.getStringExtra("File_Name");
        int data;
        char ch;

        //그리드 뷰 어댑터 붙여주기.
        girdview.setAdapter(adapter);
        File file = new File(File_Name);
        try{
            fr=new FileReader(file);
            int j=0;
            while((data = fr.read()) != -1)
            {
                //글자 하나를 읽을 때마다 초기화.
                ch=(char)data;
                ItemAdd(ch);
            }
            //linearLayout.addView(parentLL2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ItemAdd(char A){
        String Name="a";
        for(int i=5;i>=0;i--)
        {
            if((( A >> i) & 0b1) == 1)
                Name = Name+"1";
            else if((( A >> i) & 0b1)==0)
                Name = Name+"0";
        }
        adapter.addItem(ContextCompat.getDrawable(this,getResources().getIdentifier(Name, "drawable", this.getPackageName())), "ㄱ");
    }
}

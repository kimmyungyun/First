package com.example.user.first;

import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

/*  원래 되던 것. 잠시 주석
public class Dot_Show extends AppCompatActivity {
    private FileReader fr = null;
    private GridView girdview;
    private DotShowAdapter adapter=null;
    FileInputStream fileInputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show);
        adapter = new DotShowAdapter();

        girdview = (GridView) findViewById(R.id.Dot_Show);
        Toast.makeText(getApplicationContext(), "읽는 것.", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Path = intent.getStringExtra("File_Path");
        //그리드 뷰 어댑터 붙여주기.
        girdview.setAdapter(adapter);
        int line;
        int jong,jung,cho;
        try {
            fileInputStream = new FileInputStream(File_Path);
            Reader in = new InputStreamReader(fileInputStream, "euc-kr");
            BufferedReader reader = new BufferedReader(in);

            while((line = reader.read()) != -1) {
                if (line == 32)    //띄어쓰기 일 경우 어떻게 처리할지 생각 해봐야할듯
                    ItemAdd((char)0b0,(char)0b0," ",0);
                else if (line == 13 || line == 10) //엔터인 경우인데 둘이 같이 붙어다님. 생각해봐야할듯.
                    ItemAdd((char)0b0,(char)0b0," ",0);
                else {
                    line = line - 0xAC00;
                    jong = line % 28;
                    jung = ((line - jong) / 28) % 21;
                    cho = (((line - jong) / 28) - jung) / 21;
                    Dot dot = new Dot(cho, jung, jong);

                    //초성일 경우 쓰기
                    switch (dot.whatcase / 6) {
                        case 0:
                            ItemAdd((char)dot.cb_cho1,(char)0b0,dot.ch_cho,0);
                            break;
                        case 1:
                            break;
                        default:
                            //ItemAdd((char)dot.cb_cho1,dot.ch_cho);
                            // 18.02.01 13:43 박종수 dot.ch_cho-> string: " "로 하여 빈칸이 나오게
                            ItemAdd((char)dot.cb_cho1,(char)dot.cb_cho2 ," ",1);
                            //ItemAdd((char)dot.cb_cho2,dot.ch_cho,2);
                            break;
                    }

                    //중성일 경우 쓰기
                    switch ((dot.whatcase % 6) / 3) {
                        case 0:
                            ItemAdd((char)dot.cb_jung1, (char)0b0,dot.ch_jung, 0);
                            break;
                        default:
                            ItemAdd((char)dot.cb_jung1, (char)dot.cb_jung2,dot.ch_jung,1);
                            //ItemAdd((char)dot.cb_jung2,dot.ch_jung,2);
                            break;
                    }

                    //종성일 경우 쓰기.
                    switch (dot.whatcase % 3) {
                        case 0:
                            ItemAdd((char)dot.cb_jong1,(char)dot.cb_jong2,dot.ch_jong,1);
                            //ItemAdd((char)dot.cb_jong2,dot.ch_jong,2);
                            break;
                        case 1:
                            ItemAdd((char)dot.cb_jong1,(char)0b0,dot.ch_jong,0);
                            break;
                        default:
                            break;
                    }
                }
            }
                reader.close();
        }catch(Exception e){}
    }
    public void ItemAdd(char A, char B, String Hangul,int type){
        String Name="a";
        String Name2="a";
        for(int i=5;i>=0;i--)
        {
            if((( A >> i) & 0b1) == 1)
                Name = Name+"1";
            else if((( A >> i) & 0b1)==0)
                Name = Name+"0";
        }

        for(int i=5;i>=0;i--)
        {
            if((( B >> i) & 0b1) == 1)
                Name2 = Name2+"1";
            else if((( B >> i) & 0b1)==0)
                Name2 = Name2+"0";
        }
        Log.d("Name 값.", "ItemAdd: Name : "+Name +" Name2 : "+Name2);
        Drawable tmp1 = ContextCompat.getDrawable(this,getResources().getIdentifier(Name, "drawable", this.getPackageName()));
        Drawable tmp2 = ContextCompat.getDrawable(this,getResources().getIdentifier(Name2, "drawable", this.getPackageName()));
        Log.d("Drawable 값.", "ItemAdd: Name : "+tmp1 +" Name2 : "+tmp2);
        adapter.addItem(tmp1,tmp2, Hangul,type);
    }
}
*/

public class Dot_Show extends AppCompatActivity {
    private FileReader fr = null;
    private ListView listView;
    private DotShowAdapter adapter=null;
    FileInputStream fileInputStream;
    ListView tmpListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show);
        adapter = new DotShowAdapter();

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_form,null);

        listView = (ListView) findViewById(R.id.Dot_Show_List);
        Toast.makeText(getApplicationContext(), "읽는 것.", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Path = intent.getStringExtra("File_Path");
        //그리드 뷰 어댑터 붙여주기.
        //listView.setAdapter(adapter);
        int line;
        int jong,jung,cho;
        try {
            fileInputStream = new FileInputStream(File_Path);
            Reader in = new InputStreamReader(fileInputStream, "euc-kr");
            BufferedReader reader = new BufferedReader(in);
            while((line = reader.read()) != -1) {
                Log.d("읽는중","읽는중");
                tmpListview = (ListView) view.findViewById(R.id.Listview_Form);
                Log.d("읽는중","읽는중2");
                tmpListview.setAdapter(adapter);
                Log.d("읽는중","읽는중3");
                if (line == 32)    //띄어쓰기 일 경우 어떻게 처리할지 생각 해봐야할듯
                    ItemAdd((char)0b0,(char)0b0," ",0);
                else if (line == 13 || line == 10) //엔터인 경우인데 둘이 같이 붙어다님. 생각해봐야할듯.
                    ItemAdd((char)0b0,(char)0b0," ",0);
                else {
                    line = line - 0xAC00;
                    jong = line % 28;
                    jung = ((line - jong) / 28) % 21;
                    cho = (((line - jong) / 28) - jung) / 21;
                    Dot dot = new Dot(cho, jung, jong);

                    //초성일 경우 쓰기
                    switch (dot.whatcase / 6) {
                        case 0:
                            ItemAdd((char)dot.cb_cho1,(char)0b0,dot.ch_cho,0);
                            break;
                        case 1:
                            break;
                        default:
                            //ItemAdd((char)dot.cb_cho1,dot.ch_cho);
                            // 18.02.01 13:43 박종수 dot.ch_cho-> string: " "로 하여 빈칸이 나오게
                            ItemAdd((char)dot.cb_cho1,(char)dot.cb_cho2 ," ",1);
                            //ItemAdd((char)dot.cb_cho2,dot.ch_cho,2);
                            break;
                    }

                    //중성일 경우 쓰기
                    switch ((dot.whatcase % 6) / 3) {
                        case 0:
                            ItemAdd((char)dot.cb_jung1, (char)0b0,dot.ch_jung, 0);
                            break;
                        default:
                            ItemAdd((char)dot.cb_jung1, (char)dot.cb_jung2,dot.ch_jung,1);
                            //ItemAdd((char)dot.cb_jung2,dot.ch_jung,2);
                            break;
                    }

                    //종성일 경우 쓰기.
                    switch (dot.whatcase % 3) {
                        case 0:
                            ItemAdd((char)dot.cb_jong1,(char)dot.cb_jong2,dot.ch_jong,1);
                            //ItemAdd((char)dot.cb_jong2,dot.ch_jong,2);
                            break;
                        case 1:
                            ItemAdd((char)dot.cb_jong1,(char)0b0,dot.ch_jong,0);
                            break;
                        default:
                            break;
                    }
                }
            }
            reader.close();
        }catch(Exception e){}
    }
    public void ItemAdd(char A, char B, String Hangul,int type){
        String Name="a";
        String Name2="a";
        for(int i=5;i>=0;i--)
        {
            if((( A >> i) & 0b1) == 1)
                Name = Name+"1";
            else if((( A >> i) & 0b1)==0)
                Name = Name+"0";
        }

        for(int i=5;i>=0;i--)
        {
            if((( B >> i) & 0b1) == 1)
                Name2 = Name2+"1";
            else if((( B >> i) & 0b1)==0)
                Name2 = Name2+"0";
        }
        Log.d("Name 값.", "ItemAdd: Name : "+Name +" Name2 : "+Name2);
        Drawable tmp1 = ContextCompat.getDrawable(this,getResources().getIdentifier(Name, "drawable", this.getPackageName()));
        Drawable tmp2 = ContextCompat.getDrawable(this,getResources().getIdentifier(Name2, "drawable", this.getPackageName()));
        Log.d("Drawable 값.", "ItemAdd: Name : "+tmp1 +" Name2 : "+tmp2);
        adapter.addItem(tmp1,tmp2, Hangul,type);
        if(adapter.getCount() == 6)
        {
            listView.addView(tmpListview);
        }
    }

}
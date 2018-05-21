package com.example.user.first;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class Dot_Show3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<DotShowItem> dotShowItems;
    FileInputStream fileInputStream;

    String File_Path;
    String File_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show3);

        dotShowItems = new ArrayList<DotShowItem>();
        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        File_Path = intent.getStringExtra("File_Path");
        File_Name = intent.getStringExtra("File_Name");

        //레이아웃 초기화 그리드형태로 초기화.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);

        //버튼 생성.
        Button b = (Button)findViewById(R.id.Button);
        //Connect 버튼 클릭 시 블루투스로 전송.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Dot_Show3.this, BlueTooth.class);
                intent.putExtra("File_Path",File_Path);
                intent.putExtra("File_Name",File_Name);
                startActivity(intent);
                finish();
            }
        });
        int line;
        int jong,jung,cho;
        try {
            fileInputStream = new FileInputStream(File_Path);
            //Reader in = new InputStreamReader(fileInputStream, "euc-kr");
            FileReader in = new FileReader(File_Path);
            BufferedReader reader = new BufferedReader(in);
            int qqq= 0; //파일 용량이 너무 커서 리사이클러뷰가 감당을 못함.(메모리 아웃나는듯)
            while((line = reader.read()) != -1) {
                qqq++;
                //Log.d("읽는중", "읽는중");
                if (qqq == 250) {
                    Toast.makeText(getApplicationContext(), "파일이 커 일부만 보여드립니다.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (line == 32){    //띄어쓰기 일 경우 어떻게 처리할지 생각 해봐야할듯
                    ItemAdd((char) 0b0, (char) 0b0, " ", 0);
                }
                else if (line == 13 || line == 10) { //엔터인 경우인데 둘이 같이 붙어다님. 생각해봐야할듯.
                    ItemAdd((char) 0b0, (char) 0b0, " ", 0);
                }
                // 18.04.16 16:12 영어일 경우 분리
                else if (line >= 65 && line <= 122){


                }
                else if(line >= 0xAC00 && line <= 0xD800) {
                    Log.d("Line : ", Integer.toString(line));
                    line = line - 0xAC00;
                    jong = line % 28;
                    jung = ((line - jong) / 28) % 21;
                    cho = (((line - jong) / 28) - jung) / 21;
                    Log.d("cho : ", Integer.toString(cho));
                    Log.d("jung : ", Integer.toString(jung));
                    Log.d("jong : ", Integer.toString(jong));
                    Dot dot = new Dot(cho, jung, jong);

                    //초성일 경우 쓰기
                    switch (dot.whatcase / 6) {
                        case 0:
                            ItemAdd((char) dot.cb_cho1, (char) 0b0, dot.ch_cho, 0);
                            break;
                        case 1:
                            break;
                        default:

                            // 18.02.01 13:43 박종수 dot.ch_cho-> string: " "로 하여 빈칸이 나오게
                            ItemAdd((char) dot.cb_cho1, (char) dot.cb_cho2, dot.ch_cho, 1);
                            break;
                    }
                    //중성일 경우 쓰기
                    switch ((dot.whatcase % 6) / 3) {
                        case 0:

                            ItemAdd((char) dot.cb_jung1, (char) 0b0, dot.ch_jung, 0);
                            break;
                        default:

                            ItemAdd((char) dot.cb_jung1, (char) dot.cb_jung2, dot.ch_jung, 1);
                            break;
                    }
                    //종성일 경우 쓰기.
                    switch (dot.whatcase % 3) {
                        case 0:
                            ItemAdd((char) dot.cb_jong1, (char) dot.cb_jong2, dot.ch_jong, 1);
                            break;
                        case 1:
                            ItemAdd((char) dot.cb_jong1, (char) 0b0, dot.ch_jong, 0);

                            break;
                        default:
                            break;
                    }
                }

            }
            //recyclerview 어댑터 초기화
            //작성해둔 Adapter 에 ArrayList 형태로 저장해 둔 ArrayList를 넘겨줌
            MyAdapter myAdapter = new MyAdapter(dotShowItems);

            //recyclerview에 adapter 세팅.
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);
            reader.close();
        }catch(Exception e){}

    }

    public void ItemAdd(char A, char B, String Hangul,int type){
        Log.d("어댑터 생성 전 : ","Add");
        String name="a";
        String name2="a";
        for(int i=5;i>=0;i--)
        {
            if((( A >> i) & 0b1) == 1)
                name = name+"1";
            else if((( A >> i) & 0b1)==0)
                name = name+"0";
        }

        for(int i=5;i>=0;i--)
        {
            if((( B >> i) & 0b1) == 1)
                name2 = name2+"1";
            else if((( B >> i) & 0b1)==0)
                name2 = name2+"0";
        }

        //Log.d("Name 값.", "ItemAdd: Name : "+name +" Name2 : "+name2);
        Drawable tmp1 = ContextCompat.getDrawable(this,getResources().getIdentifier(name, "drawable", this.getPackageName()));
        Drawable tmp2 = ContextCompat.getDrawable(this,getResources().getIdentifier(name2, "drawable", this.getPackageName()));
        //Log.d("Drawable 값.", "ItemAdd: Name : "+tmp1 +" Name2 : "+tmp2);
        if(type == 0) {
            //DotShowItem(Drawable icon, Drawable icon2, String title, int Type){
            DotShowItem item = new DotShowItem(tmp1, tmp2, Hangul, type);
            dotShowItems.add(item);
            Log.d("ADd 값.", "1");
        }
        if(type == 1)
        {
            DotShowItem item = new DotShowItem(tmp1, tmp2, Hangul, type);
            dotShowItems.add(item);
            Log.d("Add 값.", "2");
        }

    }
}

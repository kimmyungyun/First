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
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Dot_Show2 extends AppCompatActivity {
    private FileReader fr = null;
    private RecyclerView recyclerView;
    ArrayList<DotShowItem> dotShowItems;
    FileInputStream fileInputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show2);


        //레이아웃 초기화 그리드형태로 초기화.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        Intent intent = getIntent();
        //리스트 초기화
        dotShowItems = new ArrayList<DotShowItem>();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Path = intent.getStringExtra("File_Path");
        Button b = (Button)findViewById(R.id.Button);
        //Connect 버튼 클릭 시 블루투스로 전송.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Dot_Show2.this, BlueTooth.class);
                intent.putExtra("File_Path",File_Path);
                startActivity(intent);
                finish();
            }
        });
        int line;

        try {
            fileInputStream = new FileInputStream(File_Path);
            Reader in = new InputStreamReader(fileInputStream, "euc-kr");
            BufferedReader reader = new BufferedReader(in);

            while((line = reader.read()) != -1) {
                char A = (char)line;
                /*
                String Name="a";
                for(int i=5;i>=0;i--)
                {
                    if((( A >> i) & 0b1) == 1)
                        Name = Name+"1";
                    else if((( A >> i) & 0b1)==0)
                        Name = Name+"0";
                }
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dot_show,null);
                ImageView img = (ImageView)view.findViewById(R.id.Dot_img);
                Drawable tmp1 = ContextCompat.getDrawable(this,getResources().getIdentifier(Name, "drawable", this.getPackageName()));
                img.setImageDrawable(tmp1);
                gridLayout.addView(view);
                */
                ItemAdd((A));
            }
            MyAdapter myAdapter = new MyAdapter(dotShowItems);

            //recyclerview에 adapter 세팅.
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);
            reader.close();
        }catch(Exception e){}
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

        Log.d("Name 값.", "ItemAdd: Name : "+Name);
        Drawable tmp1 = ContextCompat.getDrawable(this,getResources().getIdentifier(Name, "drawable", this.getPackageName()));
        Log.d("Drawable 값.", "ItemAdd: Name : "+tmp1);
        DotShowItem item = new DotShowItem();
        item.setIcon(tmp1);
        item.setTitle(" ");
        item.setType(0);
        dotShowItems.add(item);
    }
}

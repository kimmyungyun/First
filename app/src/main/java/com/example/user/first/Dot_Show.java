package com.example.user.first;

import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;

public class Dot_Show extends AppCompatActivity {
// 파일 만든게 제대로 만들어 졌나 그냥 보여줄려고 만든 액티비티 나중에 없애도 됨 ㅇㅇ
    private FileReader fr = null;
    private  LinearLayout linearLayout;
    private String tmp;
    private LinearLayout parentLL;
    private LinearLayout.LayoutParams plControl;
    private LinearLayout parentLL2;
    private GridView girdview;
    private DotShowAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot__show);
        adapter = new DotShowAdapter();
        //부모 뷰
/*      이전버전 Dot_show
        linearLayout = (LinearLayout)findViewById(R.id.linear);
        //가로 줄 리니어 레이어
        parentLL = new LinearLayout(this);
        LinearLayout.LayoutParams plControl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        plControl.setMargins(5,5,5,5);
        parentLL.setLayoutParams(plControl);
        parentLL.setOrientation(LinearLayout.HORIZONTAL);

        //세로 줄 리니어 레이어
        parentLL2 = new LinearLayout(this);
        LinearLayout.LayoutParams plControl2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        plControl2.setMargins(5,5,5,5);
        parentLL2.setLayoutParams(plControl2);
        parentLL2.setOrientation(LinearLayout.VERTICAL);
*/
        girdview = (GridView) findViewById(R.id.Dot_Show);
        Toast.makeText(getApplicationContext(), "읽는것.", Toast.LENGTH_LONG).show();

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
                tmp="";
                ch=(char)data;
                for(int i=5;i>=0;i--)
                {
                    if(((ch>>i)&0x1) == 1)
                        tmp=tmp+"O";
                    else if(((ch>>i)&0x1)==0)
                        tmp=tmp+"X";
                    if(i==4 || i==2)
                        tmp=tmp+'\n';
                }
                /*
                TextView view1 = new TextView(this);
                view1.setId(j);
                view1.setTextSize(15);
                view1.setTextColor(Color.BLACK);
                view1.setWidth(100);
                view1.setHeight(180);
                view1.setText(tmp);
                view1.setPadding(15,0,10,0);
                j++;
                */
                ItemAdd(ch);
                /*  이전 버젼 Dot_Show
                parentLL.addView(view1);

                if(j%8==0){
                    parentLL2.addView(parentLL);
                    parentLL = new LinearLayout(this);
                    plControl2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    plControl2.setMargins(5,5,5,5);
                    parentLL.setLayoutParams(plControl2);
                    parentLL.setOrientation(LinearLayout.HORIZONTAL);
                }
                */
            }
            //linearLayout.addView(parentLL2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ItemAdd(char A){
        String Name="";
        for(int i=5;i>0;i--)
        {
            if((( A >> i) & 0b1) == 1)
                Name = Name+"1";
            else if((( A >> i) & 0b1)==0)
                Name = Name+"0";
        }
        switch (A) {
           // case 0b000001:    된소리 부분
           //     adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
            case 0b100000:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            case 0b001100:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            case 0b000110:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            case 0b001000:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            case 0b001001:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            case 0b101000:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            case 0b000010:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
            default:
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.text), "ㄱ");
                break;
        }
    }

}

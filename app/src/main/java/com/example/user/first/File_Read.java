package com.example.user.first;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;

public class File_Read extends AppCompatActivity {
    TextView textView;
    File DataFile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__read);
        //이전파일 삭제와 현재 새롭게 만드는 파일 두가지가 동시에 진행됨. 쓰레드 때문인지 뭔지... 같이 진행 안되게 할 방법이 필요.
        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Path = intent.getStringExtra("File_Path");
        //최상위 폴더
        String Root_Path = intent.getStringExtra("Root_Path");
        //읽어온 파일의 이름
        String File_Name = intent.getStringExtra("File_Name");
        //가져온 파일이름만 남기기
        final String FileName = File_Name.substring(0,File_Name.lastIndexOf("."));
        textView = (TextView)findViewById(R.id.textView2);
        String Root_Folder = Root_Path+"/E_Dot";
        //경로에 있는 파일을 가져옴.
        FileInputStream fileInputStream = null;
        int line;
        int jong,jung,cho;

        //폴더가 없으면 생성
        File Root_File = new File(Root_Folder);
        if(!Root_File.exists())
            Root_File.mkdir();
        DataFile = new File(Root_Folder+"/"+FileName+".dat");
        if(DataFile.exists())
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(File_Read.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                if(DataFile.delete())
                    Toast.makeText(getApplicationContext(), "이전 "+FileName + " 파일 삭제 완료.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "이전 "+FileName + " 파일 삭제 실패.", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //취소 버튼 눌렀을 때, 무슨일이 있어야할까..?
                        }
                    });
            alert.setMessage("이전 파일이 존재합니다. 삭제해도 되겠습니까?");
            alert.show();
        }
        //Toast.makeText(getApplicationContext(), FileName, Toast.LENGTH_LONG).show();
        try{
            BufferedWriter bfw = new BufferedWriter(new FileWriter(Root_File+"/"+FileName+".dat"));
            Toast.makeText(getApplicationContext(), "생성", Toast.LENGTH_LONG).show();
            //텍스트 파일 읽기.
            fileInputStream = new FileInputStream(File_Path);
            Reader in = new InputStreamReader(fileInputStream,"euc-kr");
            BufferedReader reader = new BufferedReader(in);
            while((line = reader.read()) != -1){
                line = line - 0xAC00;
                jong = line % 28;
                jung = ((line - jong) / 28) % 21;
                cho = (((line - jong) / 28) - jung) / 21;
                bfw.write(line+"\n");
            }
            bfw.flush();
            bfw.close();
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

package com.example.user.first;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Epub2Html extends AppCompatActivity {
    String Root_Folder;
    String Zip_Folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epub2_html);

        Intent intent = getIntent();
        //파일에 대한 모든 경로가 들어있음. (파일명까지)
        String File_Path = intent.getStringExtra("File_Path");
        //최상위 폴더
        String Root_Path = intent.getStringExtra("Root_Path");
        //읽어온 파일의 이름
        String File_Name = intent.getStringExtra("File_Name");
        //가져온 파일이름만 남기기

        //파일 이름 +.epub 파일명
        String name1 = File_Path.substring(File_Path.lastIndexOf("/")+1);
        //파일 주소.
        String name2 = File_Path.substring(0,File_Path.lastIndexOf("/"));
        //파일 이름만 따옴.
        String name3 = name1.substring(0,name1.lastIndexOf("."));

        Root_Folder = Root_Path+"/E_Dot";
        Zip_Folder = Root_Folder+"/Zip";

        //폴더가 없으면 생성
        File Root_File = new File(Root_Folder);
        if(!Root_File.exists())
            Root_File.mkdir();
        //zip 폴더가 없으면 생성.
        File Zip_File = new File(Zip_Folder);
        if(!Zip_File.exists())
            Zip_File.mkdir();
        //이 위로는 이상 없음.

        File filePre = new File(Zip_Folder+"/",name1);
        File fileNow = new File(Zip_Folder+"/",name3+".zip");

        copyFile(File_Path,Zip_Folder+"/"+name3+".epub");
        //파일 확장자 변경
          if(filePre.renameTo(fileNow))
            Toast.makeText(getApplicationContext(),"변경성공",Toast.LENGTH_LONG).show();
           else
            Toast.makeText(getApplicationContext(),"변경 실패",Toast.LENGTH_LONG).show();

   }
    //inFilePath 에 있는 것을 outFilePath 로 복사.
    //이 메소드를 한 이유는, 확장자를 그냥 변경할 경우 오리지널 파일이 사라짐.
    //채널을 사용한 이유는 일반 버퍼스트림을 이용할 경우 너무 오래걸림.
    public synchronized void copyFile(String inFilePath, String outFilePath)
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fcin = null;
        FileChannel fcout = null;

        try{
            //스트림 생성
            fis = new FileInputStream(inFilePath);
            fos = new FileOutputStream(outFilePath);

            fcin = fis.getChannel();
            fcout = fos.getChannel();

            long size = fcin.size();

            fcin.transferTo(0,size,fcout);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        //자원 해제
        try {
            fcout.close();
        }catch(IOException ioe){}
        try{
            fcin.close();
        }catch(IOException ioe){}
        try{
            fos.close();
        }catch(IOException ioe){}
        try{
            fis.close();
        }catch(IOException ioe){}
    }
}

package com.example.user.first;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Epub2Html extends AppCompatActivity {
    String Root_Folder;
    String Zip_Folder;
    File FolderFile = null;
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


           /* 박종수
              18.01.05 15:00
              epub파일을 zip파일로 바꾼후 zip파일의 압축을 해제
            */
           String Foldername=Zip_Folder+"/"+name3;
           makeFolder(Foldername);
          String unzipfilepath = fileNow.getAbsolutePath();
        Toast.makeText(getApplicationContext(),unzipfilepath,Toast.LENGTH_LONG).show();
          try{
              unZipZipfile(unzipfilepath,Foldername);
              Toast.makeText(getApplicationContext(),"압축성공",Toast.LENGTH_LONG).show();
          }
          catch (Throwable e){
              e.printStackTrace();
              Toast.makeText(getApplicationContext(),"압축실패",Toast.LENGTH_LONG).show();
          }



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
    /* 박종수
       18.01.05 12:02
       폴더경로+이름을 받고
       폴더가 있으면 폴더를 삭제할것인지 물어보고, 폴더가 없으면 폴더를 만든다.
     */
    public void makeFolder (final String foldername)
    {
        try {

            FolderFile = new File(foldername);

            //폴더가 존재하지 않으면 폴더생성
            if (!FolderFile.exists())
                FolderFile.mkdir();

            //폴더가존재하면 폴더명1, 폴더명2 순으로 존재유무를 판별하고 존재하지않을때까지 숫자를 크게한후 폴더 생성
            else
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(Epub2Html.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (FolderFile.delete())
                            Toast.makeText(getApplicationContext(), "이전 " + foldername + " 파일 삭제 완료.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "이전 " + foldername + " 파일 삭제 실패.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //취소 버튼 눌렀을 때, 무슨일이 있어야할까..?
                                int makeFoldercounti=1;
                                FolderFile=new File(foldername+String.valueOf(makeFoldercounti));
                                while(!FolderFile.exists())
                                {
                                    makeFoldercounti++;
                                    FolderFile=new File(foldername+String.valueOf(makeFoldercounti));
                                }
                                FolderFile.mkdir();
                            }
                        });
                alert.setMessage("이전 파일이 존재합니다. 삭제해도 되겠습니까?");
                alert.setCancelable(false);
                alert.show();

            }

        }catch(Exception e){
        }
    }
    /*
    박종수
    18.01.05 15:00
    위에서 압축된 zip파일을 해제하기 위해 만듬
    http://nowonbun.tistory.com/321
     */
    public void unZipZipfile (String zipFileName, String unZipdirectory) throws Throwable
    {
        File zipfile = new File(zipFileName);
        FileInputStream unZipFileInputStream = null;
        ZipInputStream unZipZipInputStream = null;
        ZipEntry unZipZipEntry = null;
        try{
            //파일스트림
            unZipFileInputStream= new FileInputStream(zipfile);
            //Zip 파일 스트림
            unZipZipInputStream = new ZipInputStream(unZipFileInputStream);
            while((unZipZipEntry=unZipZipInputStream.getNextEntry()) != null){
                String inzipfilename = unZipZipEntry.getName();
                File file = new File(unZipdirectory, inzipfilename);
                //entiry가 폴더면 폴더 생성
                if (unZipZipEntry.isDirectory()) {
                    file.mkdirs();
                } else {
                    //파일이면 파일 만들기
                    createFile(file, unZipZipInputStream);
                }
            }
        }
        catch (Throwable e){
            throw e;
        } finally {
            if (unZipZipInputStream != null)
                unZipZipInputStream.close();
            if (unZipZipInputStream != null)
                unZipZipInputStream.close();
        }
    }
    /*
    박종수
    180107 22:32
    위의 UNZIP 클래스에서 압축을 해제할때 파일을 생성하는 클래스
    참조 http://nowonbun.tistory.com/321
     */
    private static void createFile(File file, ZipInputStream zis) throws Throwable {
        //디렉토리 확인
        File parentDir = new File(file.getParent());
        //디렉토리가 없으면 생성하자
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        FileOutputStream fos = null;
        fos= new FileOutputStream(file);
        //파일 스트림 선언
        try{
            byte[] buffer = new byte[256];
            int size = 0;
            //Zip스트림으로부터 byte뽑아내기
            while ((size = zis.read(buffer)) > 0) {
                //byte로 파일 만들기
                fos.write(buffer, 0, size);
            }
        } catch (Throwable e) {
            throw e;
        }
    }



}

package com.example.user.first;
//파일 탐색기 교체될 액티비티 ( 이걸로 교체할거임)
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class File_Find2 extends AppCompatActivity {
    private String root = Environment.getExternalStorageDirectory().getAbsolutePath();  //최상위 폴더
    private String CurPath = Environment.getExternalStorageDirectory().getAbsolutePath();   //현재 탐색하는 폴더
    private ArrayList<String> itemFiles = new ArrayList<String>();  //display 되는 파일이나 폴더이름
    private ArrayList<String> pathFiles = new ArrayList<String>();  // 화면에 display 되는 list의 경로와 이름이 붙어있는 목록
    private ListViewAdapter2 adapter=null;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__find2);
        adapter = new ListViewAdapter2();

        listview = (ListView) findViewById(R.id.File_View2);
        listview.setAdapter(adapter);

        getDir(root);

        //adapter.addItem(ContextCompat.getDrawable(this,R.drawable.folder),"First","설명");
        //adapter.addItem(ContextCompat.getDrawable(this,R.drawable.folder),"Second","설명");

        //listview 아이템 눌렀을 경우 실행 될 행동.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //눌린 위치의 정보를 가져옴
                ListViewItem ob = adapter.getItem(position);
                //눌렀을 때 폴더 이동 함수를 호출
                itemClick(ob.getTitle(),ob.getDesc());
            }
        });
    }
    private void getDir(String dirPath)
    {
        if(!dirPath.endsWith("/")) dirPath = dirPath+"/";
        File file = new File(dirPath);
        File[] files = file.listFiles();
        if( files == null) {
            Toast.makeText(getApplicationContext(), dirPath, Toast.LENGTH_LONG).show();
            return;
        }
        //ArrayList 내용을 다시 채우기 위하여 비움. (보여주는 리스트 지우는 거라 보면 됨)
        itemFiles.clear();
        pathFiles.clear();

        if( !root.endsWith("/")) root = root+"/";
        if( !root.equals(dirPath)){
            itemFiles.add("../");
            pathFiles.add(file.getParent());
        }
        //찾아낸 파일 및 폴더 리스트를 배열에다가 정리.
        for(int i=0; i<files.length;i++) {
            File f = files[i];
            pathFiles.add(f.getPath());

            if (f.isDirectory())
                itemFiles.add(f.getName() + "/");
            else {
                //Toast.makeText(getApplicationContext(), f.getName(), Toast.LENGTH_LONG).show();
                itemFiles.add(f.getName());
            }
        }
        Move_folder();
    }
    private void itemClick(String name, String path)
    {
        //일단은 listview 어댑터에 넣은 파일 이름이랑 파일 위치만 띄웠는데 이것을 고쳐서
        //txt파일 클릭시에 이 파일을 사용 할거냐는 다이얼로그를 띄우고, 그 파일을 점자 파일로 변환 시켜야함.
        //epub 파일의 경우 zip 파일로 바꾸고 해부해서 html 파일 찾아내기.
        File file = new File(path);
        if(file.isDirectory())
        {
            if(file.canRead()){
                CurPath = path;
                //데이터 정보가 바뀔시 리스트 뷰 다시 재정비.
                getDir(CurPath);

            }else{  //요 부분 없어도 되는 부분.
                new AlertDialog.Builder(this)
                        .setTitle("["+file.getName() + "] folder can't be read!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        }
        //파일 일 때
        else{
            final String Name = file.getName();
            final String Path = file.getPath();
            //누른 파일이 텍스트 파일이면
            if(file.getName().endsWith(".txt"))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(File_Find2.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //확인 버튼 누르면 이 텍스트 파일을 읽어 와야됨.
                        //다른 액티비티에서 읽어 올려고 함.
                        Intent intent = new Intent(
                                getApplicationContext(), File_Read.class);
                        //파일 경로 전송.
                        intent.putExtra("File_Path", Path);
                        intent.putExtra("Root_Path",root);
                        intent.putExtra("File_Name",Name);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //취소 버튼 눌렀을 때, 아무일도 없어도 됨.
                            }
                        });
                alert.setMessage("이 텍스트 파일을 변환 하시겠습니까?");
                alert.show();
            }
            //누른 파일이 epub 파일이면
            else if(file.getName().endsWith(".epub"))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(File_Find2.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //확인 버튼 누르면 이 epbu 파일을 읽어 와야됨.
                        //다른 액티비티에서 읽어 올려고 함.
                        Intent intent = new Intent(
                                getApplicationContext(), Epub2Html.class);
                        //파일 경로 전송.
                        intent.putExtra("File_Path", Path);
                        intent.putExtra("Root_Path",root);
                        intent.putExtra("File_Name",Name);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //취소 버튼 눌렀을 때, 아무일도 없어도 됨.
                    }
                });
                alert.setMessage("이 전자책 파일을 변환 하시겠습니까?");
                alert.show();
            }
            else {
                new AlertDialog.Builder(this)
                        .setTitle("[" + file.getName() + "]")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        }
    }
    private void Move_folder()
    {
        //Listview 초기화 및 다시 그리기.
        ListViewAdapter2 adapter2 = new ListViewAdapter2();
        adapter = adapter2;
        listview.setAdapter(adapter);
        for(int i=0;i<itemFiles.size();i++)
        {
            // 여기서 listview 에 넣는데 확장자 명에 따라 그림을 구분 해줘야됨.
            if(itemFiles.get(i).endsWith(".txt"))
                adapter.addItem(ContextCompat.getDrawable(this,R.drawable.text),itemFiles.get(i),pathFiles.get(i));
            else if(itemFiles.get(i).endsWith(".png") || itemFiles.get(i).endsWith(".jpg") || itemFiles.get(i).endsWith(".dat"))
                adapter.addItem(ContextCompat.getDrawable(this,R.drawable.etc),itemFiles.get(i),pathFiles.get(i));
            else if(itemFiles.get(i).endsWith(".epub"))
                adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_action_name),itemFiles.get(i),pathFiles.get(i));
            else
                adapter.addItem(ContextCompat.getDrawable(this,R.drawable.folder),itemFiles.get(i),pathFiles.get(i));
        }
        adapter.notifyDataSetChanged();
    }

}

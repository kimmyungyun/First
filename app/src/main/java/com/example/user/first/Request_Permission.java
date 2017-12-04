package com.example.user.first;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Request_Permission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__permission);
        RequestPermission();
    }
    public void RequestPermission(){
        //마시멜로우 이상인지 체크
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
            // 저장장치에 접근 할수 있는지 확인
            int permissionResult = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            if(permissionResult == PackageManager.PERMISSION_DENIED){
                //한번이라도 거부한적이 있다면 조사 거부했다면 true
                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Request_Permission.this);
                    dialog.setTitle("권한이 필요 합니다.").setMessage("이 기능을 사용하기 위해서는 단말기의 " +
                            "\"파일 관리\" 권한이 필요합니다. 계쏙 하시겠습니까?").setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                            }
                        }
                    }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(Request_Permission.this,"기능을 취소했습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }).create().show();
                }
                //최초로 권한을 요청할 때
                else{
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                }
            }
            // 데이터 관련 권한이 있을 때.
            else{
                //일단 파일 찾는 액티비티로 이동
                Intent intent = new Intent(
                        getApplicationContext(), File_Find.class);
                startActivity(intent);
                this.finish();
            }

        }
        //사용자의 OS버전이 마시멜로우 이하 일 때
        else{
            //일단 파일 찾는 액티비티로 이동
            Intent intent = new Intent(
                    getApplicationContext(), File_Find.class);
            startActivity(intent);
            this.finish();
        }
    }
}

package com.example.user.first;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
      //check for permission
        int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_DENIED ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //ask for permission
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }
            } else {
                Intent intent = new Intent(Request_Permission.this, File_Find2.class);
                startActivity(intent);
                finish();
            }
        }
        else
        {
            Intent intent = new Intent(Request_Permission.this, File_Find2.class);
            startActivity(intent);
            finish();
        }
    }
}

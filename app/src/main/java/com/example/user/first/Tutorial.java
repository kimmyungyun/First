package com.example.user.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Tutorial extends AppCompatActivity {
    int status=0;
    ImageView imageView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //왼쪽 버튼
        Button b1 = (Button)findViewById(R.id.button1);
        //오른쪽 버튼
        Button b2 = (Button)findViewById(R.id.button2);

        //이미지뷰
        imageView = (ImageView)findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.tutorial1);
        //왼쪽버튼 기능
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (status)
                {
                    case 0:
                        //아무것도 안함.
                        break;
                    case 1:
                        status = status - 1;
                        imageView.setImageResource(R.drawable.tutorial1);
                        break;
                    case 2:
                        status = status - 1;
                        imageView.setImageResource(R.drawable.tutorial4);
                        break;
                    case 3:
                        status = status - 1;
                        imageView.setImageResource(R.drawable.tutorial2);
                        break;
                    case 4:
                        status = status - 1;
                        imageView.setImageResource(R.drawable.tutorial5);
                        break;
                    case 5:
                        status = status - 1;
                        imageView.setImageResource(R.drawable.tutorial3);
                        break;
                    default:
                        break;
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (status)
                {
                    case 0:
                        status = status + 1;
                        imageView.setImageResource(R.drawable.tutorial4);
                        break;
                    case 1:
                        status = status + 1;
                        imageView.setImageResource(R.drawable.tutorial2);
                        break;
                    case 2:
                        status = status + 1;
                        imageView.setImageResource(R.drawable.tutorial5);
                        break;
                    case 3:
                        status = status + 1;
                        imageView.setImageResource(R.drawable.tutorial3);
                        break;
                    case 4:
                        status = status + 1;
                        imageView.setImageResource(R.drawable.tutorial6);
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

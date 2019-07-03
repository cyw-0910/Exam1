package com.example.exam1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//第二界面的事件处理程序
public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    String p1_get_name, p1_get_passwd,p2_get_name,p2_get_passwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        final EditText p2_name = findViewById(R.id.page_two_name);
        final EditText p2_passwd = findViewById(R.id.page_two_passwd);
        Button p2_show = findViewById(R.id.page_two_show);
        Button p2_return = findViewById(R.id.page_two_return);
        Intent intent = getIntent();
        p1_get_name = intent.getStringExtra("p1_get_name");
        p1_get_passwd = intent.getStringExtra("p1_get_passwd");
        //Log.d(">>>", p1_get_name);
        p2_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("<<<", p1_get_name);
                p2_name.setText(p1_get_name);
                p2_passwd.setText(p1_get_passwd);
            }
        });
        //使用匿名内部类
        p2_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2_get_name = p2_name.getText().toString();
                p2_get_passwd = p2_passwd.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("p2_get_name", p2_get_name);
                intent.putExtra("p2_get_passwd", p2_get_passwd);
                Log.d("???", p2_get_name);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

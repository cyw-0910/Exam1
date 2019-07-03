package com.example.exam1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    设置全局变量
    String p1_get_name, p1_get_passwd;
    EditText p1_name, p1_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send_data = findViewById(R.id.send_data_sec_act);
        Button get_data = findViewById(R.id.get_sec_act_data);
        Button page_close = findViewById(R.id.page_one_close);
        p1_name = findViewById(R.id.page_one_name);
        p1_passwd = findViewById(R.id.page_one_passwd);
        //使用接口方式实现监听事件
        send_data.setOnClickListener(this);
        get_data.setOnClickListener(this);
        page_close.setOnClickListener(this);

    }
//设置监听，点击事件
    @Override
    public void onClick(View v) {
        //点击传递数据
        if (v.getId() == R.id.send_data_sec_act) {
            //设置输入框为密码模式
            p1_name.setTransformationMethod(PasswordTransformationMethod.getInstance());
            p1_passwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            //获取输入框的值
            p1_get_name = p1_name.getText().toString();
            p1_get_passwd = p1_passwd.getText().toString();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            //使用putExtra方法存储数据
            intent.putExtra("p1_get_name", p1_get_name);
            intent.putExtra("p1_get_passwd", p1_get_passwd);
            startActivity(intent);
            //Log.d(">>>", p1_get_name);
        }
        //点击获取第二个界面的数据
        if (v.getId() == R.id.get_sec_act_data) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivityForResult(intent, 1);
        }
        //关闭页面的操作
        if (v.getId() == R.id.page_one_close) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("警告框");
            dialog.setMessage("是否要销毁该页面？");
            //点击是的操作
            dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            //点击否的操作
            dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialog.show();
        }
    }
//重写onActivityResult方法，实现对数据的返回数据的监听
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //从存储中取出数据
                    p1_get_name = data.getStringExtra("p2_get_name");
                    p1_get_passwd = data.getStringExtra("p2_get_passwd");
                    //设置输入框为明文模式
                    p1_name.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    p1_passwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //设置输入框的内容
                    p1_name.setText(p1_get_name);
                    p1_passwd.setText(p1_get_passwd);
                }
        }
    }
}

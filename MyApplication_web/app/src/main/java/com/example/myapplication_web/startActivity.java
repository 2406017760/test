package com.example.myapplication_web;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class startActivity extends AppCompatActivity {
    EditText ets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ets = (EditText) findViewById(R.id.editText1);
    }

    public void onClick(View view) {
            // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(startActivity.this,MainActivity.class);
        switch (view.getId()) {
            case R.id.button1:
                if (ets.length() == 0) {
                    Toast.makeText(getApplicationContext(), "不可以直接进去哦~", Toast.LENGTH_SHORT).show();
                } else { intent.putExtra("url", ets.getText().toString());
                    startActivity(intent); }
                break;
            case R.id.btn1:
                intent.putExtra("url", "如何让富婆爱上你");
                startActivity(intent);
                break;
            case R.id.btn2:
                intent.putExtra("url", "https://www.bilibili.com");
                startActivity(intent);
                break;
            case R.id.btn3:
                intent.putExtra("url", "http://listen1.github.io/listen1/");
                startActivity(intent);
                break;
            case R.id.btn4:
                intent.putExtra("url", "www.bing.com");
                startActivity(intent);
                break;
            case R.id.btn7:
                intent.putExtra("url", "www.iqiyi.com");
                startActivity(intent);
                break;
            case R.id.btn8:
                intent.putExtra("url", "http://www.lsjgcx.com/");
                startActivity(intent);
                break;
            case R.id.btn9:
                Uri uri8 = Uri.parse("tel:10086");
                intent.putExtra(Intent.ACTION_DIAL,uri8);
                startActivity(intent);
                break;
            case R.id.btn10:
                intent.putExtra("url", "6666");
                startActivity(intent);
                break;



        }
    }

}
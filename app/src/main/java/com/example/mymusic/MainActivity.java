package com.example.mymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button  login, reg;
    Database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login   =(Button)findViewById(R.id.btn_main_login);
        reg   =(Button)findViewById(R.id.btn_main_reg);
        ClickLogin();
        ClickRegister();
        myDb= new Database(this);
    }
    // đăng nhập
    public void  ClickLogin()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
           }
        });
    }
    //đăng ký
    public void  ClickRegister()
    {
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
    }
}

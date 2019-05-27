package com.example.mymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edt_name, edt_pass;
    Button LOG;
    Database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_name=(EditText)findViewById(R.id.edt_login_name);
        edt_pass=(EditText)findViewById(R.id.edt_login_pass);
        LOG=(Button)findViewById(R.id.btn_login);
        myDb=new Database(this);
        ClickLOG();
    }
    //Click đăng nhập
    public void ClickLOG()
    {
        LOG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  nameStr=edt_name.getText().toString();
                String  passStr=edt_pass.getText().toString();
              if (!nameStr.isEmpty()&&!passStr.isEmpty())
              {
                  Boolean LoginSuccess= myDb.findData(nameStr,passStr);
                  if (LoginSuccess)
                  {
                      Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                      Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                      startActivity(intent);
                  }
                  else
                  {
                      Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_LONG).show();
                  }
              }
              else
              {
                  Toast.makeText(LoginActivity.this, "Tên tài khoản và mật khẩu không được để trống!", Toast.LENGTH_LONG).show();
              }
            }
        });
    }
}

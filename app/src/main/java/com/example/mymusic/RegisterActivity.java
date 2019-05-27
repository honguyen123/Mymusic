package com.example.mymusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edtname,edtpass,edtpass2;
    Button REG;
    Database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtname = (EditText)findViewById(R.id.edt_reg_name);
        edtpass = (EditText)findViewById(R.id.edt_reg_pass);
        edtpass2 = (EditText)findViewById(R.id.edt_reg_pass2);
        REG =(Button)findViewById(R.id.btn_reg);
        myDb  =new Database(this);
        ClickREG();
    }
    public void ClickREG()
    {
        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr= edtname.getText().toString();
                String passStr= edtpass.getText().toString();
                String pass2Str= edtpass2.getText().toString();

                    if(passStr.equals(pass2Str))
                    {
                        Boolean REGSuccess = myDb.addData(nameStr,passStr);
                        if (REGSuccess)
                        {
                            Toast.makeText(RegisterActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"Đăng ký không thành công",Toast.LENGTH_LONG).show();
                            edtname.setText("");
                            edtpass.setText("");
                            edtpass2.setText("");
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Mật Khẩu Nhập Lại Không Khớp! ",Toast.LENGTH_LONG).show();
                    }


            }
        });
    }
}

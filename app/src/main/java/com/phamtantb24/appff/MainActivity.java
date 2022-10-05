package com.phamtantb24.appff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signInBtn;
    EditText userNameTxt;
    EditText passwordTxt;
    CheckBox cbRememberAcc;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        //get data sharepreference
        userNameTxt.setText(sharedPreferences.getString("UserName",""));
        passwordTxt.setText(sharedPreferences.getString("Password",""));
        cbRememberAcc.setChecked(sharedPreferences.getBoolean("Checked",false));


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();
                if (userName.equals("Pham Ngoc Tan") && password.equals("1")) {
                    Toast.makeText(MainActivity.this, "Login succeed", Toast.LENGTH_SHORT).show();
                    if(cbRememberAcc.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("UserName",userName);
                        editor.putString("Password",password);
                        editor.putBoolean("Checked",true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("UserName");
                        editor.remove("Password");
                        editor.remove("Checked");
                        editor.commit();

                    }
                }
                else
                    Toast.makeText(MainActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        signInBtn = findViewById(R.id.login);
        userNameTxt = findViewById(R.id.userName);
        passwordTxt = findViewById(R.id.password);
        cbRememberAcc = findViewById(R.id.cbRememberAcc);

    }
}
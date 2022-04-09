package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity {

    EditText etxtLogin, etxtPassword;
    Button btnLogin, btnReg;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        dataBaseHelper = new DataBaseHelper(this);
        etxtLogin = findViewById(R.id.etxtLogin);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);

        btnLogin.setOnClickListener(view -> {
            Cursor user = dataBaseHelper.getUserByLoginAndPassword(etxtLogin.getText().toString(), etxtPassword.getText().toString());
            if(user.getCount() == 1) {
                user.moveToFirst();
                CurrentUser.authorizeUser(user.getInt(0), user.getString(1), user.getString(2), user.getString(3), user.getString(4));
                Intent intent = new Intent();
                if(CurrentUser.getRole().equals("Админ")){
                    intent = new Intent(EnterActivity.this, AdminPanelActivity.class);
                }
                else {
                    intent = new Intent(EnterActivity.this, ReaderActivity.class);
                }
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Неправильный логин или пароль",Toast.LENGTH_LONG).show();
            }
        });

        btnReg.setOnClickListener(view -> {
            Intent intent = new Intent(EnterActivity.this, RegActivity.class);
            startActivity(intent);
        });
    }
}
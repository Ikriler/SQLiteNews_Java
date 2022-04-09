package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    EditText etxtLogin, etxtPassword, etxtName;
    Button btnReg;
    DataBaseHelper dataBaseHelper;
    Spinner spnRoles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        dataBaseHelper = new DataBaseHelper(this);
        etxtName = findViewById(R.id.etxtName);
        etxtLogin = findViewById(R.id.etxtLogin);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnReg = findViewById(R.id.btnReg);
        spnRoles = findViewById(R.id.spnRoles);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.roles));
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnRoles.setAdapter(adapter);

        btnReg.setOnClickListener(view -> {
            if(!checkData()) {
                Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                return;
            };
            if(dataBaseHelper.getUserByLogin(etxtLogin.getText().toString()).getCount() != 0)
            {
                Toast.makeText(this, "Пользователь с таким логином уже существует", Toast.LENGTH_SHORT).show();
                return;
            }
            Boolean regResult = dataBaseHelper.insertUser(etxtName.getText().toString(), etxtName.getText().toString(), etxtName.getText().toString(), spnRoles.getSelectedItem().toString());

            if(regResult) {
                Intent intent = new Intent(RegActivity.this, EnterActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public Boolean checkData()
    {
        return etxtName.getText().toString() != "" && etxtPassword.getText().toString() != "" && etxtLogin.getText().toString() != "";
    }
}
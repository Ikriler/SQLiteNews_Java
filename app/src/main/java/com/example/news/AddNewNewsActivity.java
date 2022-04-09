package com.example.news;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDateTime;

public class AddNewNewsActivity extends AppCompatActivity {

    EditText etxtHeader, etxtContent;
    Button btnAdd;
    DataBaseHelper dataBaseHelper;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_news);

        etxtHeader = findViewById(R.id.etxtHeader);
        etxtContent = findViewById(R.id.etxtContent);
        btnAdd = findViewById(R.id.btnAdd);
        dataBaseHelper = new DataBaseHelper(this);

        btnAdd.setOnClickListener(view -> {
            dataBaseHelper.insertNews(etxtHeader.getText().toString(), etxtContent.getText().toString(), LocalDateTime.now().toString());
            Intent intent = new Intent(AddNewNewsActivity.this, AdminPanelActivity.class);
            startActivity(intent);
        });
    }
}
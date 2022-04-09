package com.example.news;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;

public class EditNewsActivity extends AppCompatActivity {

    private int IdNews;
    TextView etxtHeader, etxtContent;
    Button btnSave, btnDelete;
    DataBaseHelper dataBaseHelper;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);
        Bundle bundle = getIntent().getExtras();
        IdNews = bundle.getInt("IdNews");
        dataBaseHelper = new DataBaseHelper(this);
        etxtHeader = findViewById(R.id.etxtHeader);
        etxtContent = findViewById(R.id.etxtContent);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        setNews();


        btnSave.setOnClickListener(view -> {
            dataBaseHelper.updateNews(IdNews, etxtHeader.getText().toString(), etxtContent.getText().toString(), LocalDateTime.now().toString());
            Intent intent = new Intent(EditNewsActivity.this, AdminPanelActivity.class);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(view -> {
            dataBaseHelper.deleteNews(IdNews);
            Intent intent = new Intent(EditNewsActivity.this, AdminPanelActivity.class);
            startActivity(intent);
        });
    }

    private void setNews() {
        Cursor news = dataBaseHelper.getNewsById(IdNews);
        news.moveToFirst();
        etxtHeader.setText(news.getString(1));
        etxtContent.setText(news.getString(2));
    }
}
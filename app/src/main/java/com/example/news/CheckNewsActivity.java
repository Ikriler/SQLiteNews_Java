package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class CheckNewsActivity extends AppCompatActivity {
    private int IdNews;
    TextView txtHeader, txtContent;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_news);
        Bundle bundle = getIntent().getExtras();
        IdNews = bundle.getInt("IdNews");
        dataBaseHelper = new DataBaseHelper(this);
        txtHeader = findViewById(R.id.txtHeader);
        txtContent = findViewById(R.id.txtContent);
        setNews();
    }

    private void setNews() {
        Cursor news = dataBaseHelper.getNewsById(IdNews);
        news.moveToFirst();
        txtHeader.setText(news.getString(1));
        txtContent.setText(news.getString(2));
    }
}
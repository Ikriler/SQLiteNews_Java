package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ReaderActivity extends AppCompatActivity {

    RecyclerView recyclerNews;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        recyclerNews = findViewById(R.id.recNews);
        dataBaseHelper = new DataBaseHelper(this);

        InitRecyclerNews();
    }

    private void InitRecyclerNews() {
        List<News> newsList = new ArrayList<News>();
        Cursor newsCursor = dataBaseHelper.getAllNews();
        if(newsCursor.getCount() == 0) return;
        newsCursor.moveToFirst();
        for(int i = 0; i < newsCursor.getCount(); i++)
        {
            News news = new News(newsCursor.getInt(0), newsCursor.getString(1), newsCursor.getString(2), newsCursor.getString(3));
            newsList.add(news);
            newsCursor.moveToNext();
        }
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        NewsAdapter adapter = new NewsAdapter(newsList, this);
        recyclerNews.setLayoutManager(llm);
        recyclerNews.setAdapter(adapter);
    }
}
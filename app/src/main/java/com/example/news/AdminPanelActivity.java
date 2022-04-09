package com.example.news;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.databinding.ActivityAdminPanelBinding;

import java.util.ArrayList;
import java.util.List;

public class AdminPanelActivity extends AppCompatActivity {
    RecyclerView recyclerNews;
    Button btnGoAddNews;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        recyclerNews = findViewById(R.id.recNews);
        btnGoAddNews = findViewById(R.id.btnGoAddNews);
        dataBaseHelper = new DataBaseHelper(this);

        InitRecyclerNews();

        btnGoAddNews.setOnClickListener(view -> {
            Intent intent = new Intent(AdminPanelActivity.this, AddNewNewsActivity.class);
            startActivity(intent);
        });
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
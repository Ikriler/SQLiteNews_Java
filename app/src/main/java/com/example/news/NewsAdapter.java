package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private LayoutInflater inflater;
    private List<News> news;


    public NewsAdapter(List<News> news, Context context) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_recycleview_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News oneNews = news.get(position);
        holder.txtHeader.setText(oneNews.getHeader());
        holder.itemView.setTag(oneNews.getId());
        holder.txtPostDateTime.setText(oneNews.getPostDate());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader, txtPostDateTime;

        public NewsViewHolder(View itemView) {
            super(itemView);
            txtHeader = itemView.findViewById(R.id.txtHeader);
            txtPostDateTime = itemView.findViewById(R.id.txtPostDateTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent();
                    if(CurrentUser.getRole().equals("Админ")) {
                        intent = new Intent(context, EditNewsActivity.class);
                    }
                    else {
                        intent = new Intent(context, CheckNewsActivity.class);
                    }
                    intent.putExtra("IdNews", (int) itemView.getTag());
                    context.startActivity(intent);
                }
            });
        }
    }
}

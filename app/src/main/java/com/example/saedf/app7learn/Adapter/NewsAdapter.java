package com.example.saedf.app7learn.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.dataModel.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList;
    private Context context;

    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.ivImageNews.setImageDrawable(news.getImagenews());
        holder.tvTitleNews.setText(news.getTitle());
        holder.tvContentNews.setText(news.getContent());
        holder.tvDateNews.setText(news.getDate());

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImageNews;
        private TextView tvTitleNews;
        private TextView tvContentNews;
        private TextView tvDateNews;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ivImageNews = itemView.findViewById(R.id.iv_newsitem_imagenews);
            tvTitleNews = itemView.findViewById(R.id.tv_newsitem_titlenews);
            tvContentNews = itemView.findViewById(R.id.tv_nesitem_contentnews);
            tvDateNews = itemView.findViewById(R.id.tv_nesitem_datenews);
        }
    }
}

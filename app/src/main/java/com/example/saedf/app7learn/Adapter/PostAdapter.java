package com.example.saedf.app7learn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.SqlLiteOpenHelper.PostDataBaseOpenHelper;
import com.example.saedf.app7learn.ViewPostActivity;
import com.example.saedf.app7learn.dataModel.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> postList;
    private static final String IP_ADDRESS_SERVER = "172.20.200.45";

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, final int position) {
        final Post post = postList.get(position);
        holder.tvTitlePost.setText(post.getTitle());
        holder.tvContentPost.setText(post.getContent());
        holder.tvDatePost.setText(post.getDate());
        Picasso.with(context).load(post.getImagenewsUrl().replace("localhost", IP_ADDRESS_SERVER))
                .into(holder.ivImagePost);
        if (post.getIsVisited()==1){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.is_visited));
        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.is_not_visited));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewPostActivity.class);
                intent.putExtra(PostDataBaseOpenHelper.COL_ID,post.getId());
                intent.putExtra(PostDataBaseOpenHelper.COL_TITLE,post.getTitle());
                intent.putExtra(PostDataBaseOpenHelper.COL_CONTENT,post.getContent());
                intent.putExtra(PostDataBaseOpenHelper.COL_DATE,post.getDate());
                intent.putExtra(PostDataBaseOpenHelper.COL_POST_IMAGE_URL,post.getImagenewsUrl());
                intent.putExtra(PostDataBaseOpenHelper.COL_IS_VISITED,post.getIsVisited());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImagePost;
        private TextView tvTitlePost;
        private TextView tvContentPost;
        private TextView tvDatePost;

        public PostViewHolder(View itemView) {
            super(itemView);
            ivImagePost = itemView.findViewById(R.id.iv_itempost_imagepost);
            tvTitlePost = itemView.findViewById(R.id.tv_itempost_titlepost);
            tvContentPost = itemView.findViewById(R.id.tv_itempost_contentpost);
            tvDatePost = itemView.findViewById(R.id.tv_itempost_datepost);
        }
    }
}

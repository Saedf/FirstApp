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
import com.example.saedf.app7learn.dataModel.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post=postList.get(position);
        holder.tvTitlePost.setText(post.getTitle());
        holder.tvContentPost.setText(post.getContent());
        holder.tvDatePost.setText(post.getDate());
        Picasso.with(context).load(post.getImagenewsUrl().replace("localhost","172.20.200.45"))
                .into(holder.ivImagePost);


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImagePost;
        private TextView tvTitlePost;
        private TextView tvContentPost;
        private TextView tvDatePost;
        public PostViewHolder(View itemView) {
            super(itemView);
            ivImagePost=itemView.findViewById(R.id.iv_itempost_imagepost);
            tvTitlePost=itemView.findViewById(R.id.tv_itempost_titlepost);
            tvContentPost=itemView.findViewById(R.id.tv_itempost_contentpost);
            tvDatePost=itemView.findViewById(R.id.tv_itempost_datepost);
        }
    }
}

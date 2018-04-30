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
import com.example.saedf.app7learn.dataModel.Clothe;

import java.util.List;

public class ClotheAdapter extends RecyclerView.Adapter<ClotheAdapter.ClotheViewHolder> {
    private List<Clothe> clotheList;
    private Context context;

    public ClotheAdapter(List<Clothe> clotheList, Context context) {
        this.clotheList = clotheList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClotheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_cothe,parent,false);
        return new ClotheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClotheViewHolder holder, int position) {
        Clothe clothe=clotheList.get(position);
        holder.ivImageClothe.setImageDrawable(clothe.getImageClothe());
        holder.tvTitleClothe.setText(clothe.getTitleClothe());
        holder.tvViewCountClothe.setText(String.valueOf(clothe.getViewCountclothe()));

    }

    @Override
    public int getItemCount() {
        return clotheList.size();
    }

    public class ClotheViewHolder extends RecyclerView.ViewHolder{
private ImageView ivImageClothe;
private TextView tvTitleClothe;
private TextView tvViewCountClothe;
        public ClotheViewHolder(View itemView) {
            super(itemView);
            ivImageClothe=itemView.findViewById(R.id.iv_layoutclothe_imageClothe);
            tvTitleClothe=itemView.findViewById(R.id.tv_layoutClothe_titleClothe);
            tvViewCountClothe=itemView.findViewById(R.id.tv_layoutClothe_viewCountClothe);
        }
    }
}

package com.example.saedf.app7learn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.dataModel.AppFeature;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AppFeatureAdapter extends RecyclerView.Adapter<AppFeatureAdapter.appFeatureViewHolder> {
    private Context context;
    private List<AppFeature> appFeatureList = new ArrayList<>();

    public AppFeatureAdapter(Context context) {
        this.context = context;
    }

    public void setAppFeature(List<AppFeature> appFeatureList) {
        this.appFeatureList = appFeatureList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public appFeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_app_feature, parent, false);
        return new appFeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull appFeatureViewHolder holder, int position) {
        holder.bindFeatures(appFeatureList.get(position));
    }

    @Override
    public int getItemCount() {
        return appFeatureList.size();
    }


    public class appFeatureViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView chapterTitle;

        public appFeatureViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.feature_image_view);
            chapterTitle = itemView.findViewById(R.id.feature_title);
        }

        public void bindFeatures(final AppFeature appFeature) {
            Picasso.with(itemView.getContext()).load(appFeature.getFeatureImage()).into(imageView);
            chapterTitle.setText(appFeature.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), appFeature.getDestinationActivity()));
                }
            });
        }
    }
}

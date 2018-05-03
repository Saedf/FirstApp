package com.example.saedf.app7learn.AsycnchronisProcessing;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;


import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadImagePostTask extends AsyncTask<Void,Integer,Void> {
    private List<String> urls=new ArrayList<>();
    private Context context;
    private ProgressDialog progressDialog;

    public DownloadImagePostTask(List<String> urls, Context context) {
        this.urls = urls;
        this.context = context;
    }
    public DownloadImagePostTask(String url, Context context) {
        urls.add(url);
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < urls.size(); i++) {
            try {
                Bitmap bitmap= Picasso.with(context).load(urls.get(i)).get();
                String url=urls.get(i);
                File extImageDir=context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                String imageName=url.substring(url.lastIndexOf("/")+1,url.length());
                File image=new File(extImageDir,imageName);
                FileOutputStream fso=new FileOutputStream(image);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fso);
                fso.flush();
                fso.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress((i*100)/urls.size());

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
       // progressDialog.setTitle("دخیره عکس");
        progressDialog.setMessage("در حال دخیره سازی عکس ها");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }
}

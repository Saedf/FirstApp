package com.example.saedf.app7learn.AsycnchronisProcessing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.saedf.app7learn.SqlLiteOpenHelper.PostDataBaseOpenHelper;
import com.example.saedf.app7learn.dataModel.Post;

import java.util.List;

public class AddPostTask extends AsyncTask<Void, Integer, String> {
    private static final String TAG = "AddPostTask";
    private Context context;
    private List<Post> postList;
    private PostDataBaseOpenHelper postDataBaseOpenHelper;
    private ProgressBar progressBar;

    public AddPostTask(Context context, List<Post> postList, PostDataBaseOpenHelper postDataBaseOpenHelper) {
        this.context = context;
        this.postList = postList;
        this.postDataBaseOpenHelper = postDataBaseOpenHelper;
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (int i = 0; i < postList.size(); i++) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ContentValues contentValues=new ContentValues();
            Post post=postList.get(i);
            contentValues.put(PostDataBaseOpenHelper.COL_TITLE,post.getTitle());
            contentValues.put(PostDataBaseOpenHelper.COL_CONTENT,post.getContent());
            contentValues.put(PostDataBaseOpenHelper.COL_POST_IMAGE_URL,post.getImagenewsUrl());
            contentValues.put(PostDataBaseOpenHelper.COL_DATE,post.getDate());
            contentValues.put(PostDataBaseOpenHelper.COL_IS_VISITED,post.getIsVisited());
            SQLiteDatabase sqLiteDatabase=postDataBaseOpenHelper.getWritableDatabase();
           long isInserted= sqLiteDatabase.insert(PostDataBaseOpenHelper.POST_TABLE_NAME,null,contentValues);
           publishProgress((i*100)/postList.size());
            Log.i(TAG, "AddPostTask: "+isInserted);

        }
        return "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar=new ProgressBar(context);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }
}

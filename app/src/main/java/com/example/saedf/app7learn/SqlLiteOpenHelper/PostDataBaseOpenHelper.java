package com.example.saedf.app7learn.SqlLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.saedf.app7learn.dataModel.Post;

import java.util.ArrayList;
import java.util.List;

public class PostDataBaseOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "PostDataBaseOpenHelper";
    private Context context;
    private static final String DATABASE_NAME = "db_post";
    private static final int DATABASE_VERSION = 1;
    public static final String POST_TABLE_NAME = "tbl_postNews";
    public static final String COL_ID = "col_id";
    public static final String COL_TITLE = "col_title";
    public static final String COL_CONTENT = "col_content";
    public static final String COL_POST_IMAGE_URL = "col_post_image_url";
    public static final String COL_DATE = "col_date";
    public static final String COL_IS_VISITED = "col_is_visited";
    private static final String SQL_COMMAND_POST_TABLE = "CREATE TABLE IF NOT EXISTS " + POST_TABLE_NAME
            + "( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITLE + " TEXT,"
            + COL_CONTENT + " TEXT," + COL_POST_IMAGE_URL + " TEXT,"
            + COL_DATE + " TEXT," + COL_IS_VISITED + " INTEGER DEFAULT 0);";

    public PostDataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_COMMAND_POST_TABLE);

        } catch (SQLException e){
            Log.e(TAG, "onCreate: "+e.toString() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Boolean addPostToDB(Post post){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_TITLE,post.getTitle());
        contentValues.put(COL_CONTENT,post.getContent());
        contentValues.put(COL_POST_IMAGE_URL,post.getImagenewsUrl());
        contentValues.put(COL_DATE,post.getDate());
        contentValues.put(COL_IS_VISITED,post.getIsVisited());

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       long  isInserted=sqLiteDatabase.insert(POST_TABLE_NAME,null,contentValues);
        Log.i(TAG, "addPostToDB: "+isInserted);
        if (isInserted>0){
            return true;
        }else {
            return false;
        }

    }
    public void addListPostToDB(List<Post> postList){
        for (int i = 0; i < postList.size(); i++) {
            if (!checkPostExists(postList.get(i).getId())){
                addPostToDB(postList.get(i));
            }
        }
    }
    private boolean checkPostExists(int postId){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+POST_TABLE_NAME +
        " WHERE "+ COL_ID +" =?" ,new String[]{String.valueOf(postId)});
        return cursor.moveToFirst();

    }
    public List<Post> getPostList(){
        List<Post> postList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase() ;
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+
         POST_TABLE_NAME ,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            while (!cursor.isAfterLast()){
                Post post=new Post();
                post.setId(cursor.getInt(0));
                post.setTitle(cursor.getString(1));
                post.setContent(cursor.getString(2));
                post.setImagenewsUrl(cursor.getString(3));
                post.setDate(cursor.getString(4));
                post.setIsVisited(cursor.getInt(5));

                postList.add(post);
                cursor.moveToNext();

            }
        }
        cursor.close();;
        sqLiteDatabase.close();
        return postList;
    }
    public void setPostIsVisited(int PostId,int isVisited){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_IS_VISITED,isVisited);
        sqLiteDatabase.update(POST_TABLE_NAME,contentValues,COL_ID + " = ?",
                new String[]{String.valueOf(PostId)});
        sqLiteDatabase.close();

    }
}

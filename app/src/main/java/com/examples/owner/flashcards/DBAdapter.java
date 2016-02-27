package com.examples.owner.flashcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {


static final String KEY_ROWID="_id";
static final String KEY_EMAIL="email";
static final String KEY_PASSWORD="password";
    static final String TAG="DBAdapter";

    static final String DATABASE_NAME="myFlashCardsDB";
    static final String DATABASE_TABLE="flashCards";
    static final int DATABASE_VERSION=1;

    static final String DATABASE_CREATE= "create table flashcards (_id integer primary key autoincrement,"
            +"email text not null, password text not null);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx){

        this.context=ctx;
        DBHelper= new DatabaseHelper(context);

    }

            private static class DatabaseHelper extends SQLiteOpenHelper{

                DatabaseHelper(Context context){

                    super(context, DATABASE_NAME,null,DATABASE_VERSION);

                }
                public void onCreate(SQLiteDatabase db){
                    try{
                        db.execSQL(DATABASE_CREATE);
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
                    Log.w(TAG,"Upgrading database from version "+ oldVersion+"to"
                            + newVersion+ ", which will destroy all old data");
                    db.execSQL("DROP TABLE IF EXISTS flashCards");
                    onCreate(db);

                }
            }


    public DBAdapter open()throws SQLException{

        db= DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){

        DBHelper.close();
    }
       public long insertLogin(String email, String password){
           ContentValues initialValues =new ContentValues();
           initialValues.put(KEY_EMAIL,email);
           initialValues.put(KEY_PASSWORD,password);
           return db.insert(DATABASE_TABLE,null,initialValues);
       }

    public boolean deleteLogin(long rowId){
        return db.delete(DATABASE_TABLE,KEY_ROWID + "="+rowId,null)>0;

    }
    public Cursor getAllLogins(){
        return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_EMAIL,KEY_PASSWORD},null,null,null,null,null);


    }
    public Cursor getLogin(long rowId)throws SQLException{
        Cursor mCursor=db.query(true,DATABASE_TABLE,new String[]{KEY_ROWID,KEY_EMAIL,KEY_PASSWORD},KEY_ROWID+"="+
        rowId,null,null,null,null,null);
        if(mCursor!=null){

            mCursor.moveToFirst();

        }
        return mCursor;

    }
    public boolean updateLogin(long rowId,String email,String password){

        ContentValues args= new ContentValues();
        args.put(KEY_EMAIL,email);
        args.put(KEY_PASSWORD,password);
        return db.update(DATABASE_TABLE,args,KEY_ROWID+"="+rowId,null)>0;
    }
}

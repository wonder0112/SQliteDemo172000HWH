package com.example.stu.sqlitedemo172000hwh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StuSQLiteAdapter {
    SQLiteDatabase db;
    Context context;
    String TABLENAME="student";

    public StuSQLiteAdapter(Context context) {
        this.context = context;
    }

    public void opendDB(){
        StuOpenHelper openHelper=new StuOpenHelper(context,"StuDB.db",null,1);
        db=openHelper.getWritableDatabase();
    }
    public void closeDB(){
        if(db.isOpen()){
            db.close();
        }
        db=null;
    }
    public  long insert(Student stu){
        long rowId=-1;
        opendDB();
        //判断记录是否存在--查询
        Cursor cursor=db.query(TABLENAME,null,"name=? and course=?",new String[]{stu.getName(),stu.getCourse()},null,null,null);
        if(cursor.getCount()>0){
            rowId=0;//标识数据记录已存在
        }else if(cursor.getCount()==0){
            ContentValues values=new ContentValues();
            values.put("name",stu.getName());
            values.put("course",stu.getCourse());
            values.put("score",stu.getScore());
            rowId=db.insert(TABLENAME,null,values);
        }
        closeDB();
        return rowId;
    }
    public void update(){

    }
    public void query(){

    }
    public void delete(){

    }
}

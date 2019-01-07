package com.example.stu.sqlitedemo172000hwh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    //查询功能，，输出结果：？List<Student>输入参数：？？,无参数：全部记录，仅有姓名：该姓名下的记录，既有姓名也有科目：单条记录
    public List<Student> queryAll(){
        List<Student> list=new ArrayList<>();
        opendDB();
        //查询所有数据
        Cursor cursor=db.query(TABLENAME,null,null,null,null,null,null,null);
        //游标Cursor-->List
        if(cursor.getCount()>0){
            cursor.moveToFirst();//把指针指向第一条记录
            do{
                int _id=cursor.getInt(0);
                String name=cursor.getString(1);
                String course=cursor.getString(2);
                Float score=cursor.getFloat(3);

                Student stu=new Student();
                stu.set_id(_id);
                stu.setName(name);
                stu.setCourse(course);
                stu.setScore(score);
                list.add(stu);
            }while (cursor.moveToNext());
        }
        closeDB();
        return  list;
    }
    public List<Student> querybyName(String name){
        List<Student> list=new ArrayList<>();
        opendDB();
        //查询所有数据
        Cursor cursor=db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);
        //游标Cursor-->List
        if(cursor.getCount()>0){
            cursor.moveToFirst();//把指针指向第一条记录
            do{
                int _id=cursor.getInt(0);
                String name1=cursor.getString(1);
                String course=cursor.getString(2);
                Float score=cursor.getFloat(3);

                Student stu=new Student();
                stu.set_id(_id);
                stu.setName(name1);
                stu.setCourse(course);
                stu.setScore(score);
                list.add(stu);
            }while (cursor.moveToNext());
        }
        closeDB();

        return  list;
    }

    public List<Student> querybyNameAndCourse(String name,String course){
        List<Student> list=new ArrayList<>();
        opendDB();
        //查询所有数据
        Cursor cursor=db.query(TABLENAME,null,"name=? and course=?",new String[]{name,course},null,null,null,null);
        //游标Cursor-->List
        if(cursor.getCount()>0){
            cursor.moveToFirst();//把指针指向第一条记录
            do{
                int _id=cursor.getInt(0);
                String name1=cursor.getString(1);
                String course1=cursor.getString(2);
                Float score=cursor.getFloat(3);

                Student stu=new Student();
                stu.set_id(_id);
                stu.setName(name1);
                stu.setCourse(course1);
                stu.setScore(score);
                list.add(stu);
            }while (cursor.moveToNext());
        }
        closeDB();




        return  list;
    }



    public void delete(){

    }
}

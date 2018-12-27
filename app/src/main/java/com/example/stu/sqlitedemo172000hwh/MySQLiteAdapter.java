package com.example.stu.sqlitedemo172000hwh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteAdapter {
    //数据库连接和关闭
    SQLiteDatabase db=null;
    Context context=null;
    //数据库连接方法：功能，输入参数，输出内容
    public MySQLiteAdapter(Context context) {
        this.context = context;
    }
    public void openDB(){
        MyHelper myHelper=new MyHelper(context,"database.db",null,1);
        db=myHelper.getWritableDatabase();
    }
    public void closeDB(){
        if(db.isOpen()){
            db.close();
        }
        db=null;
    }
    //数据表操作
    //增加insert() 功能：完成数据记录添加到information表，输入：数据 Infor（name,phone），输出：boolean 添加成功与否的标志值
    public  boolean insert(Infor infor){
        boolean result=false;
        openDB();
        ContentValues values=new ContentValues();
        values.put("name",infor.getName());
        values.put("phone",infor.getPhone());
        Long rowid=db.insert("information",null,values);
        if(rowid!=-1){
            result=true;
            Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
            Log.i("数据库操作","添加成功");
        }else {
            Toast.makeText(context,"添加失败",Toast.LENGTH_SHORT).show();
            Log.i("数据库操作","添加失败");
        }
        closeDB();
        return result;
    }


    //修改
    public int update(Infor infor){
        int result=-1;
        openDB();
        ContentValues values=new ContentValues();
        values.put("phone",infor.getPhone());
        result= db.update("information",values,"name=?",new String[]{infor.getName()});
        closeDB();
        return result;
    }


    //查询
    public List<Infor> queryAll(){
        List<Infor> list=new ArrayList<Infor>();
        openDB();
        //queryAll: select * from information
        Cursor cursor=db.query("information",null,null,null,null,null,null,null);
        //Cursor->List<Infor>
        if(cursor.moveToFirst()){//判断有第一条
            do{
                int id= cursor.getInt(cursor.getColumnIndex("_id"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                Long phone=cursor.getLong(cursor.getColumnIndex("phone"));
                Infor infor=new Infor();
                infor.set_id(id);
                infor.setName(name);
                infor.setPhone(phone);
                list.add(infor);
            }while (cursor.moveToNext());//判断有下一条
        }
        closeDB();
        return list;
    }



    //删除 功能：根据姓名删除记录 输入：姓名，输出：删除条数
    public int deleteByName(String name){
        int result=-1;
        openDB();
        result=db.delete("information","name=?",new String[]{name});//delete from information where name="lisi"
        closeDB();
        return result;
    }


    //数据处理


}

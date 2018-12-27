package com.example.stu.sqlitedemo172000hwh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    //构造方法 使用new MyHelper()时调用的
    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //初始化方法，程序安装后第一次运行，实际就是数据库创建时调用，如果数据库已经存在，该方法不再被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table information(_id integer primary key autoincrement,name varchar(20),phone integer)";
        db.execSQL(sql);

    }
    //数据库版本更新时调用，就是newVersion>构造方法中的version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

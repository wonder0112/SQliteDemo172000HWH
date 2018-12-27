package com.example.stu.sqlitedemo172000hwh;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtTxtName,edtTxtPhone;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtName=findViewById(R.id.edtTxt_main_name);
        edtTxtPhone=findViewById(R.id.edtTxt_main_phone);
        tvDisplay=findViewById(R.id.tv_main_display);
        findViewById(R.id.btn_main_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮，调用适配器的insert();  对象.方法();
                String name=edtTxtName.getText().toString().trim();
                Long phone=Long.valueOf(edtTxtPhone.getText().toString().trim());
                Infor infor=new Infor();
                infor.setName(name);
                infor.setPhone(phone);
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext());
                adapter.insert(infor);
            }
        });
        findViewById(R.id.btn_main_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext());
                List<Infor> list=adapter.queryAll();
                //list
                Iterator<Infor> iterator=list.iterator();
                tvDisplay.setText(null);
                while (iterator.hasNext()){
                    Infor infor=iterator.next();
                    tvDisplay.append("ID:"+String.valueOf(infor.get_id()));
                    tvDisplay.append("name:"+infor.getName());
                    tvDisplay.append("phone:"+String.valueOf(infor.getPhone())+"\n");
                }
            }
        });
        findViewById(R.id.btn_main_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据姓名，删除数据库表里的记录，1,2,3，。。。
                String name=edtTxtName.getText().toString().trim();
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext());
                int num=0;
                num=adapter.deleteByName(name);
                tvDisplay.setText("删除记录条数："+String.valueOf(num));
               // Toast.makeText(getApplicationContext(),"删除记录条数："+String.valueOf(num),Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_main_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infor infor=new Infor();
                String name=edtTxtName.getText().toString().trim();
                infor.setName(name);
                Long phone=Long.valueOf(edtTxtPhone.getText().toString().trim());
                infor.setPhone(phone);
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext());
                int num=adapter.update(infor);
                tvDisplay.setText("更新记录条数："+String.valueOf(num));
            }
        });

    }
}

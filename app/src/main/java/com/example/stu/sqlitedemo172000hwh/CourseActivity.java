package com.example.stu.sqlitedemo172000hwh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    //全局变量
    EditText edtTxtName,edtTxtCoure,edtTxtScore;
    Button btnInsert,btnUpdate,btnQuery,btnDelete;
    TextView tvDisplay;
    StuSQLiteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        //初始化控件变量
        initUI();
        //初始化SQLite适配器
        adapter =new StuSQLiteAdapter(getApplicationContext());

        //设置监听器
        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        //逻辑设计


    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_course_insert:
                Student stu=new Student();
                stu.setName(edtTxtName.getText().toString().trim());
                stu.setCourse(edtTxtCoure.getText().toString().trim());
                stu.setScore(Float.valueOf(edtTxtScore.getText().toString().trim()));
                Long rowId=adapter.insert(stu);
                if(rowId==0){
                    Toast.makeText(getApplicationContext(),"数据已存在",Toast.LENGTH_SHORT).show();
                }else if(rowId>=0){
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_course_query:
                //获取参数
                String name=edtTxtName.getText().toString().trim();
                String course=edtTxtCoure.getText().toString().trim();
                List<Student> list=new ArrayList<>();

                //判断参数
                if(name.equals("")){
                    if (course.equals("")){
                        //查询所有数据
                        list=adapter.queryAll();
                    }else {
                        //查询科目的数据，未考虑
                    }
                }else {
                    if (course.equals("")){
                        //查询个人所有数据
                        list=adapter.querybyName(name);
                    }else {
                        //查询具体个人科目数据
                        list=adapter.querybyNameAndCourse(name,course);
                    }
                }
                if(list.size()>0){
                    Toast.makeText(getApplicationContext(),String.valueOf(list.size()),Toast.LENGTH_SHORT).show();
                }else if(list.size()==0){
                    Toast.makeText(getApplicationContext(),"无数据",Toast.LENGTH_SHORT).show();
                }



                break;
            case  R.id.btn_course_update:

                break;
            case R.id.btn_course_delete:

                break;
        }

    }
    protected  void initUI(){
        edtTxtName=findViewById(R.id.edtTxt_course_name);
        edtTxtCoure=findViewById(R.id.edtTxt_course_course);
        edtTxtScore=findViewById(R.id.edtTxt_course_score);
        btnInsert=findViewById(R.id.btn_course_insert);
        btnQuery=findViewById(R.id.btn_course_query);
        btnUpdate=findViewById(R.id.btn_course_update);
        btnDelete=findViewById(R.id.btn_course_delete);
        tvDisplay=findViewById(R.id.tv_course_display);
    }
}

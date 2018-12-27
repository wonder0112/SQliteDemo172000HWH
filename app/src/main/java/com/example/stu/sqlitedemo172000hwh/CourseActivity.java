package com.example.stu.sqlitedemo172000hwh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    //全局变量
    EditText edtTxtName,edtTxtCoure,edtTxtScore;
    Button btnInsert,btnUpdate,btnQuery,btnDelete;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        //初始化控件变量
        initUI();

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
                StuSQLiteAdapter adapter=new StuSQLiteAdapter(getApplicationContext());
                Long rowId=adapter.insert(stu);
                if(rowId==0){
                    tvDisplay.setText("数据已存在");
                }else if(rowId>=0){
                    tvDisplay.setText("添加成功");
                }else {
                    tvDisplay.setText("添加失败");
                }
                break;
            case R.id.btn_course_query:

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

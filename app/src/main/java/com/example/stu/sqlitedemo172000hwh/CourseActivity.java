package com.example.stu.sqlitedemo172000hwh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    //全局变量
    EditText edtTxtName,edtTxtCoure,edtTxtScore;
    Button btnInsert,btnUpdate,btnQuery,btnDelete;
    ListView lvDisplay;
    StuSQLiteAdapter adapter;
    List<Student> list;
    Student stu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        //初始化控件变量
        initUI();
        //初始化SQLite适配器
        adapter =new StuSQLiteAdapter(getApplicationContext());
        list=new ArrayList<>();
        stu=new Student();

        //设置监听器
        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        //逻辑设计
        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student stu=list.get(position);
                Toast.makeText(getApplicationContext(),stu.getName(),Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onClick(View v) {
        stu.setName(edtTxtName.getText().toString().trim());
        stu.setCourse(edtTxtCoure.getText().toString().trim());
        String scoreStr=edtTxtScore.getText().toString().trim();
        float score=0.0f;
        try{
            score=Float.valueOf(scoreStr);
        }catch (Exception e){
            score=0.0f;
        }finally {
            stu.setScore(score);
        }

        switch (v.getId()){
            case R.id.btn_course_insert:
                Long rowId=adapter.insert(stu);
                if(rowId==0){
                    Toast.makeText(getApplicationContext(),"数据已存在",Toast.LENGTH_SHORT).show();
                }else if(rowId>=0){
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                }
                edtTxtName.setText(null);
                edtTxtCoure.setText(null);
                edtTxtScore.setText(null);
                break;
            case R.id.btn_course_query:
                //获取参数
                String name=edtTxtName.getText().toString().trim();
                String course=edtTxtCoure.getText().toString().trim();
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
                    //list数据->ListView控件中
                    MyAdapter adapter=new MyAdapter();
                    lvDisplay.setAdapter(adapter);

                   Toast.makeText(getApplicationContext(),String.valueOf(list.size()),Toast.LENGTH_SHORT).show();
                }else if(list.size()==0){
                    Toast.makeText(getApplicationContext(),"无数据",Toast.LENGTH_SHORT).show();
                }
                edtTxtName.setText(null);
                edtTxtCoure.setText(null);
                edtTxtScore.setText(null);
                break;
            case  R.id.btn_course_update:
                int num=adapter.update(stu);
                if(num>0){
                    Toast.makeText(getApplicationContext(),"更新成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"更新失败",Toast.LENGTH_SHORT).show();
                }
                edtTxtName.setText(null);
                edtTxtCoure.setText(null);
                edtTxtScore.setText(null);
                break;
            case R.id.btn_course_delete:
                if(adapter.delete(stu.getName(),stu.getCourse())>0){
                    Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"删除失败",Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
    //list数据显示到ListView适配器
    public class  MyAdapter extends  BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=View.inflate(getApplicationContext(),R.layout.course_item,null);
            TextView tvID=view.findViewById(R.id.tv_course_item_id);
            TextView tvName=view.findViewById(R.id.tv_course_item_name);
            TextView tvCourse=view.findViewById(R.id.tv_course_item_course);
            TextView tvScore=view.findViewById(R.id.tv_course_item_score);

            Student student=(Student)getItem(position);

            tvID.setText(String.valueOf(student.get_id()));
            tvName.setText(student.getName());
            tvCourse.setText(student.getCourse());
            tvScore.setText(String.valueOf(student.getScore()));

            return view;
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
        lvDisplay=findViewById(R.id.lv_course_display);
    }
}

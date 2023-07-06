package com.example.ept2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    private Note note = new Note();
    private EditText title_et;
    private EditText author_et;
    private TextView time_tv;
    private EditText content_et;
    private Button ok_bt;
    private Button del_bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        title_et = (EditText) findViewById(R.id.et_intro);
        author_et = (EditText) findViewById(R.id.et_author);
        time_tv = (TextView)findViewById(R.id.tv_time);
        content_et = (EditText)findViewById(R.id.et_ctn);

        if(getIntent().getBooleanExtra("isAdd",false)){
            addNote();
        }
        else if(getIntent().getBooleanExtra("isItem",false)){
            title_et.setText(getIntent().getStringExtra("title"));
            author_et.setText(getIntent().getStringExtra("author"));
            time_tv.setText(new Date().toString());
            content_et.setText(getIntent().getStringExtra("content"));
            ok_bt = (Button)findViewById(R.id.bt_ok);
            ok_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(NoteActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });
            delNote();
        }

    }


    public void addNote(){
        ok_bt = (Button)findViewById(R.id.bt_ok);
        ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTitle(title_et.getText().toString());
                note.setAuthor(author_et.getText().toString());
                note.setContent(content_et.getText().toString());
                note.setTime(new Date());
                note.save();
                Intent intent = new Intent(NoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void delNote(){
        del_bt = (Button)findViewById(R.id.bt_del);
        del_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Note.class,"title = ? and author = ?",
                        title_et.getText().toString(),author_et.getText().toString());
                Intent intent = new Intent(NoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
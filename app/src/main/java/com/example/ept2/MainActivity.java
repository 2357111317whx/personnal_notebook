package com.example.ept2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Note> notes = new ArrayList<>();
    private FloatingActionButton add_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        add_btn = (FloatingActionButton)findViewById(R.id.bt_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                intent.putExtra("isAdd",true);
                startActivity(intent);
            }
        });

        initNotes();
        NoteAdapter adapter = new NoteAdapter(MainActivity.this,R.layout.note_item,notes);
        listView = (ListView) findViewById(R.id.lv_1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = notes.get(i);
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                intent.putExtra("isItem",true);
                intent.putExtra("title",note.getTitle());
                intent.putExtra("author",note.getAuthor());
                intent.putExtra("content",note.getContent());
                startActivity(intent);
            }
        });
    }

    public void initNotes(){
        notes = LitePal.findAll(Note.class);
    }





}
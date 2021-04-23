package com.example.juliet2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    DataBase dataBase;
    FloatingActionButton add;
    RecyclerView recyclerView;
    public List<Book> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = DataBase.getInstance(MainActivity.this);

        add = findViewById(R.id.Btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddItem.class));
            }
        });

        recyclerView = findViewById(R.id.ListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        getTasks();
    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Book>> {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                taskList = dataBase
                        .getInstance(getApplicationContext())
                        .getDAO()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(final List<Book> tasks) {
                super.onPostExecute(tasks);
                BookAdapter adapter = new BookAdapter(MainActivity.this, tasks);
                recyclerView.setAdapter(adapter);
            }}

            GetTasks gt = new GetTasks();
           gt.execute();
        }
    }


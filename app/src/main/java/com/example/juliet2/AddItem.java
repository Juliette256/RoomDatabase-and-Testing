package com.example.juliet2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class AddItem extends AppCompatActivity {
    DataBase dataBase;
    private Book book;
  EditText bookname,bookdesc,booktype;
  Button save;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        bookname = findViewById(R.id.bookName);
        bookdesc = findViewById(R.id.bookDescription);
        booktype = findViewById(R.id.bookType);
        save = findViewById(R.id.buttonSave);

        dataBase = DataBase.getInstance(AddItem.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bookname.getText().toString().trim();
                String desc = bookdesc.getText().toString().trim();
                String type = booktype.getText().toString().trim();

                if (name.isEmpty()) {
                    bookname.setError("can not be empty");
                    return;
                }
                if (desc.isEmpty()) {
                    bookdesc.requestFocus();
                    return;
                }
                if (type.isEmpty()) {
                    booktype.requestFocus();
                    return;
                }

                book = new Book(id, name, desc, type);
                new insertTask(AddItem.this, book).execute();
            }
        });

    }
    private class insertTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<AddItem> activityReference;
        private Book book;

        public insertTask(AddItem addItem, Book book) {
            activityReference = new WeakReference<>(addItem);
            this.book = book;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            activityReference.get().dataBase.getDAO().insert(book);
            return true;
        }

        protected void onPostExecute(Boolean bool) {
            if (bool){
                activityReference.get().setResult(book,1);
                startActivity(new Intent(AddItem.this, MainActivity.class));
            }

    }}

    private void setResult(Book book, int i) {
        setResult(i,new Intent().putExtra("BOOK", String.valueOf(book)));
        finish();
    }
}


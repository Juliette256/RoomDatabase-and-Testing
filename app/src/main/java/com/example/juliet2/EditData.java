package com.example.juliet2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditData extends AppCompatActivity {
 EditText name,des,type;
 Button save,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        name=findViewById(R.id.bookName);
        des=findViewById(R.id.bookDescription);
        type=findViewById(R.id.bookType);
        save=findViewById(R.id.buttonSave);
        delete=findViewById(R.id.buttonDelete);

        final Book book= (Book) getIntent().getSerializableExtra("book");

            name.setText(book.getBookName());
            des.setText(book.getBookDescription());
            type.setText(book.getBookType());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTask(book);
            }
        });
        
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteTask(book);
            }
        });

    }

    private void UpdateTask(final Book book) {
        final String new_name = name.getText().toString();
        final String new_des = des.getText().toString();
        final String new_type = type.getText().toString();

        if (new_name.isEmpty()) {
            name.requestFocus();
            return;
        }
        if (new_des.isEmpty()) {
            des.requestFocus();
            return;
        }
        if (new_type.isEmpty()) {
            type.requestFocus();
            return;
        }

     class UpdateTask extends AsyncTask<Void,Void,Void>{
         @Override
         protected Void doInBackground(Void... voids) {

             book.setBookName(new_name);
             book.setBookDescription(new_des);
             book.setBookType(new_type);
             DataBase.getInstance(getApplicationContext()).getDAO().update(book);
             return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);
             startActivity(new Intent(EditData.this,MainActivity.class));

         }}
         UpdateTask ut = new UpdateTask();
        ut.execute();
     }

    private void DeleteTask(final Book book) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DataBase.getInstance(getApplicationContext()).getDAO().delete(book);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Book has been Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(EditData.this, MainActivity.class));
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }}



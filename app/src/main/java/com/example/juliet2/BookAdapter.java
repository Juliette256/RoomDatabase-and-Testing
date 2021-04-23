package com.example.juliet2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    List<Book>taskList = null;
    Context context;

    public BookAdapter(MainActivity mainActivity, List<Book> taskList){
        this.context=mainActivity;
        this.taskList=taskList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder viewHolder= new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        holder.bind(taskList.get(position));
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,itemDesc,itemType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.ItemName);
            itemDesc=itemView.findViewById(R.id.ItemDesc);
            itemType=itemView.findViewById(R.id.ItemType);
        }

        public void bind(final Book book) {
        itemName.setText(book.getBookName());
        itemDesc.setText(book.getBookDescription());
        itemType.setText(book.getBookType());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Book book1= taskList.get(getAdapterPosition());

                Intent intent = new Intent(context, EditData.class);
                intent.putExtra("book", book1);
                context.startActivity(intent);
            }
        });
        }
    }


}
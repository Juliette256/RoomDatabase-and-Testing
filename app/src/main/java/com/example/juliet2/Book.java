package com.example.juliet2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Book implements Serializable {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true) int BookId;
    @ColumnInfo String BookName;
    @ColumnInfo String BookDescription;
    @ColumnInfo String BookType;


    public Book(int BookId,String BookName, String BookDescription, String  BookType){
        this.BookId=BookId;
        this.BookName=BookName;
        this.BookDescription=BookDescription;
        this.BookType= BookType; }


    public int getBookId() { return BookId; }

    public void setBookName(String bookName) { BookName = bookName; }
    public String getBookName() { return BookName; }

    public void setBookDescription(String bookDescription) {BookDescription= bookDescription; }
    public String getBookDescription() { return BookDescription; }

    public void setBookType(String bookType) { BookType = bookType; }
    public String getBookType() { return BookType; }

}


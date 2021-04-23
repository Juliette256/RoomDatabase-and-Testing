package com.example.juliet2;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM Book WHERE BookId = BookId")
    List<Book> getAll();

    @Delete
    public void delete(Book book);

    @Insert
    public void insert(Book book);

    @Update
    public void update(Book book);

    @Delete
    void deleteAll(List<Book> books);

    @Query("Update Book Set BookType=:BookType, BookName=:BookName,BookDescription=:BookDescription WHERE BookId=:BookId")
    void getBookById(String BookName, String BookType,String BookDescription,int BookId);
}

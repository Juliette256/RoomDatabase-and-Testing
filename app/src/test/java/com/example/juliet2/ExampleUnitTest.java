package com.example.juliet2;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@Config(manifest= Config.NONE)
public class ExampleUnitTest {
    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum); }

//    testing my database existence

    private DAO dao;
    private DataBase dataBase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, DataBase.class).allowMainThreadQueries().build();
        dao = dataBase.getDAO();
    }

    @After
    public void closeDb() throws IOException {
        dataBase.close();
    }

    @Test
    public void write() {
     Book book = new Book(1,"george","home","am tired");
     Book book1 = new Book(2,"juliet","find","am done");

        dao.insert(book);
        dao.insert(book1);
        List<Book> books=dao.getAll();
        assertEquals(books.get(0).getBookName(), "george");
        assertEquals(books.get(1).getBookType(), "am done");

    }

    @Test
    public void deleteOne(){
       Book book = new Book(2, "Juliet", "work", "am finished");
       Book book1 = new Book(3, "Juliet", "work", "am finished");

        dao.insert(book);
        dao.insert(book1);

       dao.delete(book1);

        List<Book> updatedBooks = dao.getAll();
        Assert.assertSame(1, updatedBooks.size());
    }

    @Test
    public void deleteAll(){
        Book book = new Book(2, "Juliet", "work", "am finished");
        Book book1 = new Book(3, "Juliet", "work", "am finished");

        dao.insert(book);
        dao.insert(book1);

        List<Book> books = dao.getAll();
        dao.deleteAll(books);

        List<Book> updatedBooks = dao.getAll();
        Assert.assertTrue(updatedBooks.isEmpty());
    }

    @Test
    public void update(){
        Book book4 = new Book(2, "Juliet", "work", "am finished");
        dao.insert(book4);

        book4.setBookType("NEW");
        dao.update(book4);
        List<Book>books=dao.getAll();
        Assert. assertEquals("NEW", books.get(0).getBookType());
    }

    @Test
    public void UpdateById(){
        Book book = new Book(7,"","","");
        dao.insert(book);

        dao.getBookById("","","",7);
        book.setBookName("Jack");
        book.setBookType("new");
        book.setBookDescription("maggie");

        dao.update(book);

        List<Book>books=dao.getAll();
        Assert.assertEquals("Jack",books.get(0).getBookName());
          }


}


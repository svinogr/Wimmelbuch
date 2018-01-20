package info.upump.wimmelbuch.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.data.IData;
import info.upump.wimmelbuch.model.Book;

/**
 * Created by explo on 19.01.2018.
 */

public class DataBaseBookDao extends DataBaseDao implements IData<Book> {

    public DataBaseBookDao(Context context) {
        super(context);
    }

    @Override
    public List<Book> getList() {
        Cursor cursor = database.query(DataBaseHelper.TABLE_BOOK,
                new String[]{
                        DataBaseHelper.TABLE_KEY_ID,
                        DataBaseHelper.TABLE_KEY_TITLE,
                        DataBaseHelper.TABLE_KEY_RATE,
                        DataBaseHelper.TABLE_KEY_IMG_TITLE},
                null, null, null, null, null, null
        );
        List<Book> bookList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getLong(0));
                book.setTitle(cursor.getString(1));
                book.setRate(cursor.getInt(2));
                book.setImgTitle(cursor.getString(3));
                System.out.println(book);
                bookList.add(book);
            } while (cursor.moveToNext());
        }


        return bookList;
    }

    @Override
    public List<Book> getListById(Book book) {
        return null;
    }
}

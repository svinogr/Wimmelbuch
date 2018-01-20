package info.upump.wimmelbuch.data.db;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.data.IData;
import info.upump.wimmelbuch.model.Book;
import info.upump.wimmelbuch.model.Page;

/**
 * Created by explo on 20.01.2018.
 */

public class DataBasePageDao extends DataBaseDao implements IData<Page> {
    public DataBasePageDao(Context context) {
        super(context);
    }

    @Override
    public List<Page> getList() {
        return null;
    }

    @Override
    public List<Page> getListById(Book book) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_PAGE,
                new String[]{
                        DataBaseHelper.TABLE_KEY_ID,
                        DataBaseHelper.TABLE_KEY_BOOK_ID,
                        DataBaseHelper.TABLE_KEY_NUMBER_PAGE,
                        DataBaseHelper.TABLE_KEY_IMG_TITLE,
                        DataBaseHelper.TABLE_KEY_IMG_PAGE},
                DataBaseHelper.TABLE_KEY_BOOK_ID + "=? " , new String[]{String.valueOf(book.getId())}, null, null, null, null
        );

        List<Page> pageList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Page page = new Page();
                page.setId(cursor.getLong(0));
                page.setBookId(cursor.getLong(1));
                page.setNumberPage(cursor.getInt(2));
                page.setImgTitle(cursor.getString(3));
                page.setImgPage(cursor.getString(4));
                pageList.add(page);
                System.out.println(page);
            } while (cursor.moveToNext());
        }
        return pageList;
    }
}

package info.upump.wimmelbuch.data;

import java.util.List;

import info.upump.wimmelbuch.model.Book;

/**
 * Created by explo on 19.01.2018.
 */

public interface IData<T> {
    List<T> getList();
    List<T> getListById(Book book);
}

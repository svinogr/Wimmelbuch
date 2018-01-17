package info.upump.wimmelbuch.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.model.Book;

/**
 * Created by explo on 17.01.2018.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private Context context;
    public BookLoader(Context context) {
        super(context);
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> books = new ArrayList<>(10);
        for(int i = 0; i < 10; i++){
            Book book = new Book();
            book.setId(i);
            book.setTitle("Mein schonstes Wimmelbuch Weihnachten");
            book.setImgTitle("http://img.wimmelbuch.su/2287-home_default/mein-grobes-wimmelbuch-fahrzeuge.jpg");
            book.setRate(i+100);
            books.add(book);
        }
        return books;
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();

    }

    @Override
    public void deliverResult(List<Book> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}

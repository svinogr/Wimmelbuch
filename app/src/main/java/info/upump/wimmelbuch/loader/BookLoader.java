package info.upump.wimmelbuch.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

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
        return null;
    }
}

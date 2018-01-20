package info.upump.wimmelbuch.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.data.IData;
import info.upump.wimmelbuch.data.db.DataBasePageDao;
import info.upump.wimmelbuch.model.Book;
import info.upump.wimmelbuch.model.Page;

/**
 * Created by explo on 17.01.2018.
 */

public class PageLoader extends AsyncTaskLoader<List<Page>> {
    private Context context;
    private Book book;

    public PageLoader(Context context, long idBook) {
        super(context);
        this.context = context;
        this.book = new Book();
        book.setId(idBook);
    }

    @Override
    public List<Page> loadInBackground() {
        IData iData = new DataBasePageDao(context);
        return iData.getListById(book);
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
    public void deliverResult(List<Page> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}

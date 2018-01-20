package info.upump.wimmelbuch.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.data.IData;
import info.upump.wimmelbuch.data.db.DataBaseBookDao;
import info.upump.wimmelbuch.model.Book;


/**
 * Created by explo on 17.01.2018.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private Context context;

    public BookLoader(Context context) {
        super(context);
        this.context = context;

    }

    @Override
    public List<Book> loadInBackground() {
        IData iData = new DataBaseBookDao(context);
        return iData.getList();
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

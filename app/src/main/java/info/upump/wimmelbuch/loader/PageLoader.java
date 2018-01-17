package info.upump.wimmelbuch.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.model.Page;

/**
 * Created by explo on 17.01.2018.
 */

public class PageLoader extends AsyncTaskLoader<List<Page>> {
    private Context context;
    private long idBook;

    public PageLoader(Context context, long idBook) {
        super(context);
        this.context = context;
        this.idBook = idBook;
    }

    @Override
    public List<Page> loadInBackground() {
        List<Page> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Page page = new Page();
            page.setId(i);
            page.setNumberPage(i);
            page.setImgPage("http://img.wimmelbuch.su/144-thickbox_default/mein-superdickes-wimmelbuch.jpg");
            list.add(page);
        }
        return list;
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

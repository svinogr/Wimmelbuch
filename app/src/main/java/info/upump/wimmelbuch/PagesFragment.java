package info.upump.wimmelbuch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.adapter.PageAdapter;
import info.upump.wimmelbuch.loader.PageLoader;
import info.upump.wimmelbuch.model.Book;
import info.upump.wimmelbuch.model.Page;

public class PagesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Page>> {
    private static final String ID_BOOK = "id book";
    private static final String TITLE_BOOK = "title book";
    private static final String RATE_BOOK = "rate book";
    private static final String IMG_TITLE_BOOK = "img title book";
    private static final int NUMBER_OF_SPAN = 2;

    private Book book;
    private List<Page> pageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PageAdapter pageAdapter;


    public PagesFragment() {
        // Required empty public constructor
    }

    public static PagesFragment newInstance(Book book) {
        PagesFragment fragment = new PagesFragment();
        Bundle args = new Bundle();
        args.putLong(ID_BOOK, book.getId());
        args.putString(TITLE_BOOK, book.getTitle());
        args.putInt(RATE_BOOK, book.getRate());
        args.putString(IMG_TITLE_BOOK, book.getImgTitle());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = new Book();
        if (getArguments() != null) {
            book.setId(getArguments().getLong(ID_BOOK));
            book.setTitle(getArguments().getString(TITLE_BOOK));
            book.setRate(getArguments().getInt(RATE_BOOK));
            book.setImgTitle(getArguments().getString(IMG_TITLE_BOOK));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(book.getTitle().substring(0, 1).toUpperCase() + book.getTitle().substring(1));
        View inflate = inflater.inflate(R.layout.fragment_pages, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), NUMBER_OF_SPAN);
        System.out.println(" onCreateView P");
        pageAdapter = new PageAdapter(pageList);

        recyclerView = inflate.findViewById(R.id.fragment_pages_recycle);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(pageAdapter);
        return inflate;
    }

    @Override
    public Loader<List<Page>> onCreateLoader(int id, Bundle args) {
        PageLoader pageLoader = null;
        try {
            long aLong = getArguments().getLong(ID_BOOK);
            pageLoader = new PageLoader(getContext(), aLong);
        } catch (NullPointerException e) {
            System.out.println("где книга БЛЕАТЬ");
        }

        return pageLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Page>> loader, List<Page> data) {
        pageList.clear();
        pageList.addAll(data);
        pageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Page>> loader) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getLoaderManager().initLoader(0, null, this);
    }

}

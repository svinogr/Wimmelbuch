package info.upump.wimmelbuch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import info.upump.wimmelbuch.adapter.BookAdapter;
import info.upump.wimmelbuch.loader.BookLoader;
import info.upump.wimmelbuch.model.Book;


public class BooksFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {
    private RecyclerView recyclerView;
    private List<Book> bookList = new ArrayList<>();
    private BookAdapter bookAdapter;


    public BooksFragment() {
    }

    public static BooksFragment newInstance() {
        BooksFragment fragment = new BooksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Книги");
        View inflate = inflater.inflate(R.layout.fragment_books, container, false);
        recyclerView = inflate.findViewById(R.id.fragment_books_recycler);

        bookAdapter = new BookAdapter(bookList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(bookAdapter);
        return inflate;
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        BookLoader bookLoader = new BookLoader(getContext());
        return bookLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        System.out.println(data.size());
        bookList.clear();
        bookList.addAll(data);
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getLoaderManager().initLoader(0, null, this);
    }
}

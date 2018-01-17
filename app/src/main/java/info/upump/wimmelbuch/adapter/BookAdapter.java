package info.upump.wimmelbuch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.upump.wimmelbuch.R;
import info.upump.wimmelbuch.model.Book;
import info.upump.wimmelbuch.model.BookViewHolder;

/**
 * Created by explo on 17.01.2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_card_layout, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(inflate);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {
        holder.binder(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}

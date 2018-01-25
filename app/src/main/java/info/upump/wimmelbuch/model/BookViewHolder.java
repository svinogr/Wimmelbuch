package info.upump.wimmelbuch.model;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.upump.wimmelbuch.BooksFragment;
import info.upump.wimmelbuch.Controller;
import info.upump.wimmelbuch.PagesFragment;
import info.upump.wimmelbuch.R;

import static android.R.attr.path;

/**
 * Created by explo on 17.01.2018.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView imgTitle;
    private TextView rate;
    private Book book;

    public BookViewHolder(final View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.book_item_card_item_title);
        imgTitle = itemView.findViewById(R.id.book_item_card_item_img_title);
        rate = itemView.findViewById(R.id.book_item_card_item_rate);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BooksFragment.CallBacks callBacks  = (BooksFragment.CallBacks) itemView.getContext();
                callBacks.setSelectedBook(book);
                callBacks.onBookSelected(book);
            }
        });
    }

    public void binder(Book book) {
        this.book = book;
      title.setText(book.getTitle().substring(0,1).toUpperCase()+book.getTitle().substring(1));
        int identifier = itemView.getContext().getApplicationContext().getResources().getIdentifier(book.getImgTitle(), "drawable",itemView.getContext().getApplicationContext().getPackageName());
        if(identifier == 0){identifier = 2130837589;}
        Picasso.with(itemView.getContext())
                .load(identifier)
                .placeholder(R.mipmap.ic_load)
                .error(R.mipmap.ic_noload)
                .fit()
                .into(imgTitle);

        rate.setText(String.valueOf(book.getRate()));
    }
}

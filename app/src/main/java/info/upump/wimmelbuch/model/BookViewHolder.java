package info.upump.wimmelbuch.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.upump.wimmelbuch.Controller;
import info.upump.wimmelbuch.PagesFragment;
import info.upump.wimmelbuch.R;

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
                Controller controller = (Controller) itemView.getContext();
                PagesFragment pagesFragment = PagesFragment.newInstance(book);
                controller.createFragment(pagesFragment);
            }
        });
    }

    public void binder(Book book) {
        this.book = book;
        title.setText(book.getTitle());
        Picasso.with(itemView.getContext())
                .load(book.getImgTitle())
                .fit()
                .placeholder(R.drawable.ic_bookmark_black_24dp)
                .into(imgTitle);

       // imgTitle.setImageBitmap(new );
        rate.setText(String.valueOf(book.getRate()));
    }
}

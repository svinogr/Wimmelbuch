package info.upump.wimmelbuch.model;

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
               /* Controller controller = (Controller) itemView.getContext();
                PagesFragment pagesFragment = PagesFragment.newInstance(book);
                controller.createFragment(pagesFragment);*/
                System.out.println("work holder book");
                BooksFragment.CallBacks callBacks  = (BooksFragment.CallBacks) itemView.getContext();
                callBacks.onBookSelected(book);

            }
        });
    }

    public void binder(Book book) {
        this.book = book;
        title.setText(book.getTitle().substring(0,1).toUpperCase()+book.getTitle().substring(1));
        System.out.println(book.getImgTitle());
        System.out.println(itemView.getContext().getApplicationContext().getPackageName());
        System.out.println("con "+itemView.getContext().getApplicationContext().getResources().getIdentifier("book_142","drawable",itemView.getContext().getApplicationContext().getPackageName()));
        int identifier = itemView.getContext().getApplicationContext().getResources().getIdentifier(book.getImgTitle(), "drawable",itemView.getContext().getApplicationContext().getPackageName());
        System.out.println("ident "+ identifier);
        if(identifier == 0){identifier = 2130837589;}
        Picasso.with(itemView.getContext())
                .load(identifier)
                .error(R.drawable.ic_bookmark_black_24dp)
                .fit()
            //    .placeholder(R.drawable.ic_bookmark_black_24dp)
                .into(imgTitle);

       // imgTitle.setImageBitmap(new );
        rate.setText(String.valueOf(book.getRate()));
    }
}

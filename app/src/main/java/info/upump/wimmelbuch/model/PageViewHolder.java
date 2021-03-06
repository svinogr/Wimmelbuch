package info.upump.wimmelbuch.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.upump.wimmelbuch.Controller;
import info.upump.wimmelbuch.R;
import info.upump.wimmelbuch.ViewPageFragment;

/**
 * Created by explo on 17.01.2018.
 */

public class PageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView number;
    private Page page;

    public PageViewHolder(final View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.page_item_card_layout_image_view);
        number = itemView.findViewById(R.id.page_item_card_layout_number_page_view);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller controller = (Controller) itemView.getContext();
                ViewPageFragment viewPageFragment = ViewPageFragment.newInstance(page);
                controller.createFragment(viewPageFragment);

            }
        });
    }

    public void bind(Page page) {
        this.page = page;
        int identif = itemView.getContext().getApplicationContext().getResources().getIdentifier(page.getImgTitle(),"drawable", itemView.getContext().getPackageName());
        if(identif == 0){identif =2130837589;}
        Picasso.with(itemView.getContext())
                .load(identif)
                .fit()
               // .placeholder(R.drawable.ic_bookmark_black_24dp)
                .into(imageView);
        //imageView.setImageBitmap(page.getImgTitle());
        System.out.println(page.getNumberPage());
        number.setText(String.valueOf(page.getNumberPage()));
    }
}

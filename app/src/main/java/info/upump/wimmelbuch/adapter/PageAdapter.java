package info.upump.wimmelbuch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.upump.wimmelbuch.R;
import info.upump.wimmelbuch.model.Page;
import info.upump.wimmelbuch.model.PageViewHolder;

/**
 * Created by explo on 17.01.2018.
 */

public class PageAdapter extends RecyclerView.Adapter<PageViewHolder>{
    private List<Page> pageList;

    public PageAdapter(List<Page> pageList) {
        this.pageList = pageList;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item_card_layout, parent, false);
        return new PageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        holder.bind(pageList.get(position));
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }
}

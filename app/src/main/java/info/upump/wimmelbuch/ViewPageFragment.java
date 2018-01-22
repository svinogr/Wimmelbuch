package info.upump.wimmelbuch;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;

import info.upump.wimmelbuch.model.Page;

public class ViewPageFragment extends Fragment{
    public static final  String ID_PAGE = "id page";
    public static final  String ID_BOOK = "id book";
    public static final  String NUMBER_PAGE = "number page";
    public static final  String IMG_TITLE_PAGE = "img title";
    public static final  String IMG_PAGE = "img page";
    private static final String START_URL = "https://img.wimmelbuch.su/img/p/";

    private Page page;
    private ImageView imageViewPage;


    public ViewPageFragment() {
        // Required empty public constructor
    }

    public static ViewPageFragment newInstance(Page page) {
        ViewPageFragment fragment = new ViewPageFragment();
        Bundle args = new Bundle();
        args.putLong(ID_PAGE, page.getId());
        args.putLong(ID_BOOK, page.getBookId());
        args.putInt(NUMBER_PAGE, page.getNumberPage());
        args.putString(IMG_TITLE_PAGE, page.getImgTitle());
        args.putString(IMG_PAGE, page.getImgPage());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = new Page();
        if (getArguments() != null) {
            page.setId(getArguments().getLong(ID_PAGE));
            page.setBookId(getArguments().getLong(ID_BOOK));
            page.setNumberPage(getArguments().getInt(NUMBER_PAGE));
            page.setImgTitle(getArguments().getString(IMG_TITLE_PAGE));
            page.setImgPage(getArguments().getString(IMG_PAGE));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_view_page, container, false);
        getActivity().setTitle("Страница "+page.getNumberPage());
        imageViewPage = inflate.findViewById(R.id.view_page_fragment_image_view);
       // int identif = getContext().getApplicationContext().getResources().getIdentifier(page.getImgTitle(),"drawable", getContext().getPackageName());

        Picasso.with(getContext())
                .load(Uri.parse(getURL()))
          //      .placeholder(identif)
                .fit()
                .into(imageViewPage);
        return inflate;
    }

    private String getURL(){
        String split[] = page.getImgPage().split("-");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<split[0].length();i++){
            stringBuilder.append(split[0].substring(i,i+1));
            stringBuilder.append("/");
        }
        String pageURL =START_URL+stringBuilder.toString()+page.getImgPage();
        return pageURL;
    }



}

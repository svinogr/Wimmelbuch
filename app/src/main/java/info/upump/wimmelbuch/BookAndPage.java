package info.upump.wimmelbuch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by explo on 25.01.2018.
 */

public class BookAndPage extends Fragment {
    private BooksFragment.CallBacks callBacks;

    public BookAndPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(callBacks.getSelectedBook()!=null){
            System.out.println(callBacks.getSelectedBook());
            callBacks.onBookSelected(callBacks.getSelectedBook());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println(" onCreateView BP");

        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    public static BookAndPage newInstance() {
        BookAndPage fragment = new BookAndPage();
        return fragment;
    }

    @LayoutRes
    private int getLayoutResourceId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBacks = (BooksFragment.CallBacks) context;

    }
}
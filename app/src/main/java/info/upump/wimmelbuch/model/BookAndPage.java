package info.upump.wimmelbuch.model;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.upump.wimmelbuch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookAndPage extends Fragment {


    public BookAndPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @LayoutRes
    private int getLayoutResourceId() {
        System.out.println(R.layout.activity_masterdetail);
        return R.layout.activity_masterdetail;
    }

}

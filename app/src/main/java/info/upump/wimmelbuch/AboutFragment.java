package info.upump.wimmelbuch;


import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private WebView webView;
    private static final String NAME_FILE= "about.html";


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_about, container, false);
        getActivity().setTitle(getString(R.string.title_about));
        webView = inflate.findViewById(R.id.about_fragment_web_view);
        webView.loadUrl("file:///android_asset/about.html");
        return inflate;
    }


}

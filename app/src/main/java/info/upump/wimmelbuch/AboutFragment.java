package info.upump.wimmelbuch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private WebView webView;
    private static final String NAME_FILE= "file:///android_asset/about.html";

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
        webView.loadUrl(NAME_FILE);
        return inflate;
    }

}

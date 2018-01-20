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

public class ViewPageFragment extends Fragment implements View.OnTouchListener{
    public static final  String ID_PAGE = "id page";
    public static final  String ID_BOOK = "id book";
    public static final  String NUMBER_PAGE = "number page";
    public static final  String IMG_TITLE_PAGE = "img title";
    public static final  String IMG_PAGE = "img page";
    private static final String START_URL = "https://img.wimmelbuch.su/img/p/";

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    private Bitmap bmap;

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
        imageViewPage.setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        imageViewPage = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null && event.getPointerCount() == 2 || event.getPointerCount() == 3) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (imageViewPage.getWidth() / 2) * sx;
                        float yc = (imageViewPage.getHeight() / 2) * sx;
                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        imageViewPage.setImageMatrix(matrix);

        bmap= Bitmap.createBitmap(imageViewPage.getWidth(), imageViewPage.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmap);
        imageViewPage.draw(canvas);

        //fin.setImageBitmap(bmap);
        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        float s=x * x + y * y;
        return (float)Math.sqrt(s);
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

}

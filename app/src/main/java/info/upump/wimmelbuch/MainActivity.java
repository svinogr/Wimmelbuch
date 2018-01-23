package info.upump.wimmelbuch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import info.upump.wimmelbuch.model.Book;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Controller, BooksFragment.CallBacks {

    private static final Uri URL_SHOP =Uri.parse("http://wimmelbuch.su/") ;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        //setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            BooksFragment booksFragment = BooksFragment.newInstance();
            createFragment(booksFragment);

        }

    }

    @LayoutRes
    private int getLayoutResourceId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_general) {
            // Handle the camera action
            BooksFragment booksFragment = new BooksFragment();
            createFragment(booksFragment);
        } else if (id == R.id.nav_to_shop) {
            Intent intent = new Intent(Intent.ACTION_VIEW, URL_SHOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            createFragment(aboutFragment);

        } else if (id == R.id.nav_mailto) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.mail_to)});
            email.putExtra(Intent.EXTRA_SUBJECT,"Wimmelbuch");
            email.putExtra(Intent.EXTRA_TEXT, "");
            //email.setType("message/rfc822");
            email.setType("plain/text");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void createFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        System.out.println(findViewById(R.id.content_main_two_pane_root));
        System.out.println(findViewById(R.id.main_activity_container));
        System.out.println(findViewById(R.id.main_activity_container_page));

        if(fragment instanceof ViewPageFragment){
            if(findViewById(R.id.main_activity_container_page) != null) {// if have
                fragmentTransaction.replace(R.id.content_main_two_pane_root, fragment);
            }else {
                ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.content_main_two_pane_root);
                layout.removeAllViews();
                fragmentTransaction.replace(R.id.main_activity_container, fragment);}
        }else fragmentTransaction.replace(R.id.main_activity_container, fragment);


        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        if (!(fragment instanceof BooksFragment)) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBookSelected(Book book) {
        if(findViewById(R.id.main_activity_container_page) == null){
            PagesFragment fragment = PagesFragment.newInstance(book);
            createFragment(fragment);

        }else {
            PagesFragment fragment = PagesFragment.newInstance(book);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_activity_container_page, fragment);
            fragmentTransaction.commit();
        }

    }
}

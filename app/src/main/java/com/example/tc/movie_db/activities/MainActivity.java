package com.example.tc.movie_db.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tc.movie_db.R;
import com.example.tc.movie_db.fragments.MovieFragment;
import com.example.tc.movie_db.fragments.TVShowsFragment;
import com.example.tc.movie_db.movies.Genre;
import com.example.tc.movie_db.movies.InitialiseMovieGenres;
import com.example.tc.movie_db.movies.MovieGenres;
import com.example.tc.movie_db.network.ApiClient;
import com.example.tc.movie_db.network.MovidbService;
import com.example.tc.movie_db.tvshows.InitialiseTVGenres;
import com.example.tc.movie_db.tvshows.TvGenres;

import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    boolean buttonclicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //showRegionDialogue();
//
//        LinearLayout linearLayout=new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,0);
//        linearLayout.setLayoutParams(layoutParams);
//
//        final EditText editText=new EditText(this);
//        editText.setHint("Enter the ISO 3166-1 code");
//        linearLayout.addView(editText);
//
//        final TextView textView=new TextView(this);
//        textView.setText("Don't Know the code? Click here");
//        textView.setTextSize(20);
//        textView.setTextColor(Color.parseColor("blue"));
//        textView.setPadding(100,10,0,50);
//        textView.setClickable(true);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//
//                Uri uri=Uri.parse("https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2");
//
//                intent.setData(uri);
//                if (intent.resolveActivity(getPackageManager())!=null)
//                    startActivity(intent);
//            }
//        });
//
//        linearLayout.addView(textView);
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("Region");
//        builder.setIcon(R.drawable.ic_map_black_24dp);
//        builder.setView(linearLayout);
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (editText.getText().toString()!=null)
//                region_code=editText.getText().toString();
//            }
//        });
//
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();

        final Retrofit retrofit = ApiClient.getRetrofit();

        MovidbService service = retrofit.create(MovidbService.class);

        Call<MovieGenres> call = service.getMovieGenresList();
        call.enqueue(new Callback<MovieGenres>() {
            @Override
            public void onResponse(Call<MovieGenres> call, Response<MovieGenres> response) {
                MovieGenres movieGenres = response.body();
                List<Genre> movies = movieGenres.getGenres();
                InitialiseMovieGenres.setGenresMap(movies);
            }

            @Override
            public void onFailure(Call<MovieGenres> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        MovidbService service1 = retrofit.create((MovidbService.class));

        Call<TvGenres> call1 = service1.getTVGenresList();
        call1.enqueue(new Callback<TvGenres>() {
            @Override
            public void onResponse(Call<TvGenres> call, Response<TvGenres> response) {
                TvGenres tvGenres = response.body();
                List<com.example.tc.movie_db.tvshows.Genre> tvshows = tvGenres.getGenres();
                InitialiseTVGenres.setGenresMap(tvshows);
            }

            @Override
            public void onFailure(Call<TvGenres> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.movies);
        setTitle("Movies");
        toolbar.setBackgroundColor(Color.parseColor("#424141"));
        setFragment(new MovieFragment());

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final PrettyDialog dialog = new PrettyDialog(this);
            dialog.setTitle("Confirm Exit").setMessage("Are you sure you wish to Exit?")
                    .setIcon(R.drawable.ic_exit_to_app_black_24dp)
                    .addButton("Yes", R.color.pdlg_color_white, R.color.pdlg_color_green, new PrettyDialogCallback() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    })
                    .addButton("No", R.color.pdlg_color_white, R.color.pdlg_color_red, new PrettyDialogCallback() {
                        @Override
                        public void onClick() {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_region) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.movies) {
            if (!item.isChecked()) {
                setTitle("Movies");
                setFragment(new MovieFragment());
            }
        } else if (id == R.id.tv_shows) {
            if (!item.isChecked()) {
                setTitle("T.V. Shows");
                setFragment(new TVShowsFragment());
            }
        } else if (id == R.id.favourites) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

//    public void setthisasfavourite() {
//        final ImageButton imageButton = findViewById(R.id.favbutton);
//        if (buttonclicked) {
//            imageButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
//            buttonclicked = false;
//        } else {
//            imageButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
//            buttonclicked = true;
//        }
//    }
}

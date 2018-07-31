package com.example.tc.movie_db.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tc.movie_db.R;
import com.example.tc.movie_db.adapters.NowShowingAdapter;
import com.example.tc.movie_db.adapters.TvAdapter;
import com.example.tc.movie_db.movies.NowPlaying;
import com.example.tc.movie_db.movies.Popular;
import com.example.tc.movie_db.movies.Result;
import com.example.tc.movie_db.movies.TopRated;
import com.example.tc.movie_db.movies.Upcoming;
import com.example.tc.movie_db.network.ApiClient;
import com.example.tc.movie_db.network.MovidbService;
import com.example.tc.movie_db.tvshows.AiringToday;
import com.example.tc.movie_db.tvshows.Latest;
import com.example.tc.movie_db.tvshows.OnTheAir;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends android.support.v4.app.Fragment {

    RecyclerView recyclerView_AT,recyclerView_OTA,recyclerView_Pop,recyclerView_ToRa;

    ArrayList<com.example.tc.movie_db.tvshows.Result> tvs_At=new ArrayList<>();
    ArrayList<com.example.tc.movie_db.tvshows.Result> tvs_OTA=new ArrayList<>();
    ArrayList<com.example.tc.movie_db.tvshows.Result> tvs_Pop=new ArrayList<>();
    ArrayList<com.example.tc.movie_db.tvshows.Result> tvs_ToRa=new ArrayList<>();

    public TVShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tvshows, container, false);

        recyclerView_AT=view.findViewById(R.id.airing_today_recycler);

        final TvAdapter adapter=new TvAdapter(getContext(),tvs_At);

        recyclerView_AT.setAdapter(adapter);
        recyclerView_AT.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper=new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView_AT);

        final Retrofit retrofit= ApiClient.getRetrofit();

        MovidbService service=retrofit.create(MovidbService.class);

        Call<AiringToday> call=service.getAiringTodaytvs();
        call.enqueue(new Callback<AiringToday>() {
            @Override
            public void onResponse(Call<AiringToday> call, Response<AiringToday> response) {
                AiringToday airingToday=response.body();
                List<com.example.tc.movie_db.tvshows.Result> movies=airingToday.getResults();
                for (com.example.tc.movie_db.tvshows.Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        tvs_At.add(movie);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<AiringToday> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView_OTA=view.findViewById(R.id.on_the_air_recycler);

        final TvAdapter adapter1=new TvAdapter(getContext(),tvs_OTA);

        recyclerView_OTA.setAdapter(adapter1);
        recyclerView_OTA.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper1=new GravitySnapHelper(Gravity.START);
        snapHelper1.attachToRecyclerView(recyclerView_OTA);

        Call<OnTheAir> call1=service.getOnTheAirtvs();
        call1.enqueue(new Callback<OnTheAir>() {
            @Override
            public void onResponse(Call<OnTheAir> call, Response<OnTheAir> response) {
                OnTheAir onTheAir=response.body();
                List<com.example.tc.movie_db.tvshows.Result> movies=onTheAir.getResults();
                for (com.example.tc.movie_db.tvshows.Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        tvs_OTA.add(movie);
                }
                adapter1.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<OnTheAir> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView_Pop=view.findViewById(R.id.popular_recycler2);

        final TvAdapter adapter2=new TvAdapter(getContext(),tvs_Pop);

        recyclerView_Pop.setAdapter(adapter2);
        recyclerView_Pop.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper2=new GravitySnapHelper(Gravity.START);
        snapHelper2.attachToRecyclerView(recyclerView_Pop);

        Call<com.example.tc.movie_db.tvshows.Popular> call2=service.getPopulartvs();
        call2.enqueue(new Callback<com.example.tc.movie_db.tvshows.Popular>() {
            @Override
            public void onResponse(Call<com.example.tc.movie_db.tvshows.Popular> call, Response<com.example.tc.movie_db.tvshows.Popular> response) {
                com.example.tc.movie_db.tvshows.Popular popular=response.body();
                List<com.example.tc.movie_db.tvshows.Result> movies=popular.getResults();
                for (com.example.tc.movie_db.tvshows.Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        tvs_Pop.add(movie);
                }
                adapter2.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<com.example.tc.movie_db.tvshows.Popular> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView_ToRa=view.findViewById(R.id.top_rated_recycler2);

        final TvAdapter adapter3=new TvAdapter(getContext(),tvs_ToRa);

        recyclerView_ToRa.setAdapter(adapter3);
        recyclerView_ToRa.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper3=new GravitySnapHelper(Gravity.START);
        snapHelper3.attachToRecyclerView(recyclerView_ToRa);

        Call<com.example.tc.movie_db.tvshows.TopRated> call3=service.getTopRatedtvs();
        call3.enqueue(new Callback<com.example.tc.movie_db.tvshows.TopRated>() {
            @Override
            public void onResponse(Call<com.example.tc.movie_db.tvshows.TopRated> call, Response<com.example.tc.movie_db.tvshows.TopRated> response) {
                com.example.tc.movie_db.tvshows.TopRated topRated=response.body();
                List<com.example.tc.movie_db.tvshows.Result> movies=topRated.getResults();
                for (com.example.tc.movie_db.tvshows.Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        tvs_ToRa.add(movie);
                }
                adapter3.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<com.example.tc.movie_db.tvshows.TopRated> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}

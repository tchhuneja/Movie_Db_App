package com.example.tc.movie_db.fragments;


import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tc.movie_db.adapters.NowShowingAdapter;
import com.example.tc.movie_db.R;
import com.example.tc.movie_db.movies.NowPlaying;
import com.example.tc.movie_db.movies.Popular;
import com.example.tc.movie_db.movies.Result;
import com.example.tc.movie_db.movies.TopRated;
import com.example.tc.movie_db.movies.Upcoming;
import com.example.tc.movie_db.network.ApiClient;
import com.example.tc.movie_db.network.MovidbService;
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
public class MovieFragment extends android.support.v4.app.Fragment {

    RecyclerView recyclerView_NS,recyclerView_U,recyclerView_P,recyclerView_TR;
    ArrayList<Result> mmovies_NS=new ArrayList<>();
    ArrayList<Result> mmovies_U=new ArrayList<>();
    ArrayList<Result> mmovies_P=new ArrayList<>();
    ArrayList<Result> mmovies_TR=new ArrayList<>();

    int fetchedCalls = 0;

    public MovieFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView_NS=view.findViewById(R.id.now_showing_recycler);

        final NowShowingAdapter adapter=new NowShowingAdapter(getContext(),mmovies_NS);

        recyclerView_NS.setAdapter(adapter);
        recyclerView_NS.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper=new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView_NS);

        final Retrofit retrofit= ApiClient.getRetrofit();

        MovidbService service=retrofit.create(MovidbService.class);

        Call<NowPlaying> call=service.getNowPlaying();
        call.enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                NowPlaying nowPlaying=response.body();
                List<Result> movies=nowPlaying.getResults();
                for (Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        mmovies_NS.add(movie);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView_U=view.findViewById(R.id.upcoming_recycler);

        final NowShowingAdapter adapter1=new NowShowingAdapter(getContext(),mmovies_U);

        recyclerView_U.setAdapter(adapter1);
        recyclerView_U.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper1=new GravitySnapHelper(Gravity.START);
        snapHelper1.attachToRecyclerView(recyclerView_U);

        Call<Upcoming> call1=service.getUpcoming();
        call1.enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(Call<Upcoming> call, Response<Upcoming> response) {
                Upcoming upcoming=response.body();
                List<Result> movies=upcoming.getResults();
                for (Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        mmovies_U.add(movie);
                }
                adapter1.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Upcoming> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView_P=view.findViewById(R.id.popular_recycler);

        final NowShowingAdapter adapter2=new NowShowingAdapter(getContext(),mmovies_P);

        recyclerView_P.setAdapter(adapter2);
        recyclerView_P.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper2=new GravitySnapHelper(Gravity.START);
        snapHelper2.attachToRecyclerView(recyclerView_P);

        Call<Popular> call2=service.getPopular();
        call2.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                Popular popular=response.body();
                List<Result> movies=popular.getResults();
                for (Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        mmovies_P.add(movie);
                }
                adapter2.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Popular> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView_TR=view.findViewById(R.id.top_rated_recycler);

        final NowShowingAdapter adapter3=new NowShowingAdapter(getContext(),mmovies_TR);

        recyclerView_TR.setAdapter(adapter3);
        recyclerView_TR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper3=new GravitySnapHelper(Gravity.START);
        snapHelper3.attachToRecyclerView(recyclerView_TR);

        Call<TopRated> call3=service.getTopRated();
        call3.enqueue(new Callback<TopRated>() {
            @Override
            public void onResponse(Call<TopRated> call, Response<TopRated> response) {
                TopRated topRated=response.body();
                List<Result> movies=topRated.getResults();
                for (Result movie:movies) {
                    if (movie.getBackdropPath()!=null)
                        mmovies_TR.add(movie);
                }
                adapter3.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<TopRated> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}

package com.example.tc.movie_db;


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

    RecyclerView recyclerView;
    ArrayList<Result> mmovies=new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView=view.findViewById(R.id.now_showing_recycler);

        final NowShowingAdapter adapter=new NowShowingAdapter(getContext(),mmovies);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper=new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);

        final Retrofit retrofit=ApiClient.getRetrofit();

        MovidbService service=retrofit.create(MovidbService.class);
        Call<NowPlaying> call=service.getNowPlaying();
        call.enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                NowPlaying nowPlaying=response.body();
                List<Result> movies=nowPlaying.getResults();
                mmovies.addAll(movies);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}

package com.example.tc.movie_db.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.tc.movie_db.CastViewHolder;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder> {

    Context context;
    //ArrayList<> cast;

    public CastAdapter(Context context) {
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

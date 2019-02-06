package com.sreeyainfotech.mapexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sreeyainfotech.mapexample.R;
import com.sreeyainfotech.mapexample.model.Location_sub;

import java.util.List;

public class DetailAdapter  extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    private List<Location_sub> moviesList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title ;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tittle);
            image = (ImageView) view.findViewById(R.id.image);

        }
    }


    public DetailAdapter(Context context, List<Location_sub> moviesList) {
        this.moviesList = moviesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_adpter_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Location_sub movie = moviesList.get(position);
        holder.title.setText(movie.getName());

//        Picasso.with(context)
//                .load(movie.getFilePath())
//                .networkPolicy(NetworkPolicy.NO_CACHE)
//                .memoryPolicy(MemoryPolicy.NO_CACHE)
//                .resize(500, 500)
//                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

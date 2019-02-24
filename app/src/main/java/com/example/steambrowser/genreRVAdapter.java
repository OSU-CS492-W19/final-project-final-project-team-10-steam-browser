package com.example.steambrowser;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class genreRVAdapter extends RecyclerView.Adapter<genreRVAdapter.GenreThumbViewHolder> {
    private ArrayList<String> genres;

    @Override
    public GenreThumbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.genre_category_thumb, parent, false);
        //make api cal
        return new GenreThumbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreThumbViewHolder holder, int position) {
        holder.bind(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }


    class GenreThumbViewHolder extends RecyclerView.ViewHolder {
        private TextView mGenreName;
        private ImageView mGenrePic;

        public GenreThumbViewHolder(View itemView) {
            super(itemView);
            mGenreName = (TextView)itemView.findViewById(R.id.tv_genre_name);
            mGenrePic = (ImageView) itemView.findViewById(R.id.iv_genre_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //create intent here to other page?
                }
            });
        }

        private String getShortDate(String longDate) throws ParseException {
            String [] parts = longDate.split(" ");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date       = format.parse (parts[0]);
            String shortDate = date.toString();
            // we only care about the date month, but it gives us othe crap
            return shortDate.split("00")[0];
        }

        // simple on bind, all we do is use the string to insert into the text view,
        //and use the string to resolve the path as well.
        public void bind(String genre){

            mGenreName.setText(genre);

        }
    }


}
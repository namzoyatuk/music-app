package com.example.music_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music_app.R;
import com.example.music_app.database.entitites.Album;
import com.example.music_app.databinding.ItemTrackBinding;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.ui.AlbumActivity;
import com.example.music_app.ui.ArtistActivity;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private Context context;
    private TrackDto track;

    public TrackAdapter(Context context, TrackDto track) {
        this.context = context;
        this.track = track;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemTrackBinding binding = ItemTrackBinding.inflate(inflater, parent, false);
        return new TrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Glide.with(context).load(track.getAlbum().getImages().get(0).getUrl()).into(holder.binding.trackImage);
        holder.binding.trackArtistName.setText(track.getArtists().get(0).getName());
        holder.binding.trackAlbumName.setText(track.getAlbum().getName());
        holder.binding.trackName.setText(track.getName());

        holder.binding.trackAlbumName.setOnClickListener( v -> {
            Intent intent = new Intent(context, AlbumActivity.class);
            intent.putExtra("albumId", track.getAlbum().getId());
            context.startActivity(intent);
        });

        holder.binding.trackArtistName.setOnClickListener( v -> {
            Intent intent = new Intent(context, ArtistActivity.class);
            intent.putExtra("artistId", track.getArtists().get(0).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        private final ItemTrackBinding binding;

        public TrackViewHolder(ItemTrackBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

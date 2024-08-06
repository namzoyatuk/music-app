package com.example.music_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.music_app.databinding.ItemAlbumBinding;
import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.ui.ArtistActivity;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private final Context context;
    private final AlbumDto album;

    public AlbumAdapter(Context context, AlbumDto album) {
        this.context = context;
        this.album = album;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull
    ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemAlbumBinding binding = ItemAlbumBinding.inflate(inflater, parent, false);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Glide.with(context).load(album.getImages().get(0).getUrl()).into(holder.binding.albumImage);
        holder.binding.albumName.setText(album.getName());
        holder.binding.albumArtist.setText(album.getArtists().get(0).getName());

        String releaseDate = album.getRelease_date();
        String year = releaseDate.split("-")[0];
        holder.binding.albumYear.setText(year);

        holder.binding.albumArtist.setOnClickListener(v -> {
            Intent intent = new Intent(context, ArtistActivity.class);
            intent.putExtra("artistId", album.getArtists().get(0).getId());
            context.startActivity(intent);
        });

        holder.binding.albumName.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final ItemAlbumBinding binding;

        public AlbumViewHolder(ItemAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

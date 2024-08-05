package com.example.music_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music_app.databinding.ItemArtistBinding;
import com.example.music_app.network.DTO.ArtistDto;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private final Context context;
    private final ArtistDto artist;

    public ArtistAdapter(Context context, ArtistDto artist) {
        this.context = context;
        this.artist = artist;
    }

    //Inflate the layout for each item and create a ViewHolder
    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemArtistBinding binding = ItemArtistBinding.inflate(inflater, parent, false);
        return new ArtistViewHolder(binding);
    }

    // Bind data to the views in the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Glide.with(context).load(artist.getImages().get(0).getUrl()).into(holder.binding.artistImage);
        holder.binding.artistName.setText(artist.getName());
    }

    @Override
    public int getItemCount() {
        return 1;

    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        private final ItemArtistBinding binding;

        public ArtistViewHolder(ItemArtistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

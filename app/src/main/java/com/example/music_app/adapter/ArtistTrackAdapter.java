package com.example.music_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music_app.databinding.ItemArtistTrackBinding;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.ui.TrackActivity;

import java.util.List;

public class ArtistTrackAdapter extends RecyclerView.Adapter<ArtistTrackAdapter.ArtistTrackViewHolder> {
    private final Context context;
    private final List<TrackDto> tracks;

    public ArtistTrackAdapter(Context context, List<TrackDto> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public ArtistTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemArtistTrackBinding binding = ItemArtistTrackBinding.inflate(inflater, parent, false);
        return new ArtistTrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistTrackViewHolder holder, int position) {
        TrackDto track = tracks.get(position);
        // Logcat if get name
        Log.d("ArtistTrackAdapter", "onBindViewHolder: " + track.getName());

        Glide.with(context).load(track.getAlbum().getImages().get(0).getUrl()).into(holder.binding.artistTrackImage);
        holder.binding.artistTrackName.setText(track.getName());

        holder.binding.artistTrackName.setOnClickListener(v -> {
            Intent intent = new Intent(context, TrackActivity.class);
            intent.putExtra("trackId", track.getId());
            context.startActivity(intent);
        });
    }

    public int getItemCount() {
        return tracks.size();
    }

    public static class ArtistTrackViewHolder extends RecyclerView.ViewHolder {
        private final ItemArtistTrackBinding binding;

        public ArtistTrackViewHolder(ItemArtistTrackBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.music_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.databinding.ItemAlbumTrackBinding;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.ui.TrackActivity;

import java.util.List;

public class AlbumTrackAdapter extends RecyclerView.Adapter<AlbumTrackAdapter.AlbumTrackViewHolder> {
    private final Context context;
    private final List<TrackDto> tracks;

    public AlbumTrackAdapter(Context context, List<TrackDto> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public AlbumTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemAlbumTrackBinding binding = ItemAlbumTrackBinding.inflate(inflater, parent, false);
        return new AlbumTrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumTrackViewHolder holder, int position) {
        TrackDto track = tracks.get(position);
        // Logcat if get name
        Log.d("AlbumTrackAdapter", "onBindViewHolder: " + track.getName());
        holder.binding.albumTrackName.setText(track.getName());

        holder.binding.albumTrackName.setOnClickListener(v -> {
            Intent intent = new Intent(context, TrackActivity.class);
            intent.putExtra("trackId", track.getId());
            intent.putExtra("previewUrl", track.getPreview_url());
            context.startActivity(intent);
        });

        holder.binding.albumTrackName.setSelected(true);
    }

    public int getItemCount() {
        return tracks.size();
    }

    public static class AlbumTrackViewHolder extends RecyclerView.ViewHolder {
        private final ItemAlbumTrackBinding binding;

        public AlbumTrackViewHolder(ItemAlbumTrackBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

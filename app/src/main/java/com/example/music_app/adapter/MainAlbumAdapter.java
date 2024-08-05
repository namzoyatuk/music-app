package com.example.music_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music_app.databinding.ItemMainAlbumBinding;
import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.ui.AlbumActivity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainAlbumAdapter extends RecyclerView.Adapter<MainAlbumAdapter.MainAlbumViewHolder> {
    private final Context context;
    private List<AlbumDto> albumList;

    public MainAlbumAdapter(Context context) {
        this.context = context;
        this.albumList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MainAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMainAlbumBinding binding = ItemMainAlbumBinding.inflate(layoutInflater, parent, false);

        binding.imageMainAlbum.setMaxHeight(200);


        return new MainAlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAlbumViewHolder holder, int position) {
        AlbumDto album = albumList.get(position);
        holder.bind(album);

        holder.binding.imageMainAlbum.setOnClickListener( v -> {
            Intent intent = new Intent(context, AlbumActivity.class);
            intent.putExtra("albumId", albumList.get(position).getId());
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }


    static class MainAlbumViewHolder extends RecyclerView.ViewHolder {
        private final ItemMainAlbumBinding binding;

        public MainAlbumViewHolder(ItemMainAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull AlbumDto album) {
            Glide.with(binding.getRoot().getContext())
                    .load(album.getImages().get(0).getUrl())
                    .into(binding.imageMainAlbum);
            binding.imageMainAlbum.setTag(album.getId());
        }
    }



}
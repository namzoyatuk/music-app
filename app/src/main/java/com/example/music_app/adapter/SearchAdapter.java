package com.example.music_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music_app.R;
import com.example.music_app.databinding.ItemSearchResultBinding;
import com.example.music_app.network.DTO.Searchable;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private final List<Searchable> searchResults;

    public SearchAdapter(Context context, List<Searchable> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemSearchResultBinding binding = ItemSearchResultBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Searchable item = searchResults.get(position);
        holder.bind(item);


    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        private final ItemSearchResultBinding binding;

        public SearchViewHolder(ItemSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Searchable searchable) {
            Glide.with(binding.getRoot()).load(searchable.getImageUrl()).into(binding.searchableImage);
            binding.searchableName.setText(searchable.getName());
            binding.searchableType.setText(searchable.getType());
        }
    }
}

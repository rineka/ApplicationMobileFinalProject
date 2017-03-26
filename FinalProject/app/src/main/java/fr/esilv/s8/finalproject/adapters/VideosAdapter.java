package fr.esilv.s8.finalproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esilv.s8.finalproject.R;
import fr.esilv.s8.finalproject.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.finalproject.models.Videos;
import fr.esilv.s8.finalproject.viewholders.VideosViewHolder;

/**
 * Created by Carine on 26/03/2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosViewHolder> {

    private final Videos videos;
    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosAdapter(Videos videos) {
        this.videos = videos;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_video, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        holder.setOnVideoSelectedListener(onVideoSelectedListener);
        holder.bind(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos != null ? videos.size() : 0;
    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}


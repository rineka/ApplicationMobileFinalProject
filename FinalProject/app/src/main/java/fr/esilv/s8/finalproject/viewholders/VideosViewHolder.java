package fr.esilv.s8.finalproject.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.esilv.s8.finalproject.R;
import fr.esilv.s8.finalproject.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.finalproject.models.Video;

/**
 * Created by Carine on 26/03/2017.
 */

public class VideosViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private TextView datePublication;
    private TextView authors;
    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.titleTextView);
        description = (TextView) itemView.findViewById(R.id.descriptionTextView);
        datePublication = (TextView) itemView.findViewById(R.id.datePublicationTextView);
        authors = (TextView) itemView.findViewById(R.id.authorsTextView);
    }

    public void bind(final Video video) {

        title.setText(video.getFirstItems().getSnippet().getTitle());
        description.setText(video.getFirstItems().getSnippet().getDescription());
        datePublication.setText(video.getFirstItems().getSnippet().getPublishedAt());
        authors.setText(video.getFirstItems().getSnippet().getChannelTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVideoSelectedListener == null) {
                    return;
                }
                onVideoSelectedListener.onVideoSelected(video);
            }
        });
    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}
package com.thef33d.dirtysprite.dirtysprite.adapters;

import android.content.Context;

import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.thef33d.dirtysprite.dirtysprite.R;
import com.thef33d.dirtysprite.dirtysprite.models.Media;
import com.thef33d.dirtysprite.dirtysprite.util.CircleTransform;
import com.yqritc.scalablevideoview.ScalableType;
import com.yqritc.scalablevideoview.ScalableVideoView;

import java.util.ArrayList;

/**
 * Created by feral on 8/9/16.
 */
public class MediaCardAdapter extends RecyclerView.Adapter<MediaCardAdapter.CardViewHolder> {

        private ArrayList<Media> mediaItems;
        // Store the context for easy access
        private Context mContext;

        public MediaCardAdapter(Context context, ArrayList<Media> items) {
            mContext = context;
            mediaItems = items;
        }

        public void setMediaItems(ArrayList<Media> items){
            mediaItems.clear();
            mediaItems.addAll(items);
            notifyDataSetChanged();
        }

        private Context getContext() {
            return mContext;
        }


        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LinearLayout v = (LinearLayout) LayoutInflater.from(context)
                    .inflate(R.layout.media_card_view, parent, false);
            CardViewHolder vh = new MediaCardAdapter.CardViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
            Media curItem = mediaItems.get(i);
            cardViewHolder.setText(curItem.creatorName);
            cardViewHolder.SetImage(curItem.previewImg);
            cardViewHolder.setAvi(curItem.creatorAvi);
            cardViewHolder.setVideo(curItem.video);
        }

        @Override
        public int getItemCount() {
            return mediaItems.size();
        }

        public static class CardViewHolder extends RecyclerView.ViewHolder {
            public TextView mCardUrl;
            public ImageView mCardImage;
            public ImageView mCardAvi;
            public ScalableVideoView mCardVideo;
            public Context cardViewContext;

            public CardViewHolder(LinearLayout itemView) {
                super(itemView);
                cardViewContext = itemView.getContext();
                mCardUrl = (TextView) itemView.findViewById(R.id.tvCount);
                mCardImage = (ImageView) itemView.findViewById(R.id.imageView);
                mCardAvi = (ImageView) itemView.findViewById(R.id.avi_image_view);
                mCardVideo = (ScalableVideoView) itemView.findViewById(R.id.card_video_view);
            }

            public void setAvi(String avi) {
                if(avi != null) {
                    mCardAvi.setVisibility(View.VISIBLE);
                    Picasso.with(cardViewContext).load(avi)
                            .resize(150, 150)
                            .centerCrop()
                            .transform(new CircleTransform())
                            //.placeholder(mCardAvi)
                            .into(mCardAvi);
                }
                else {
                    mCardAvi.setVisibility(View.INVISIBLE);
                }
            }

            public void setText(String url) {
                mCardUrl.setText(url);
            }

            public void SetImage(String previewImage) {
                if(previewImage != null) {
                    mCardImage.setVisibility(View.VISIBLE);
                    Picasso.with(cardViewContext).load(previewImage)
                            .resize(400, 400)
                            .centerCrop()
                            .into(mCardImage);
                }
                else {
                    mCardImage.setVisibility(View.INVISIBLE);
                }
            }

            public void setVideo(String videoUrl) {
                mCardImage.setVisibility(View.VISIBLE);
                if(videoUrl != null) {

                    try {
                        mCardVideo.setDataSource(videoUrl);
                        mCardVideo.setScalableType(ScalableType.FIT_START);
                        mCardVideo.setVolume(0, 0);
                        mCardVideo.setLooping(true);
                        mCardVideo.prepareAsync(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mCardVideo.start();
                                mCardImage.setVisibility(View.INVISIBLE);
                            }
                        });
                    } catch (Exception e) {
                        //handle error
                        e.printStackTrace();
                        mCardImage.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
}


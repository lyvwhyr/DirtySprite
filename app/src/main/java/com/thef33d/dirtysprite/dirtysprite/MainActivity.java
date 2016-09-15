package com.thef33d.dirtysprite.dirtysprite;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.os.Bundle;
import com.thef33d.dirtysprite.dirtysprite.activities.ClipboardHelper;

import com.thef33d.dirtysprite.dirtysprite.adapters.MediaCardAdapter;
import com.thef33d.dirtysprite.dirtysprite.database.DataSource;
import com.thef33d.dirtysprite.dirtysprite.database.sources.MediaSource;
import com.thef33d.dirtysprite.dirtysprite.models.Media;
import com.thef33d.dirtysprite.dirtysprite.util.InstagramParser;
import com.thef33d.dirtysprite.dirtysprite.util.Logger;

import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;


import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;


public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {


    private RecyclerView mRecyclerView;
    private DataSource mDataSource;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Media> mediaItems;

    private ArrayList<Media> unParsedMedia;
    private boolean scraperBusy;
    private Media lastMediaItem;

    private WebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.media_main);

        ClipboardHelper.startService(this);

        isStoragePermissionGranted();

        mRecyclerView = (RecyclerView) findViewById(R.id.media_cards);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.setItemAnimator(new FadeInDownAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(1000);

        mDataSource = new DataSource(this);


        mediaItems = getParsedMedia();
        // attach new adapter and insert media list data
        mAdapter = new MediaCardAdapter(this, mediaItems);
        SlideInBottomAnimationAdapter slideInAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        //slideInAdapter.setDuration(500);
        mRecyclerView.setAdapter(slideInAdapter);
        //mRecyclerView.setAdapter(mAdapter);

        myWebView = (WebView) findViewById(R.id.web_scrapper_view);

        scrapeUrls();
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Release the Camera because we don't need it when paused
        // and other activities might need to use it.
        if (!scraperBusy && unParsedMedia.size() < 1) {
            unParsedMedia = getUnparsedMedia();
            mediaItems.clear();
            mediaItems.addAll(getParsedMedia());
            mAdapter.notifyDataSetChanged();
            scrapeUrls();
        }
    }

    private void scrapeUrls() {

        class JsObject {
            @JavascriptInterface
            public void bodyDump(String htmlBody) {
                Logger.d("bodyDump: event called");
                InstagramParser post = new InstagramParser(htmlBody, lastMediaItem);

                Logger.d("url " + post.url);
                Logger.d("type " + post.type);
                Logger.d("image " + post.image);
                Logger.d("creatorAvi " + post.creatorAvi);
                Logger.d("creatorName " + post.creatorName);
                Logger.d("creatorUrl " + post.creatorUrl);
                Logger.d("previewImg " + post.previewImg);
                Logger.d("video " + post.video);
                Logger.d("parseDate " + post.parseDate);

                mDataSource.getMediaSource().update(post);
                unParsedMedia.remove(0);
                scraperBusy = false;

                Logger.d("Scrapper: parsed  " + lastMediaItem.url);

            }
        }
        String jsBodyScrapeOnLoad = "window.onload = function() {"+
                "var bodyString = document.body.innerHTML.toString();" +
                "scrapper.bodyDump(bodyString);" +
                "}";
        String jsBodyScrape = "window.onload = function() {"+
                "var bodyString = document.body.innerHTML.toString();" +
                "scrapper.bodyDump(bodyString);" +
                "}";

        if(unParsedMedia == null) {
            unParsedMedia = getUnparsedMedia();
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(new JsObject(), "scrapper");
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedError(WebView v, WebResourceRequest req, WebResourceError e) {
                    String url = v.getUrl();
                    Logger.d("Scrapper: Error loading URL " + url);
                    Logger.d(e.toString());
                    Toast.makeText(getApplicationContext(), "Failed to load url: " + url, Toast.LENGTH_SHORT).show();
                    unParsedMedia.remove(0);
                    scraperBusy = false;
                }
            });
        }



        if(scraperBusy) {
            Logger.d("Scrapper: busy with  " + lastMediaItem.url);
            myWebView.evaluateJavascript(jsBodyScrape, null);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scrapeUrls();
                }
            }, 3000);
        }
        else {

            mediaItems.clear();
            mediaItems.addAll(getParsedMedia());
            mAdapter.notifyItemInserted(0);

            if (unParsedMedia.size() > 0) {
                lastMediaItem = unParsedMedia.get(0);
                Logger.d("Scrapper: Loading url " + lastMediaItem.url);
                myWebView.evaluateJavascript(jsBodyScrapeOnLoad, null);
                scraperBusy = true;
                myWebView.loadUrl(lastMediaItem.url);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrapeUrls();
                    }
                }, 1000);
            }
            else {
                Logger.d("Scrapper: scrapping jobs complete ");
            }
        }


    }

    /**
     * retrieve media list data from database
     */
    private ArrayList<Media> getParsedMedia() {
        MediaSource mediaSource;
        ArrayList<Media> mediaList = new ArrayList<>();
        try {
            mediaSource = mDataSource.getMediaSource();
            mediaList = mediaSource.getParsedMedia();
        } catch (Exception e) {
            Logger.d("Failed to retrieve media list");
        }
        return mediaList;
    }

    /**
     * retrieve media list data from database
     */
    private ArrayList<Media> getUnparsedMedia() {
        MediaSource mediaSource;
        ArrayList<Media> mediaList = new ArrayList<>();
        try {
            mediaSource = mDataSource.getMediaSource();
            mediaList = mediaSource.getUnparsedMedia();
        } catch (Exception e) {
            Logger.d("Failed to retrieve media list");
        }
        return mediaList;
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Logger.d("MainActivity: Permission is granted");
                return true;
            } else {

                Logger.d("MainActivity: Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Logger.d("MainActivity: Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Logger.d("MainActivity: Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }

}


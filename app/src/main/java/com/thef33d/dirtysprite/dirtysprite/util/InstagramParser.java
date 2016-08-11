package com.thef33d.dirtysprite.dirtysprite.util;

import android.support.annotation.Nullable;
import android.util.Log;

import com.thef33d.dirtysprite.dirtysprite.models.Media;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by feral on 8/5/16.
 */
public class InstagramParser extends Media {

    private static Integer EXTRACT_TEXT = 0;
    private static Integer EXTRACT_SOURCE = 1;
    private static Integer EXTRACT_LINK = 2;
    private static Integer EXTRACT_DATETIME = 3;

    private static String VIDEO = "video";
    private static String IMAGE = "image";

    private static String IG_SIZE_REPLACE = "(/s([0-9][0-9][0-9])x([0-9][0-9][0-9])/)";
    private static String IG_SIZE_MATCH = "((.*)/s([0-9][0-9][0-9])x([0-9][0-9][0-9])/(.*))";


    public InstagramParser(String html, Media unParsedMedia) {

        this.image = parseImage(html);
        this.video = parseVideo(html);
        this.previewImg = parsePreviewImg(html);
        this.postDate = parsePostDate(html);

        this.creatorName = parseCreatorName(html);
        this.creatorAvi = parseCreatorAvi(html);
        this.creatorUrl = parseCreatorUrl(html);

        this.mediaId = unParsedMedia.mediaId;
        this.url = unParsedMedia.url;
        this.isSavedToDevice = 0;
        this.parseDate = new Date();
        this.type = getType();
    }

    // given html, selector, and desired attribute
    // return a string of desired attribute
    @Nullable
    private static String parser(String html, String selector, Integer extractAttr) {
        Document doc = Jsoup.parse(html);
        Element el = doc.select(selector).first();
        String parsed;
        if (el != null) {
            if (extractAttr.equals(EXTRACT_SOURCE)) {
                parsed = el.absUrl("src");
            }
            else if (extractAttr.equals(EXTRACT_LINK)) {
                parsed = el.absUrl("href");
            }
            else if (extractAttr.equals(EXTRACT_DATETIME)) {
                parsed = el.attr("datetime");
            }
            else {
                parsed = el.ownText();
            }
            return parsed;
        }
        return null;
    }

    private String getType() {
        if(this.video == null || this.video.isEmpty()) {
            return IMAGE;
        }
        return VIDEO;

    }

    @Nullable
    private static String parseCreatorAvi(String html) {
        String selector = "article > header > a > img";
        String aviUrl = parser(html, selector, EXTRACT_SOURCE);
        //String[] urlParts;
        if(aviUrl != null) {
            if(aviUrl.matches(IG_SIZE_MATCH)) {

                aviUrl = aviUrl.replaceAll(IG_SIZE_REPLACE, "/s200x200/");
                Logger.d("IG_PARSER: matched size regex " + aviUrl);
            }
            /**
             * TODO: parse full sized version of image to resized 200x200

            else {
                urlParts = aviUrl.split("/");
                urlParts.length
            }
             */

        }
        return aviUrl;
    }

    @Nullable
    private static String parseCreatorUrl(String html) {
        String selector = "article > header > div > a";
        return parser(html, selector, EXTRACT_LINK);
    }

    @Nullable
    private static String parseCreatorName(String html) {
        String selector = "article > header > div > a";
        return parser(html, selector, EXTRACT_TEXT);
    }

    @Nullable
    private static String parsePreviewImg(String html) {
        String selector = "article > div > div > div img";
        return parser(html, selector, EXTRACT_SOURCE);
    }

    @Nullable
    private static String parseVideo(String html) {
        String vidSelector = "article > div > div > div video ";
        return parser(html, vidSelector, EXTRACT_SOURCE);
    }

    @Nullable
    private static String parseImage(String html) {
        String imgSelector = "article > div > div > div img ";
        String imageUrl = parser(html, imgSelector, EXTRACT_SOURCE);
        if(imageUrl != null) {
            imageUrl.replaceAll(IG_SIZE_REPLACE, "");
        }
        return imageUrl;
    }

    @Nullable
    private static Date parsePostDate(String html) {
        String selector = "article > div > section > a > time";
        String isoPostDate = parser(html, selector, EXTRACT_DATETIME);
        try {
            return Utils.isoFormat.parse(isoPostDate);
        }
        catch(ParseException e) {
            Logger.d("Failed to parse post date from Instagram Post");
            Logger.d(e.getMessage());
            return null;
        }

    }

}

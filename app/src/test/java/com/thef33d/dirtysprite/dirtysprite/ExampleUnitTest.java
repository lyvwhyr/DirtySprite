package com.thef33d.dirtysprite.dirtysprite;

import com.thef33d.dirtysprite.dirtysprite.models.Media;
import com.thef33d.dirtysprite.dirtysprite.util.InstagramParser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    String postImageUrl = "https://scontent.cdninstagram.com/t51.2885-15/e35/14128887_1623148301316473_885148507_n.jpg";
    String postCreatorUrl = "https://www.instagram.com/carfanatics/";
    String postCreatorName = "carfanatics";
    String postUrl = "https://www.instagram.com/p/BKXRNdWDY4k/";
    String getPostCreatorAviUrl = "https://scontent.cdninstagram.com/t51.2885-19/s200x200/11850121_656653864371084_542691953_a.jpg";
    String postBody = "" +
            "<span id=\"react-root\"><section data-reactroot=\"\" class=\"_8f735\"><main class=\"_6ltyr _1wptv\" role=\"main\"><div class=\"_ba61e\"><div class=\"_tjnr4\"><article class=\"_ksjel _djxz1 _j5hrx\"><header class=\"_s6yvg\"><a class=\"_5lote _pss4f _vbtk2\" href=\"/carfanatics/\" style=\"width: 30px; height: 30px;\"><img class=\"_a012k\" src=\"https://scontent.cdninstagram.com/t51.2885-19/s150x150/11850121_656653864371084_542691953_a.jpg\"></a><div class=\"_f95g7\"><a class=\"_4zhc5 notranslate _ook48\" title=\"carfanatics\" href=\"/carfanatics/\">carfanatics</a></div><span class=\"_fbms8 _e616g\"><button class=\"_aj7mu _r4e4p _kenyh _o0442\">Following</button></span></header><div><div class=\"_22yr2 _e0mru\"><div class=\"_jjzlb\" style=\"padding-bottom: 100%;\"><img alt=\"Baller status \uD83D\uDD11tag a mate\uD83D\uDD11 \n" +
            "__________\n" +
            "\uD83D\uDCF7: @staeldo | #carfanatics #blacklist #carswithoutlimits #s65amg #g5004x4\" class=\"_icyx7\" id=\"pImage_0\" src=\"https://scontent.cdninstagram.com/t51.2885-15/e35/14128887_1623148301316473_885148507_n.jpg\"></div><!-- react-empty: 18 --><div class=\"_ovg3g\"></div></div></div><div class=\"_es1du _rgrbt\"><section class=\"_tfkbw _hpiil\"><div class=\"_iuf51 _oajsw\"><span class=\"_tf9x3\"><!-- react-text: 26 --><!-- /react-text --><span>298</span><!-- react-text: 28 --> likes<!-- /react-text --></span></div><a class=\"_rmo1e\" href=\"/p/BKXRNdWDY4k/\"><time class=\"_379kp\" datetime=\"2016-09-15T04:43:06.000Z\" title=\"Sep 14, 2016\">3h</time></a></section><ul class=\"_mo9iw _pnraw\"><li class=\"_nk46a\"><h1><a class=\"_4zhc5 notranslate _iqaka\" title=\"carfanatics\" href=\"/carfanatics/\">carfanatics</a><span><!-- react-text: 36 -->Baller status \uD83D\uDD11tag a mate\uD83D\uDD11 <!-- /react-text --><br><!-- react-text: 38 -->__________<!-- /react-text --><br><!-- react-text: 40 -->\uD83D\uDCF7: <!-- /react-text --><a class=\"notranslate\" href=\"/staeldo/\">@staeldo</a><!-- react-text: 42 --> | <!-- /react-text --><a href=\"/explore/tags/carfanatics/\">#carfanatics</a><!-- react-text: 44 --> <!-- /react-text --><a href=\"/explore/tags/blacklist/\">#blacklist</a><!-- react-text: 46 --> <!-- /react-text --><a href=\"/explore/tags/carswithoutlimits/\">#carswithoutlimits</a><!-- react-text: 48 --> <!-- /react-text --><a href=\"/explore/tags/s65amg/\">#s65amg</a><!-- react-text: 50 --> <!-- /react-text --><a href=\"/explore/tags/g5004x4/\">#g5004x4</a></span></h1></li><li class=\"_nk46a\"><a class=\"_4zhc5 notranslate _iqaka\" title=\"tim_bnsi\" href=\"/tim_bnsi/\">tim_bnsi</a><span><!-- react-text: 55 -->\uD83D\uDC4F<!-- /react-text --></span></li></ul><section class=\"_jveic _dsvln\"><a class=\"_ebwb5 _1tv0k\" href=\"#\" role=\"button\" aria-disabled=\"false\"><span class=\"_soakw coreSpriteHeartOpen\">Like</span></a><form class=\"_k3t69\"><input type=\"text\" class=\"_7uiwk _qy55y\" aria-label=\"Add a comment…\" placeholder=\"Add a comment…\" value=\"\"></form><button class=\"_9q0pi coreSpriteEllipsis _soakw\">More options</button></section></div></article></div></div></main><nav class=\"_onabe _kjy2s\" role=\"navigation\"><div class=\"_fjpuc _hykpq\"><div><div class=\"_j4mb5\"></div><div class=\"_bfc7q\"><div class=\"_4kdxu\"><div class=\"_n7q2c\"><div class=\"_r1svv\"><a class=\"_gx3bg\" href=\"/\"><div class=\"_o5rm6 coreSpriteMobileNavHomeActive\"></div></a></div><div class=\"_r1svv\"><a class=\"_gx3bg\" href=\"/explore/\"><div class=\"_o5rm6 coreSpriteMobileNavSearchInactive\"></div></a></div><div class=\"_r1svv\"><a class=\"_gx3bg\" href=\"/accounts/activity/\"><div class=\"_o5rm6 coreSpriteMobileNavActivityInactive\"></div></a></div><div class=\"_r1svv\"><a class=\"_gx3bg\" href=\"/feralcode/\"><div class=\"_o5rm6 coreSpriteMobileNavProfileInactive\"></div></a></div></div></div></div><div class=\"_28zbs _nqfwp\"><span class=\"_hblvq\"><a class=\"_9sso8\" href=\"javascript:;\">Open in app</a><a class=\"_jj6py\" href=\"javascript:;\">×</a></span></div></div></div></nav><footer class=\"_oofbn\" role=\"contentinfo\"><div class=\"_mhrsk _pcuq6\" style=\"max-width: 935px;\"><nav class=\"_p1gbi\" role=\"navigation\"><ul class=\"_fh0f2\"><li class=\"_fw3ds\"><a href=\"/about/us/\">About us</a></li><li class=\"_fw3ds\"><a href=\"https://help.instagram.com/\">Support</a></li><li class=\"_fw3ds\"><a href=\"http://blog.instagram.com/\">Blog</a></li><li class=\"_fw3ds\"><a href=\"/press/\">Press</a></li><li class=\"_fw3ds\"><a href=\"/developer/\">API</a></li><li class=\"_fw3ds\"><a href=\"/about/jobs/\">Jobs</a></li><li class=\"_fw3ds\"><a href=\"/legal/privacy/\">Privacy</a></li><li class=\"_fw3ds\"><a href=\"/legal/terms/\">Terms</a></li><li class=\"_fw3ds\"><a href=\"/about/directory/\">Directory</a></li><li class=\"_fw3ds\"><span class=\"_17z9g\"><!-- react-text: 109 -->Language<!-- /react-text --><select class=\"_nif11\"><option value=\"af\">Afrikaans</option><option value=\"cs\">Czech</option><option value=\"da\">Danish</option><option value=\"de\">German</option><option value=\"el\">Greek</option><option value=\"en\">English</option><option value=\"es\">Spanish</option><option value=\"fi\">Finnish</option><option value=\"fr\">French</option><option value=\"hi\">Hindi</option><option value=\"id\">Indonesian</option><option value=\"it\">Italian</option><option value=\"ja\">Japanese</option><option value=\"ko\">Korean</option><option value=\"ms\">Malay</option><option value=\"nb\">Norwegian</option><option value=\"nl\">Dutch</option><option value=\"pl\">Polish</option><option value=\"pt\">Portuguese (Portugal)</option><option value=\"pt-br\">Portuguese</option><option value=\"ru\">Russian</option><option value=\"sv\">Swedish</option><option value=\"th\">Thai</option><option value=\"tl\">Tagalog/Filipino</option><option value=\"tr\">Turkish</option><option value=\"zh-cn\">Chinese (Simplified)</option><option value=\"zh-tw\">Chinese (Traditional)</option></select></span></li></ul></nav><span class=\"_es4h6\">© 2016 Instagram</span></div></footer></section></span>\n" +
            "\n" +
            "    \n" +
            "\n" +
            "        \n" +
            "    \n" +
            "<script type=\"text/javascript\">window._sharedData = {\"country_code\": \"US\", \"language_code\": \"en\", \"gatekeepers\": {\"cc\": true, \"pf\": true, \"sulgin\": true}, \"show_app_install\": false, \"static_root\": \"//instagramstatic-a.akamaihd.net/h1\", \"platform\": \"android\", \"hostname\": \"www.instagram.com\", \"entry_data\": {\"PostPage\": [{\"media\": {\"caption_is_edited\": false, \"code\": \"BKXRNdWDY4k\", \"date\": 1473914586, \"dimensions\": {\"width\": 1080, \"height\": 1080}, \"usertags\": {\"nodes\": []}, \"comments_disabled\": false, \"comments\": {\"count\": 1, \"page_info\": {\"has_previous_page\": false, \"start_cursor\": null, \"end_cursor\": null, \"has_next_page\": false}, \"nodes\": [{\"text\": \"\\ud83d\\udc4f\", \"created_at\": 1473916435.0, \"id\": \"17863539349009587\", \"user\": {\"username\": \"tim_bnsi\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/13732205_1194492463947531_1197196173_a.jpg\", \"id\": \"604767885\"}}]}, \"id\": \"1339615105818398244\", \"caption\": \"Baller status \\ud83d\\udd11tag a mate\\ud83d\\udd11 \\n__________\\n\\ud83d\\udcf7: @staeldo | #carfanatics #blacklist #carswithoutlimits #s65amg #g5004x4\", \"likes\": {\"count\": 298, \"viewer_has_liked\": false, \"nodes\": [{\"user\": {\"username\": \"kunjalp_007\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/14240947_1394677317217876_238254913_a.jpg\", \"id\": \"797658560\"}}, {\"user\": {\"username\": \"wael_mustang\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/13740926_277923652579567_2002322940_a.jpg\", \"id\": \"3497059792\"}}, {\"user\": {\"username\": \"jousefsafa\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/12331395_428518507337105_1878294846_a.jpg\", \"id\": \"1964181346\"}}, {\"user\": {\"username\": \"aksingh.thind\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/11350740_433811830142337_863401252_a.jpg\", \"id\": \"1696636489\"}}, {\"user\": {\"username\": \"car.lovers22\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/13686982_1117953831586315_1354853288_a.jpg\", \"id\": \"3674348808\"}}, {\"user\": {\"username\": \"azib_faiz12\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/14288186_1048995858552740_113681150_a.jpg\", \"id\": \"3924064158\"}}, {\"user\": {\"username\": \"saeed.h.031\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/14288088_1067189426681330_1883438575_a.jpg\", \"id\": \"1509769646\"}}, {\"user\": {\"username\": \"martez610\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/13473244_285375738476008_291909793_a.jpg\", \"id\": \"421290963\"}}, {\"user\": {\"username\": \"vrajp0008\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/14288204_1114094691992960_1655268072_a.jpg\", \"id\": \"1904540603\"}}, {\"user\": {\"username\": \"novana1111\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/12501754_946884642067842_1126362385_a.jpg\", \"id\": \"14984647\"}}]}, \"owner\": {\"username\": \"carfanatics\", \"is_unpublished\": false, \"requested_by_viewer\": false, \"followed_by_viewer\": true, \"blocked_by_viewer\": false, \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/11850121_656653864371084_542691953_a.jpg\", \"full_name\": null, \"has_blocked_viewer\": false, \"id\": \"18664056\", \"is_private\": false}, \"is_video\": false, \"is_ad\": false, \"display_src\": \"https://scontent.cdninstagram.com/t51.2885-15/e35/14128887_1623148301316473_885148507_n.jpg\", \"location\": null}}]}, \"qe\": {\"profile\": {\"p\": {\"chaining\": \"true\"}, \"g\": \"test\"}, \"su_universe\": {\"p\": {}, \"g\": \"\"}, \"notif\": {\"p\": {\"fcn\": \"false\"}, \"g\": \"control\"}, \"us\": {\"p\": {}, \"g\": \"\"}, \"su\": {\"p\": {\"enabled\": \"true\"}, \"g\": \"test_20160322\"}, \"us_li\": {\"p\": {}, \"g\": \"\"}, \"discovery\": {\"p\": {}, \"g\": \"\"}}, \"display_properties_server_guess\": {\"viewport_width\": 412, \"pixel_ratio\": 1.0}, \"config\": {\"viewer\": {\"username\": \"feralcode\", \"has_profile_pic\": true, \"profile_pic_url_hd\": \"https://scontent.cdninstagram.com/t51.2885-19/s320x320/13534523_481522465305765_1865037812_a.jpg\", \"profile_pic_url\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/13534523_481522465305765_1865037812_a.jpg\", \"full_name\": \"Brandon\", \"id\": \"222145107\", \"biography\": \"\\u264b  M SF/ATL full stack developer,\", \"external_url\": null}, \"csrf_token\": \"9qnnuFkcF8wbTJdSgh1DezXDlpWzhv9I\"}, \"environment_switcher_visible_server_guess\": true};</script>\n" +
            "<script src=\"//instagramstatic-a.akamaihd.net/h1/bundles/en_US_Commons.js/fe72543861a5.js\" type=\"text/javascript\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"//instagramstatic-a.akamaihd.net/h1/bundles/en_US_PostPage.js/b47bd8456da8.js\" type=\"text/javascript\" crossorigin=\"anonymous\"></script>\n" +
            "        \n" +
            "<script>\n" +
            "!function(f,b,e,v,n,t,s){if(f.fbq)return;n=f.fbq=function(){n.callMethod?\n" +
            "n.callMethod.apply(n,arguments):n.queue.push(arguments)};if(!f._fbq)f._fbq=n;\n" +
            "n.push=n;n.loaded=!0;n.version='2.0';n.queue=[];t=b.createElement(e);t.async=!0;\n" +
            "t.src=v;s=b.getElementsByTagName(e)[0];s.parentNode.insertBefore(t,s)}(window,\n" +
            "document,'script','//connect.facebook.net/en_US/fbevents.js');\n" +
            "\n" +
            "fbq('init', '1425767024389221');\n" +
            "\n" +
            "fbq('track', 'PageView');\n" +
            "\n" +
            "\n" +
            "</script>\n" +
            "<noscript>\n" +
            "\n" +
            "\n" +
            "</noscript>\n" +
            "\n" +
            "    \n" +
            "    <script type=\"text/javascript\">window._timings.domInteractive = Date.now()</script>\n" +
            "\n" +
            "    \n" +
            "<div id=\"fb-root\" class=\" fb_reset\"><div style=\"position: absolute; top: -10000px; height: 0px; width: 0px;\"><div><iframe name=\"fb_xdm_frame_https\" frameborder=\"0\" allowtransparency=\"true\" allowfullscreen=\"true\" scrolling=\"no\" id=\"fb_xdm_frame_https\" aria-hidden=\"true\" title=\"Facebook Cross Domain Communication Frame\" tabindex=\"-1\" src=\"https://staticxx.facebook.com/connect/xd_arbiter/r/P5DLcu0KGJB.js?version=42#channel=f231d3c7425e358&amp;origin=https%3A%2F%2Fwww.instagram.com\" style=\"border: none;\"></iframe></div></div><div style=\"position: absolute; top: -10000px; height: 0px; width: 0px;\"><div></div></div></div><div id=\"pswpContainer\"></div>";
    @Test
    public void createPostFromUrl() throws Exception {
        Media post = createPostFromUrl(postUrl);
        assertEquals(post.url, postUrl);
    }

    @Test
    public void parseInstagramProfilePic() throws Exception {
        Media post = createPostFromUrl(postUrl);
        Media parsed = parsePostFromString(postBody, post);

        assertEquals(parsed.url, postUrl);
        assertEquals(parsed.creatorAvi, getPostCreatorAviUrl);
        assertEquals(parsed.creatorName, postCreatorName);
        assertEquals(parsed.image, postImageUrl);
    }

    public Media createPostFromUrl(String postUrl) {
        return new Media(postUrl);
    }

    public Media parsePostFromString(String unParsedBody, Media post) {
        return new InstagramParser(unParsedBody, post);
    }
}
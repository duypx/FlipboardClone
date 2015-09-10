package clone.flipboard.flipboardclone.object;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.widget.FrameLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import clone.flipboard.flipboardclone.model.RssItem;
import clone.flipboard.flipboardclone.rssReader.RssParser;
import clone.flipboard.flipboardclone.util.WebAccessHandler;

/**
 * Created by mainguyen on 9/10/15.
 */
public class AfterStart extends FrameLayout {
    private int image, id;
    private String title, link;
    private static final String DEFAULT_TEXT = "Title";
    private static final String DEFAULT_LINK = "http://www.thanhnsfsdien.comnnn.vn/_layouts/newsrss.aspx?Channel=Gi%C3%A1o+d%E1%BB%A5c";
    private ImageView imageView0, imageView1, imageView2;
    private TextView textView;
    private ArrayList<String> urlList = new ArrayList<String>();
    private Animation slide;

    public AfterStart(Context context, String title, int image, String link) {
        super(context);
        this.title = title;
        this.image = image;
        this.link = link;

        this.setOnClickListener((View.OnClickListener) context);
        this.setOnLongClickListener((View.OnLongClickListener) context);
         this.setBackgroundResource(image);

        imageView0 = new ImageView(context);
        imageView0.setPadding(5, 0, 0, 0);
        imageView0.setImageDrawable(getResources().getDrawable(image));
        imageView0.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView1 = new ImageView(context);
        imageView1.setPadding(5, 0, 0, 0);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView2 = new ImageView(context);
        imageView2.setPadding(5, 0, 0, 0);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        slide = new ScaleAnimation((float) 1, (float) 1, (float) 0, (float) 1,
                Animation.RELATIVE_TO_SELF, (float) 0,
                Animation.RELATIVE_TO_SELF, (float) 1);

        slide.setDuration(1000);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);

        setAnimationParams();

        textView = new TextView(context);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface dincond = Typeface.createFromAsset(context.getAssets(),
                "fonts/segoeui.ttf");
        textView.setTypeface(dincond);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        textView.setText(title);
        textView.setHighlightColor(Color.parseColor("#BCC6CC"));
        textView.setGravity(Gravity.BOTTOM);

    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public void setIcon(int resid) {
        imageView1.setImageDrawable(getResources().getDrawable(resid));
    }

    public void setAnimationParams() {
        Random r = new Random();
//        final int it = this.id % 4 + 1;
        if (isGetTwoUrlFromRSSUrl(link) != 0) {
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                int i = 0;

                public void run() {

                    if (i == 0) {
                        Picasso.with(getContext()).load(urlList.get(0))
                                .into(imageView1);
                        Picasso.with(getContext()).load(urlList.get(1))
                                .into(imageView2);
                    }
                    if (i == 1) {
                        Picasso.with(getContext()).load(urlList.get(1))
                                .into(imageView1);
                        Picasso.with(getContext()).load(urlList.get(0))
                                .into(imageView2);
                    }
                    imageView2.startAnimation(slide);
                    i++;
                    if (i > 1) {
                        i = 0;
                    }
                    handler.postDelayed(this, 3000); // for interval...
                }
            };
            handler.postDelayed(runnable, 0); // for initial delay..

        }

    }
    public int isGetTwoUrlFromRSSUrl(String url) {
        try {

            if (url.equals("facebook") == true || url.equals("twiter") == true
                    || url.equals("google+") == true) {
                return 0;
            }
            WebAccessHandler webhandle = new WebAccessHandler();
            ArrayList<RssItem> temp = new ArrayList<RssItem>();
            InputStream inStream = null;
            inStream = webhandle.getStreamFromLink(url);

            if (inStream == null) {
                return 0;
            }
            temp = new RssParser().parseXML(inStream, 2);
            if (temp.size() == 0 || temp == null || temp.get(0) == null
                    || temp.get(0).getThumbnail() == null) {

                return 0;
            }

            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getThumbnail().length() != 0) {
                    urlList.add(temp.get(i).getThumbnail());
                    Log.d("tag", "linkurl: " + i + ", " + urlList.get(i));
                }
            }
            if (urlList.size() == 1) {
                urlList.add(urlList.get(0));
            }
            if (urlList.size() == 0) {
                return 0;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return 0;
        }
        return 1;
    }
}

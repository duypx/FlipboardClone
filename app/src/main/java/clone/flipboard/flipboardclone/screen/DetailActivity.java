package clone.flipboard.flipboardclone.screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import clone.flipboard.flipboardclone.R;
import clone.flipboard.flipboardclone.model.NewsInfo;
import clone.flipboard.flipboardclone.model.RssItem;
import clone.flipboard.flipboardclone.model.RssItemInfo;

/**
 * Created by mainguyen on 9/8/15.
 */
public class DetailActivity extends Fragment {
    public static final String ARG_PAGE = "page";

    private int mPageNumber;
    private RssItemInfo item;
    private NewsInfo news;

    public static DetailActivity create(int pageNumber,
                                        RssItemInfo new_item, NewsInfo newspaper) {
        DetailActivity fragment = new DetailActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putSerializable(RssItemInfo.class.getName(), new_item);
        args.putSerializable(NewsInfo.class.getName(), newspaper);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        item = (RssItemInfo) getArguments().getSerializable(
                RssItemInfo.class.getName());
        news = (NewsInfo) getArguments().getSerializable(
                NewsInfo.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_read_article, container, false);
        ((TextView) rootView.findViewById(R.id.rss_tv_newspaper)).setText(news
                .getNews().getTitle());
        if (news.getNews().getThumbnail().length() != 0) {
            Picasso.with(getActivity())
                    .load(news.getNews().getThumbnail())
                    .into(((ImageView) rootView
                            .findViewById(R.id.rss_img_newspaper)));
        }
        if (item.getRssItem().getThumbnail().length() != 0) {
            Picasso.with(getActivity()).load(item.getRssItem().getThumbnail())
                    .into(((ImageView) rootView.findViewById(R.id.rss_img)));
        }

        ((TextView) rootView.findViewById(R.id.rss_title)).setText(item
                .getRssItem().getTitle());
//        if (item.getRssItem().getPubDateFormat() != null) {
//            ((TextView) rootView.findViewById(R.id.rss_tv_publish_time))
//                    .setText(item.getRssItemInfo().getPubDateFormat()
//                            .toLocaleString());
//        }
        ((TextView) rootView.findViewById(R.id.rss_content)).setText(item
                .getRssItem().getDescription());
        ((Button) rootView.findViewById(R.id.rss_btn_show_article))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Uri uri = Uri
                                    .parse(item.getRssItem().getLink());
                            getActivity().startActivity(
                                    new Intent(Intent.ACTION_VIEW, uri));
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }
                });
        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }

}
